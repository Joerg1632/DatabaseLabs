-- Active: 1729132147977@@localhost@5432@Railways
DO $$ 
DECLARE
BEGIN
    CREATE TEMP TABLE report (
        report_date DATE PRIMARY KEY,
        count_trips INTEGER,
        passenger_count INTEGER,
        distance_sum INTEGER
    );
    <<count_trips>>
    DECLARE
        row RECORD;
        cur CURSOR FOR (
        SELECT trip_day AS day, COUNT(*) AS trip_count
        FROM (
            SELECT generate_series(
                DATE(MIN(tt.arrival_time + tt.parking_time)),
                DATE(MAX(tt.arrival_time)),
                INTERVAL '1 day'
            ) AS trip_day
            FROM trip_times tt
            GROUP BY id_trip
        ) AS generated_days
        GROUP BY day
        );
    BEGIN
        OPEN cur;
        LOOP
            FETCH cur INTO row;
            EXIT WHEN NOT FOUND;
            INSERT INTO report (report_date, count_trips, passenger_count, distance_sum)
            VALUES (row.day, row.trip_count, 0, 0);
        END LOOP;
        CLOSE cur;
    END;

    <<passenger_count_distance>>
    DECLARE
        row RECORD;
        cur CURSOR FOR (
            SELECT DATE(generate_series(DATE(tt_start.arrival_time + tt_start.parking_time), DATE(tt_end.arrival_time), INTERVAL '1 day')) AS day, 
                   ABS(rs_end.distance - rs_start.distance) / ((DATE(tt_end.arrival_time) - DATE(tt_start.arrival_time + tt_start.parking_time)) + 1) AS distance
            FROM passenger_trips pt
            JOIN trips t ON pt.id_trip = t.id_trip
            JOIN route_stations rs_start ON rs_start.id_route = t.id_route AND rs_start.id_station = pt.departure_station
            JOIN route_stations rs_end ON rs_end.id_route = t.id_route AND rs_end.id_station = pt.destination_station
            JOIN trip_times tt_start ON tt_start.id_trip = pt.id_trip AND tt_start.num_station = rs_start.num_station
            JOIN trip_times tt_end ON tt_end.id_trip = pt.id_trip AND tt_end.num_station = rs_end.num_station
        );
    BEGIN
        OPEN cur;   
        LOOP
            FETCH cur INTO row;
            EXIT WHEN NOT FOUND;
            INSERT INTO report (report_date, count_trips, passenger_count, distance_sum)
            VALUES (row.day, 0, 1, row.distance)
            ON CONFLICT (report_date)
            DO UPDATE SET
                passenger_count = report.passenger_count + 1,
                distance_sum = report.distance_sum + EXCLUDED.distance_sum;
        END LOOP;
        CLOSE cur;
    END;

    <<out>>
    DECLARE
        row RECORD;
        current_quarter INTEGER := NULL;
        current_year INTEGER := NULL;
        prev_quarter INTEGER := NULL;
        prev_year INTEGER := NULL;
        count_trips INTEGER := 0;
        passenger_count INTEGER := 0;
        distance_sum INTEGER := 0;
        count_trips_year INTEGER := 0;
        passenger_count_year INTEGER := 0;
        distance_sum_year INTEGER := 0;
        cur CURSOR FOR (SELECT * FROM report ORDER BY report_date);
    BEGIN
        OPEN cur;
        LOOP
            FETCH cur INTO row;
            EXIT WHEN NOT FOUND;
            current_year := EXTRACT(YEAR FROM row.report_date);
            current_quarter := EXTRACT(QUARTER FROM row.report_date);

            IF current_quarter != prev_quarter THEN
                RAISE NOTICE 'Year: %, Quarter: %, Trips Count: %, Passenger Count: %, Passenger Distance Sum: %',
                    prev_year, prev_quarter, count_trips, passenger_count, distance_sum;
                count_trips_year := count_trips_year + count_trips;
                passenger_count_year := passenger_count_year + passenger_count;
                distance_sum_year := distance_sum_year + distance_sum;
                count_trips := 0;
                passenger_count := 0;
                distance_sum := 0;

                IF current_year != prev_year THEN
                    RAISE NOTICE 'Year: %, Trips Count: %, Passenger Count: %, Passenger Distance Sum: %',
                        prev_year, count_trips_year, passenger_count_year, distance_sum_year;
                    count_trips_year := 0;
                    passenger_count_year := 0;
                    distance_sum_year := 0;
                END IF;
            END IF;

            prev_year := current_year;
            prev_quarter := current_quarter;
            count_trips := count_trips + row.count_trips;
            passenger_count := passenger_count + row.passenger_count;
            distance_sum := distance_sum + row.distance_sum;

            RAISE NOTICE 'Date: %, Trips Count: %, Passenger Count: %, Passenger Distance Sum: %',
                row.report_date, count_trips, passenger_count, distance_sum;
        END LOOP;
        CLOSE cur;
    END;

    DROP TABLE IF EXISTS report;

END $$;





