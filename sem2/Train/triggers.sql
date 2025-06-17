-- Active: 1729132147977@@localhost@5432@MedicalDb
CREATE OR REPLACE FUNCTION check_trip_times_consistency()
RETURNS TRIGGER AS $$
DECLARE
    route_id INTEGER;
BEGIN
    SELECT id_route INTO route_id
    FROM trips
    WHERE id_trip = NEW.id_trip;

    IF NOT EXISTS (
        SELECT 1 FROM route_stations as rs
        WHERE id_route = route_id AND num_station = NEW.num_station
    ) THEN
        RAISE EXCEPTION 'Station % is not found in the route % for the trip %', NEW.num_station, route_id, NEW.id_trip;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

INSERT INTO route_stations(id_route, id_station, num_station, distance) 
VALUES (1, 234, 8, 16);

INSERT INTO trip_times (id_trip, num_station, arrival_time, real_arrival_time, parking_time)
VALUES (1,8 , '2024-02-18 22:12:00', '2024-02-18 22:06:00', '00:15:00');

select *
from trip_times;

CREATE TRIGGER trip_times_consistency
BEFORE INSERT OR UPDATE ON trip_times
FOR EACH ROW
EXECUTE FUNCTION check_trip_times_consistency();

DROP TRIGGER trip_times_consistency ON  trip_times;


#2
CREATE OR REPLACE FUNCTION check_trip_times_temporal_consistency()
RETURNS TRIGGER AS $$
DECLARE
    default_interval INTERVAL := INTERVAL '5 hours';
    prev_departure TIMESTAMP;
    possible_delay INTERVAL;
    total_interval INTERVAL := INTERVAL '0';
    avg_interval INTERVAL;
    trains_count INTEGER := 0;

    route_trip INTEGER;
    prev_station_id INTEGER;
    curr_station_id INTEGER;
    route_info RECORD;
    trip_info RECORD;
    dep_time TIMESTAMP;
    arr_time TIMESTAMP;

BEGIN
    IF NEW.parking_time < INTERVAL '0' THEN
        RAISE EXCEPTION 'parking_time (%s) must be non-negative', NEW.parking_time;
    END IF;

    IF NEW.num_station = 1 THEN
        RETURN NEW;
    END IF;

    SELECT id_route INTO route_trip
    FROM trips
    WHERE id_trip = NEW.id_trip;

    SELECT id_station INTO prev_station_id
    FROM route_stations
    WHERE id_route = route_trip AND num_station = NEW.num_station - 1;

    SELECT id_station INTO curr_station_id
    FROM route_stations
    WHERE id_route = route_trip AND num_station = NEW.num_station;

    SELECT real_arrival_time + parking_time INTO prev_departure
    FROM trip_times
    WHERE id_trip = NEW.id_trip AND num_station = NEW.num_station - 1;

    IF NEW.arrival_time > prev_departure THEN
        RETURN NEW;
    END IF;

    FOR route_info IN (
        SELECT rs1.id_route, rs1.num_station AS num_prev, rs2.num_station AS num_curr
        FROM route_stations rs1
        JOIN route_stations rs2 ON rs1.id_route = rs2.id_route
        WHERE rs1.id_station = prev_station_id
          AND rs2.id_station = curr_station_id
          AND rs2.num_station = rs1.num_station + 1
    ) LOOP
        FOR trip_info IN (
            SELECT id_trip FROM trips WHERE id_route = route_info.id_route
        ) LOOP
            SELECT real_arrival_time + parking_time
            INTO arr_time
            FROM trip_times
            WHERE id_trip = trip_info.id_trip AND num_station = route_info.num_prev;

            SELECT real_arrival_time
            INTO dep_time
            FROM trip_times
            WHERE id_trip = trip_info.id_trip AND num_station = route_info.num_curr;

            IF arr_time IS NOT NULL AND dep_time IS NOT NULL THEN
                total_interval := total_interval + (dep_time - arr_time);
                trains_count := trains_count + 1;
            END IF;
        END LOOP;
    END LOOP;

    possible_delay := NEW.real_arrival_time - NEW.arrival_time;

    IF trains_count = 0 THEN
        NEW.arrival_time := prev_departure + default_interval;
    ELSE
        avg_interval := total_interval / trains_count;
        NEW.arrival_time := prev_departure + avg_interval;

    END IF;

    NEW.real_arrival_time := NEW.arrival_time + possible_delay;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


INSERT INTO trip_times (id_trip, num_station, arrival_time, real_arrival_time, parking_time)
VALUES (7, 4, '2024-12-21 15:46:58.937074'	,'2024-12-21 16:50:58.937074', '00:15:00');

INSERT INTO route_stations(id_route, id_station, num_station, distance) 
VALUES (7, 114, 4, 16);

Select * from trip_times;

select * from route_stations;

CREATE TRIGGER check_trip_times_temporal
BEFORE INSERT OR UPDATE ON trip_times
FOR EACH ROW
EXECUTE FUNCTION check_trip_times_temporal_consistency();

DROP TRIGGER check_trip_times_temporal ON trip_times;


#3
CREATE OR REPLACE FUNCTION set_free_route_number() RETURNS trigger AS $$
DECLARE
    new_id INTEGER;
BEGIN

    IF NEW.id_route IS NULL THEN
        SELECT MIN(id) INTO new_id
        FROM (
            SELECT id
            FROM generate_series(1, GREATEST(
                1,
                (SELECT MAX(id_route) FROM routes),
                (SELECT MAX(id_route) FROM trips)
            )) AS id
            EXCEPT
            SELECT id_route FROM routes
            EXCEPT
            SELECT id_route FROM trips
        ) AS free_ids;

        NEW.id_route := new_id;
        RAISE NOTICE 'Assigned new id_route: %', new_id;

    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER set_free_route_number_trigger
BEFORE INSERT ON routes
FOR EACH ROW
EXECUTE FUNCTION set_free_route_number();

INSERT INTO routes (name) VALUES ('Мkl');

DROP TRIGGER set_free_route_number_trigger ON routes;
SELECT * from routes;
select * from trips;

#4
CREATE TABLE train_deletion_audit (
    id SERIAL PRIMARY KEY,
    train_number INTEGER NOT NULL,
    id_category INTEGER,
    head_station INTEGER,
    total_tickets INTEGER NOT NULL,
    deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_by TEXT
);

select *
FROM train_deletion_audit;

DROP TABLE IF EXISTS train_deletion_audit;

CREATE OR REPLACE FUNCTION log_deleted_train_if_needed()
RETURNS TRIGGER AS $$
DECLARE
    tickets_count INTEGER;
BEGIN

    SELECT SUM(pt.count_tickets) INTO tickets_count
    FROM trips t
    JOIN passenger_trips pt ON t.id_trip = pt.id_trip
    WHERE t.id_train = OLD.train_number;

    IF tickets_count > 100 THEN
        INSERT INTO train_deletion_audit (
            train_number, id_category, head_station, total_tickets, deleted_by
        ) VALUES (
            OLD.train_number, OLD.id_category, OLD.head_station, tickets_count, CURRENT_USER
        );
    END IF;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER train_deletion_trigger
BEFORE DELETE ON trains
FOR EACH ROW
EXECUTE FUNCTION log_deleted_train_if_needed();

DELETE FROM trains WHERE train_number = 454;
DROP TRIGGER train_deletion_trigger ON trains;


SELECT t.id_train, SUM(pt.count_tickets) AS tickets_count
FROM trips t
JOIN passenger_trips pt ON t.id_trip = pt.id_trip
GROUP BY t.id_train
ORDER BY tickets_count DESC;



DO $$ 
DECLARE
    r RECORD;
BEGIN
    -- Для каждой таблицы в текущей схеме
    FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public') LOOP
        EXECUTE 'DROP TABLE IF EXISTS public.' || r.tablename || ' CASCADE';
    END LOOP;
END $$;
