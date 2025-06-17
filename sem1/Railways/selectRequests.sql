-- Active: 1729132147977@@localhost@5432@Railways


--Отчет о задержках поездов указанного поезда в указанное время
SELECT DATE(tt.real_arrival_time) AS "Дата",
    (tt.real_arrival_time - tt.arrival_time) AS "Задержка",
    s."name" as "Станция"
FROM trips t
JOIN trip_times tt ON t.id_trip = tt.id_trip
JOIN route_stations rs ON rs.id_route = t.id_route AND tt.num_station = rs.num_station
JOIN stations s ON s.id_station = rs.id_station
WHERE t.id_train = 16 and DATE(tt.arrival_time) BETWEEN '2024-12-01 00:00:00' AND '2025-01-01 00:00:00'
AND (tt.real_arrival_time - tt.arrival_time) > INTERVAL '0 seconds';



--Отчёты о маршрутах и поездах между указанными городами
SELECT t.id_route as "Номер маршрута", s1.name as "Город 1", s2.name as "Город 2", t.id_train as "Номер поезда"
from route_stations rs1
JOIN route_stations rs2 ON rs2.id_route = rs1.id_route AND rs1.num_station < rs2.num_station
JOIN trips t ON t.id_route = rs1.id_route
JOIN stations s1 ON s1.id_station = rs1.id_station
JOIN stations s2 ON s2.id_station = rs2.id_station
WHERE s1.name = 'Новосибирск' AND s2.name = 'Нижний Новгород';



--Все станции-пересадки по маршруту между двумя станциями (от заданной до заданной).
WITH bounds AS (
    SELECT 
        rs.id_route,
        MIN(CASE WHEN s.name = 'Новосибирск' THEN rs.num_station END) AS station1,
        MAX(CASE WHEN s.name = 'Нижний Новгород' THEN rs.num_station END) AS station2
    FROM route_stations rs
    JOIN stations s ON rs.id_station = s.id_station
    GROUP BY rs.id_route
)
SELECT rs.id_route AS "Маршрут", s.name AS "Станции-пересадки"
FROM route_stations rs
JOIN stations s ON rs.id_station = s.id_station
JOIN bounds b ON rs.id_route = b.id_route and b.station1 is NOT NULL and b.station2 is NOT NULL and b.station1 < b.station2
WHERE  rs.num_station > b.station1 AND rs.num_station < b.station2;



--Количество билетов на указанный поезд (от заданного города до заданного в указанный промежуток времени) с заданным типом мест (плацкарт/купе/СВ)
WITH trips_tick_per_sec AS(
    SELECT DISTINCT tr.id_trip, (ts.count_tickets - sa.occupied_seats) AS free
    FROM trips tr
    JOIN trains t ON t.train_number = tr.id_train AND t.train_number = 340
    JOIN train_seats ts ON ts.train_number = t.train_number AND ts.id_category = 4
    JOIN trip_times tt ON tt.id_trip = tr.id_trip
    JOIN route_stations rs1 ON rs1.id_route = tr.id_route
    JOIN stations s1 ON s1.id_station = rs1.id_station
    JOIN route_stations rs2 ON rs2.id_route = tr.id_route AND rs1.num_station < rs2.num_station
    JOIN stations s2 ON s2.id_station = rs2.id_station
    JOIN seat_action sa ON sa.id_trip = tr.id_trip AND sa.id_seat_category = 4
    WHERE s1.name = 'Новосибирск' AND s2.name = 'Нижний Новгород' AND (DATE(tt.arrival_time) BETWEEN '2024-12-01 00:00:00' AND '2025-01-01 00:00:00')
)
SELECT DISTINCT ttps.id_trip as Номер_маршрута, MIN(free) over (PARTITION BY id_trip) AS Количество_билетов
FROM trips_tick_per_sec ttps;



-- Отчёт о едущих ближайших поездах в указанный город в указанный отрезок времени с указанием дат-времён отправления из начальной точки и прибытия в конечную точку.
WITH initial_station AS (
    SELECT t.id_trip, t.id_route, t.id_train, rs.num_station AS initial_station_num, st.name AS initial_station_name, tt.arrival_time AS initial_arrival_time,
    (tt.arrival_time + tt.parking_time) AS initial_departure_time
    FROM trips t
    JOIN route_stations rs ON rs.id_route = t.id_route AND rs.num_station = 1 
    JOIN stations st ON st.id_station = rs.id_station
    JOIN trip_times tt ON tt.id_trip = t.id_trip AND tt.num_station = rs.num_station
),
destination_trips AS (
    SELECT t.id_trip, st.name AS destination_station_name, tt.arrival_time AS destination_arrival_time
    FROM trips t
    JOIN route_stations rs ON rs.id_route = t.id_route
    JOIN stations st ON st.id_station = rs.id_station
    JOIN trip_times tt ON tt.id_trip = t.id_trip AND tt.num_station = rs.num_station
    WHERE st.name = 'Новосибирск' AND tt.num_station != 1 AND tt.arrival_time BETWEEN '2024-12-01 00:00:00' AND '2025-01-01 00:00:00'
)
SELECT "is".id_trip, "is".id_train AS train_number, "is".initial_station_name AS departure_station, "is".initial_departure_time, dt.destination_station_name AS arrival_station,
    dt.destination_arrival_time
FROM initial_station "is"
JOIN destination_trips dt ON "is".id_trip = dt.id_trip;



--Сотрудники РЖД с иерархией (у каждого сотрудника есть непосредственный рук-ль, у него – свой и т.д., у владельца бизнеса рук-ля нет)
WITH RECURSIVE WorkerHierarchy AS (
    SELECT w.passport_id AS worker_id, w.id_position AS position, w.id_boss AS boss_id,
        Cast(w.passport_id as Varchar) as path,
        CONCAT(' ', w.surname, ' ', w.name, ' ', COALESCE(w.patronymic, '')) AS Worker,
        1 AS level 
    FROM workers w
    WHERE w.id_boss IS NULL 

    UNION ALL

    SELECT w.passport_id AS worker_id, w.id_position AS position, w.id_boss AS boss_id,
        Cast(wh.path || '->' || w.passport_id as VARCHAR) as path,
        CONCAT(LPAD('', wh.level * 5, ' '), w.surname, ' ', w.name, ' ', COALESCE(w.patronymic, '')) AS Worker,
        wh.level + 1 AS level  
    FROM workers w
    JOIN WorkerHierarchy wh ON w.id_boss = wh.worker_id  
)
SELECT Worker, position, boss_id, level, path
FROM WorkerHierarchy;