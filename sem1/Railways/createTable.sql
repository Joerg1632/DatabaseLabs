-- Active: 1729132147977@@localhost@5432@Railways

DROP TABLE IF EXISTS train_crew, workers, passenger_trips, seat_action, trip_times, trips, route_stations, train_seats, trains, passengers, routes, seat_categories, stations, worker_positions, train_types


SELECT * FROM train_types;
-- Категории поездов

CREATE TABLE train_types (
    id_type SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

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
    distance INTEGER NOT NULL,
    PRIMARY KEY (id_route, num_station),
    FOREIGN KEY (id_route) REFERENCES routes(id_route),
    FOREIGN KEY (id_station) REFERENCES stations(id_station)
);


Select * from trips;
-- Информация о поездке
CREATE TABLE trips (
    id_trip SERIAL PRIMARY KEY,
    id_route INTEGER,
    id_train INTEGER,
    FOREIGN KEY (id_route) REFERENCES routes(id_route),
    FOREIGN KEY (id_train) REFERENCES trains(train_number)
);

Select * from trip_times;
-- Времена прибытия/отправления
CREATE TABLE trip_times (
    id_trip INTEGER,
    num_station INTEGER,
    arrival_time TIMESTAMP,
    real_arrival_time TIMESTAMP,
    parking_time INTERVAL,
    PRIMARY KEY (id_trip, num_station),
    FOREIGN KEY (id_trip) REFERENCES trips(id_trip)
);


Select * from seat_action;
-- Действия с местами
CREATE TABLE seat_action (
    id_trip INTEGER,
    id_seat_category INTEGER,
    num_section INTEGER,
    occupied_seats INTEGER,
    PRIMARY KEY (id_trip, id_seat_category, num_section),
    FOREIGN KEY (id_trip) REFERENCES trips(id_trip),
    FOREIGN KEY (id_seat_category) REFERENCES seat_categories(id_seat_category)
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
    FOREIGN KEY (id_trip) REFERENCES trips(id_trip),
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
    FOREIGN KEY (id_trip) REFERENCES trips(id_trip),
    FOREIGN KEY (id_worker) REFERENCES workers(passport_id)
);
