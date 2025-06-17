WITH daily_trip_counts AS (
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
),
passenger_activity AS (
    SELECT activity_day AS day, COUNT(*) AS passengers, SUM(daily_distance) AS total_distance
    FROM (
        SELECT 
            generate_series(
                DATE(tt_start.arrival_time + tt_start.parking_time),
                DATE(tt_end.arrival_time),
                INTERVAL '1 day'
            ) AS activity_day,
            ABS(rs_end.distance - rs_start.distance) / (
                DATE(tt_end.arrival_time) - DATE(tt_start.arrival_time + tt_start.parking_time) + 1
            ) AS daily_distance
        FROM passenger_trips pt
        JOIN trips t ON pt.id_trip = t.id_trip
        JOIN route_stations rs_start ON rs_start.id_route = t.id_route AND rs_start.id_station = pt.departure_station
        JOIN route_stations rs_end ON rs_end.id_route = t.id_route AND rs_end.id_station = pt.destination_station
        JOIN trip_times tt_start ON tt_start.id_trip = pt.id_trip AND tt_start.num_station = rs_start.num_station
        JOIN trip_times tt_end ON tt_end.id_trip = pt.id_trip AND tt_end.num_station = rs_end.num_station
    ) AS expanded_days
    GROUP BY day
),
combined_stats AS (
    SELECT 
        COALESCE(dtc.day, pa.day) AS report_date,
        COALESCE(dtc.trip_count, 0) AS trip_count,
        COALESCE(pa.passengers, 0) AS passenger_count,
        COALESCE(pa.total_distance, 0) AS distance_sum
    FROM daily_trip_counts dtc
    FULL JOIN passenger_activity pa ON dtc.day = pa.day
),
aggregated_report AS (
    SELECT 
        report_date,
        EXTRACT(YEAR FROM report_date) AS year,
        EXTRACT(QUARTER FROM report_date) AS quarter,
        SUM(trip_count) AS trip_count,
        SUM(passenger_count) AS passenger_count,
        SUM(distance_sum) AS distance_sum
    FROM combined_stats
    GROUP BY ROLLUP(EXTRACT(YEAR FROM report_date), EXTRACT(QUARTER FROM report_date), report_date)
    HAVING report_date IS NULL
    UNION ALL
    SELECT 
        report_date,
        EXTRACT(YEAR FROM report_date) AS year,
        EXTRACT(QUARTER FROM report_date) AS quarter,
        SUM(trip_count) OVER (PARTITION BY EXTRACT(YEAR FROM report_date), EXTRACT(QUARTER FROM report_date) ORDER BY report_date) AS trip_count,
        SUM(passenger_count) OVER (PARTITION BY EXTRACT(YEAR FROM report_date), EXTRACT(QUARTER FROM report_date) ORDER BY report_date) AS passenger_count,
        SUM(distance_sum) OVER (PARTITION BY EXTRACT(YEAR FROM report_date), EXTRACT(QUARTER FROM report_date) ORDER BY report_date) AS distance_sum
    FROM combined_stats
)
SELECT 
    CASE 
        WHEN report_date IS NULL AND quarter IS NULL THEN year || ' — итог за год'
        WHEN report_date IS NULL THEN 'Квартал ' || quarter || ', ' || year
        ELSE TO_CHAR(report_date, 'YYYY-MM-DD')
    END AS formatted_date,
    trip_count,
    passenger_count,
    distance_sum
FROM aggregated_report
ORDER BY year, quarter, report_date;