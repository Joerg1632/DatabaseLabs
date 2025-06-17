-- Active: 1729132147977@@localhost@5432@Railways
#1.4
CREATE OR REPLACE FUNCTION update_arrival_times_by_date(
    target_date DATE
)
RETURNS VOID AS $$
DECLARE
    trip_times_row RECORD;
    delay_interval INTERVAL;
    updated_count INTEGER := 0;
    exists_on_date BOOLEAN;
BEGIN

    SELECT EXISTS (
    SELECT 1 FROM trip_times
    WHERE DATE(real_arrival_time) = target_date
    ) INTO exists_on_date;

    IF NOT exists_on_date THEN
        RAISE EXCEPTION 'В расписании нет поездов, прибывающих в дату %', target_date;
    END IF;

    FOR trip_times_row IN
        SELECT id_trip, num_station, arrival_time, real_arrival_time
        FROM trip_times
        WHERE DATE(real_arrival_time) = target_date
        AND real_arrival_time <> arrival_time
    LOOP
        delay_interval := trip_times_row.real_arrival_time - trip_times_row.arrival_time;

        UPDATE trip_times
        SET arrival_time = arrival_time + delay_interval
        WHERE id_trip = trip_times_row.id_trip
          AND num_station = trip_times_row.num_station;

        updated_count := updated_count + 1;
    END LOOP;

    IF updated_count = 0 THEN
        RAISE NOTICE 'Нет поездов, прибывающих в дату %, которые имеют задержку', target_date;
    ELSE
        RAISE NOTICE 'Обновлено % записей на дату %', updated_count, target_date;
    END IF;
END;
$$ LANGUAGE plpgsql;


SELECT update_arrival_times_by_date('2026-01-10');

select * from trip_times;
