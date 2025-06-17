-- Active: 1729132147977@@localhost@5432@Railways

DROP TABLE IF EXISTS train_crew, workers, passenger_trips, seat_action, trip_times, trips, route_stations, train_seats, trains, passengers, routes, seat_categories, stations, worker_positions, train_types

GRANT CREATE ON DATABASE Railways TO postgres;
SELECT current_database();
SELECT * FROM train_types;
-- Категории поездов

CREATE TABLE train_types (
    id_type SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

INSERT INTO train_types (name) VALUES ('Luxury');
INSERT INTO train_types (name) VALUES ('Economy');

SELECT * FROM worker_positions; 

-- Должности сотрудников
CREATE TABLE worker_positions (
    id_worker_position SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

SELECT * from stations;

-- Список станций
CREATE TABLE stations (
    id_station SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

SELECT * from seat_categories;

-- Категории мест
CREATE TABLE seat_categories (
    id_seat_category SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

SELECT * from routes;
-- Маршруты поездов
CREATE TABLE routes (
    id_route SERIAL PRIMARY KEY,
    name VARCHAR(15) NOT NULL
);

INSERT INTO routes(id_route, name)
VALUES (11, 'iываojБ');


SELECT * from passengers;
-- Информация о пассажирах
CREATE TABLE passengers (
    passport_id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    patronymic VARCHAR(20)
);

SELECT * from trains;

-- Таблица поездов
CREATE TABLE trains (
    train_number SERIAL PRIMARY KEY,
    id_category INTEGER,
    head_station INTEGER,
    FOREIGN KEY (id_category) REFERENCES train_types(id_type)
);

INSERT INTO trains (id_category, head_station) 
VALUES (1, 1); 

INSERT INTO trains (id_category, head_station) 
VALUES (2, 1); 

SELECT * from train_seats;

-- Количество мест по категориям
CREATE TABLE train_seats (
    train_number INTEGER,
    id_category INTEGER,
    count_tickets INTEGER,
    PRIMARY KEY (train_number, id_category),
    FOREIGN KEY (id_category) REFERENCES seat_categories(id_seat_category)
);

Select * from route_stations;
-- Станции маршрутов
CREATE TABLE route_stations (
    id_route INTEGER,
    id_station INTEGER,
    num_station INTEGER NOT NULL,
    distance INTEGER,
    PRIMARY KEY (id_route, num_station),
    FOREIGN KEY (id_route) REFERENCES routes(id_route),
    FOREIGN KEY (id_station) REFERENCES stations(id_station)
);

INSERT INTO route_stations(id_route, id_station, num_station, distance) 
VALUES (1, 239, 8, 16);

Select * from trips;
-- Информация о поездке
CREATE TABLE trips (
    id_trip SERIAL PRIMARY KEY,
    id_route INTEGER,
    id_train INTEGER,
    FOREIGN KEY (id_route) REFERENCES routes(id_route),
    FOREIGN KEY (id_train) REFERENCES trains(train_number) ON DELETE CASCADE
);

INSERT INTO trips (id_route, id_train) 
VALUES (1, 1); 

INSERT INTO trips (id_route, id_train) 
VALUES (6, 2); 

INSERT INTO trips (id_route, id_train) 
VALUES (6, 1);

INSERT INTO trips (id_route, id_train) 
VALUES (15, 2);

INSERT INTO trips (id_route, id_train) 
VALUES (20000, 2);


Select * from trip_times
WHERE DATE(arrival_time) = '2024-04-26';
-- Времена прибытия/отправления
CREATE TABLE trip_times (
    id_trip INTEGER,
    num_station INTEGER,
    arrival_time TIMESTAMP,
    real_arrival_time TIMESTAMP,
    parking_time INTERVAL,
    PRIMARY KEY (id_trip, num_station),
    FOREIGN KEY (id_trip) REFERENCES trips(id_trip) ON DELETE CASCADE
);


Select * from passenger_trips;
-- Поездки пассажиров
CREATE TABLE passenger_trips (
    id_passenger INTEGER,
    id_trip INTEGER,
    departure_station INTEGER,
    destination_station INTEGER,
    PRIMARY KEY (id_passenger, id_trip),
    FOREIGN KEY (id_passenger) REFERENCES passengers(passport_id),
    FOREIGN KEY (id_trip) REFERENCES trips(id_trip) ON DELETE CASCADE,
    FOREIGN KEY (departure_station) REFERENCES stations(id_station),
    FOREIGN KEY (destination_station) REFERENCES stations(id_station)
);

Select * from workers;
-- Сотрудники РЖД
CREATE TABLE workers (
    passport_id SERIAL PRIMARY KEY,
    id_position INTEGER,
    id_station INTEGER,
    id_boss INTEGER,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    patronymic VARCHAR(20),
    FOREIGN KEY (id_position) REFERENCES worker_positions(id_worker_position),
    FOREIGN KEY (id_station) REFERENCES stations(id_station),
    FOREIGN KEY (id_boss) REFERENCES workers(passport_id)
);

Select * from train_crew;
-- Бригада поезда
CREATE TABLE train_crew (
    id_trip INTEGER,
    id_worker INTEGER,
    PRIMARY KEY (id_trip, id_worker),
    FOREIGN KEY (id_trip) REFERENCES trips(id_trip) ON DELETE CASCADE,
    FOREIGN KEY (id_worker) REFERENCES workers(passport_id)
);

