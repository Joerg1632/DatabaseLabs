import psycopg2
from psycopg2.extras import execute_batch
from random import randint, choice, uniform
from datetime import datetime, timedelta
import random
import calendar


def connect_db():
    return psycopg2.connect(
        dbname="Railways",
        user="postgres",
        password="yura2004",
        host="localhost",
        port="5432"
    )

def fill_tables():
    with connect_db() as conn:
        with conn.cursor() as cursor:
            clear_tables(cursor)

            fill_train_types(cursor)
            fill_worker_positions(cursor)
            fill_stations(cursor)
            # fill_seat_categories(cursor)
            fill_routes(cursor)
            fill_passengers(cursor)

            fill_trains(cursor)
            # fill_train_seats(cursor)
            fill_route_stations(cursor)
            fill_trips(cursor)
            fill_trip_times(cursor)
            fill_workers(cursor)
            fill_train_crew(cursor)
            fill_passenger_trips(cursor)

def clear_tables(cursor):
    cursor.execute("TRUNCATE TABLE train_types, worker_positions, routes, passengers, stations,  trains,  workers, route_stations, trips, trip_times, passenger_trips, train_crew RESTART IDENTITY CASCADE;")

def fill_train_types(cursor): 
    types = ["Пассажирский", "Экспресс"]
    cursor.executemany("INSERT INTO train_types (name) VALUES (%s)", [(cat,) for cat in types])

def fill_worker_positions(cursor): 
    worker_positions = ["Машинист", "Проводник", "Техник", "Диспетчер","Директор Ржд"]
    cursor.executemany("INSERT INTO worker_positions (name) VALUES (%s)", [(cat,) for cat in worker_positions])


def fill_stations(cursor):
    cities = [
        "Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург", "Нижний Новгород",
        "Казань", "Челябинск", "Омск", "Самара", "Ростов-на-Дону", "Уфа", "Красноярск",
        "Воронеж", "Пермь", "Волгоград", "Краснодар", "Тюмень", "Ижевск", "Барнаул",
        "Тольятти", "Кемерово", "Рязань", "Астрахань", "Ульяновск", "Иркутск", "Хабаровск",
        "Ярославль", "Магнитогорск", "Киров", "Саратов", "Мурманск"
    ]
    
    for city in cities:
        cursor.execute("INSERT INTO stations (name) VALUES (%s)", (city,))
    print("30 станций с городами успешно добавлены.")
    
    for i in range(32, 301):
        station_name = f"Станция {i}"
        cursor.execute("INSERT INTO stations (name) VALUES (%s)", (station_name,))
    print("270 станций с номерами успешно добавлены.")


def fill_seat_categories(cursor): 
    categories = ["Общий", "Плацкартный", "Купейный", "СВ"]
    cursor.executemany("INSERT INTO seat_categories (name) VALUES (%s)", [(cat,) for cat in categories])


def fill_routes(cursor): 
    try:
        route_sql = "INSERT INTO routes (name) VALUES (%s)"

        routes = [f"Маршрут {i}" for i in range(1, 20001)]

        cursor.executemany(route_sql, [(route,) for route in routes])
        
        print("Маршруты успешно добавлены.")
    except Exception as e:
        print(f"Ошибка при вставке маршрутов: {e}")

def fill_passengers(cursor):   
    passengers = []

    for i in range(75000):
        passport_number = i  
        passengers.append((
            f"Имя{i}",
            f"Фамилия{i}",
            f"Отчество{i}",
            passport_number
        ))

    cursor.executemany(
        "INSERT INTO passengers (name, surname, patronymic, passport_id) VALUES (%s, %s, %s, %s)",
        passengers
    )

def fill_trains(cursor): 
    insert_sql = "INSERT INTO trains (id_category, head_station) VALUES (%s, %s)"

    try:
        trains = []
        for i in range(1, 1001):
            category_id = random.randint(1, 2)
            head_station_id = random.randint(1, 300)
            
            trains.append((category_id, head_station_id))
            
            if i % 100 == 0:
                cursor.executemany(insert_sql, trains)
                trains.clear()

        if trains:
            cursor.executemany(insert_sql, trains)

        print("Добавление поездов выполнено")
    except Exception as e:
        print(f"Ошибка: {e}")


def fill_train_seats(cursor): 
    insert_sql = "INSERT INTO train_seats (train_number, id_category, count_tickets) VALUES (%s, %s, %s)"
    
    random_gen = random.Random()

    try:
        train_seats = []
        for train_number in range(1, 1001):
            for category_id in range(1, 5):
                count_tickets = random_gen.randint(50, 100)
                train_seats.append((train_number, category_id, count_tickets))

        cursor.executemany(insert_sql, train_seats)
        cursor.connection.commit()
        print(f"Количество записей для вставки: {len(train_seats)}")
        print("Добавление train_seats выполнено.")
    
    except Exception as e:
        print(f"Ошибка при вставке данных: {e}")



def fill_workers(cursor): 
    initial_insert_sql = """
    INSERT INTO workers (passport_id, name, surname, patronymic, id_position, id_boss, id_station)
    VALUES (%s, %s, %s, %s, %s, %s, %s)
    """
    initial_workers = [
        (1, 'Имя1', 'Фамилия1', 'Отчество1', 5, None, None),
        (2, 'Имя2', 'Фамилия2', 'Отчество2', 1, 1, 2),
        (3, 'Имя3', 'Фамилия3', 'Отчество3', 2, 1, 3),
        (4, 'Имя4', 'Фамилия4', 'Отчество4', 3, 1, 4),
        (5, 'Имя5', 'Фамилия5', 'Отчество5', 4, 1, 1),
    ]
    execute_batch(cursor, initial_insert_sql, initial_workers)
    
    insert_sql = """
    INSERT INTO workers (passport_id, name, surname, patronymic, id_position, id_boss, id_station)
    VALUES (%s, %s, %s, %s, %s, %s, %s)
    """
    random_gen = random.Random()
    workers = []
    
    for i in range(6, 50001): 
        passport_number = i
        name = f"Имя{i}"
        surname = f"Фамилия{i}"
        patronymic = f"Отчество{i}"
        id_position = (i % 4) + 1  
        id_boss = random_gen.randint(1, i-1) 
        id_station = (i % 300) + 1 

        workers.append((passport_number, name, surname, patronymic, id_position, id_boss, id_station))
        
        if i % 1000 == 0:
            execute_batch(cursor, insert_sql, workers)
            workers.clear()  

    if workers: 
        execute_batch(cursor, insert_sql, workers)
    
    cursor.connection.commit()
    print(f"Добавлено {i-4} работников.")


def fill_route_stations(cursor):
    try:
        route_stations_sql = """
            INSERT INTO route_stations (id_route, num_station, id_station, distance)
            VALUES (%s, %s, %s, %s)
        """
        data = []

        for route_id in range(1, 20001):
            station_count = random.randint(3, 8)
            used_stations = set()
            accumulated_distance = 0 

            for station_num in range(1, station_count + 1):
                station_id = random.randint(1, 230)
                while station_id in used_stations:
                    station_id = random.randint(1, 230)
                used_stations.add(station_id)

                if station_num > 1:
                    distance = random.randint(50, 500)
                    accumulated_distance += distance
                else:
                    distance = 0 

                data.append((route_id, station_num, station_id, accumulated_distance))

        cursor.executemany(route_stations_sql, data)
        print("Станции маршрутов успешно добавлены с накопленным расстоянием.")
    except Exception as e:
        print(f"Ошибка при вставке станций маршрутов: {e}")


def fill_trips(cursor):
    try:
        trip_sql = """
            INSERT INTO trips (id_train, id_route)
            VALUES (%s, %s)
        """
        data = [(random.randint(1, 1000), route_id) for route_id in range(1, 20001)]
        cursor.executemany(trip_sql, data)
        print("Поездки успешно добавлены.")
    except Exception as e:
        print(f"Ошибка при вставке поездок: {e}")


def fill_trip_times(cursor):
    try:
        trip_times_sql = """
            INSERT INTO trip_times (id_trip, num_station, parking_time, arrival_time, real_arrival_time)
            VALUES (%s, %s, %s::INTERVAL, %s, %s)
        """

        # Получаем trip_id и связанный id_route
        cursor.execute("SELECT id_trip, id_route FROM trips")
        trip_route_map = cursor.fetchall()  # [(id_trip, id_route), ...]

        data = []
        current_date = datetime.now()

        for id_trip, id_route in trip_route_map:
            # Получаем список станций для маршрута
            cursor.execute("""
                SELECT num_station FROM route_stations 
                WHERE id_route = %s ORDER BY num_station
            """, (id_route,))
            stations = [row[0] for row in cursor.fetchall()]

            if not stations:
                continue

            # Выбираем случайную дату до текущей
            base_time = current_date - timedelta(days=random.randint(1, 365))
            current_time = base_time

            for num_station in stations:
                arrival_time = current_time + timedelta(hours=6)
                real_arrival_time = arrival_time + timedelta(minutes=random.randint(0, 15))
                parking_time = f"{random.randint(10, 25)} minutes"

                data.append((id_trip, num_station, parking_time, arrival_time, real_arrival_time))
                current_time = arrival_time

        cursor.executemany(trip_times_sql, data)
        print("Корректные времена поездок добавлены.")
    except Exception as e:
        print(f"Ошибка при вставке времени поездок: {e}")


def fill_train_crew(cursor):
    train_crew_sql = """
        INSERT INTO train_crew (id_trip, id_worker) 
        VALUES (%s, %s)
    """
    workers_query = "SELECT passport_id FROM workers"
    trips_query = "SELECT id_trip FROM trips"

    try:
        cursor.execute(workers_query)
        workers = [row[0] for row in cursor.fetchall()]

        # Получаем список поездок
        cursor.execute(trips_query)
        trips = [row[0] for row in cursor.fetchall()]

        for trip_id in trips:
            brigade_size = random.randint(2, 5) 
            assigned_workers = set()

            while len(assigned_workers) < brigade_size:
                worker_id = random.choice(workers)
                assigned_workers.add(worker_id)

            for worker_id in assigned_workers:
                cursor.execute(train_crew_sql, (trip_id, worker_id))

            if trip_id % 1000 == 0:
                cursor.connection.commit()

        cursor.connection.commit()
        print("Таблица train_crew успешно заполнена.")

    except Exception as e:
        print(f"Ошибка при заполнении таблицы train_crew: {e}")
        cursor.connection.rollback() 


def fill_passenger_trips(cursor):
    passenger_trips_sql = """
        INSERT INTO passenger_trips (id_passenger, id_trip, departure_station, destination_station) 
        VALUES (%s, %s, %s, %s)
    """
    route_station_query = """
        SELECT rs.num_station, rs.id_station, rs.distance
        FROM trips t
        JOIN route_stations rs ON t.id_route = rs.id_route
        WHERE t.id_trip = %s
        ORDER BY rs.num_station
    """
    check_sql = """
        SELECT 1 FROM passenger_trips WHERE id_passenger = %s AND id_trip = %s
    """

    inserted = 0
    skipped = 0

    try:
        for i in range(1, 74999):  
            passenger_id = random.randint(1, 74999)
            trip_id = random.randint(1, 20000)

            # Проверка на уникальность
            cursor.execute(check_sql, (passenger_id, trip_id))
            if cursor.fetchone():
                skipped += 1
                continue

            # Получаем станции маршрута для поездки
            cursor.execute(route_station_query, (trip_id,))
            stations = cursor.fetchall()  # [(num_station, id_station, distance), ...]

            if len(stations) < 2:
                skipped += 1
                continue

            departure_index = random.randint(0, len(stations) - 2)
            destination_index = random.randint(departure_index + 1, len(stations) - 1)

            departure_station = stations[departure_index][1]  # id_station
            destination_station = stations[destination_index][1]

            cursor.execute(passenger_trips_sql, (passenger_id, trip_id, departure_station, destination_station))
            inserted += 1

            if i % 1000 == 0:
                cursor.connection.commit()
                print(f"{i} итераций: вставлено {inserted}, пропущено {skipped}")

        cursor.connection.commit()
        print(f"Готово. Всего вставлено: {inserted}, пропущено (дубли или ошибки): {skipped}")

    except Exception as e:
        print(f"Ошибка при заполнении таблицы passenger_trips: {e}")
        cursor.connection.rollback()


if __name__ == "__main__":
    fill_tables()
