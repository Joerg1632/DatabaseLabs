-- Active: 1729132147977@@localhost@5432@Sport
--1
SELECT a.athlete_id, a.name, a.surname, a.patronymic
FROM athletes a
JOIN results r ON a.athlete_id = r.athlete_id
JOIN competition_sports cs ON r.competition_id = cs.competition_id
JOIN sports s ON cs.sport_id = s.sport_id
WHERE s.sport_id IN (
    SELECT sport_id
    FROM competition_sports
    GROUP BY sport_id
    HAVING COUNT(competition_id) = 1
);
--2n
SELECT a.athlete_id, a.name, a.surname, s.name AS sport_title
FROM (
    SELECT r.athlete_id, cs.sport_id, 
           AVG(r.result) AS avg_result,
           ROW_NUMBER() OVER (PARTITION BY cs.sport_id ORDER BY AVG(r.result) ASC) AS list
    FROM results r
    JOIN competition_sports cs ON r.competition_id = cs.competition_id
    GROUP BY r.athlete_id, cs.sport_id
) AS ranked_athletes
JOIN athletes a ON ranked_athletes.athlete_id = a.athlete_id
JOIN sports s ON ranked_athletes.sport_id = s.sport_id
WHERE ranked_athletes.list = 1;


--6
WITH athletes_competitions AS (
    SELECT athletes.athlete_id, athletes.name, athletes.surname,
        EXTRACT(YEAR FROM competitions.competition_date) AS competition_year,
        EXTRACT(QUARTER FROM competitions.competition_date) AS competition_quarter,
        COUNT(results.competition_id) AS competition_count,
        SUM(results.prize_money) AS total_reward
    FROM athletes
    JOIN results ON athletes.athlete_id = results.athlete_id
    JOIN competitions ON results.competition_id = competitions.competition_id
    GROUP BY athletes.athlete_id, competition_year, competition_quarter
)
SELECT 
    athlete_id, name, surname, competition_year, competition_quarter, competition_count,
    SUM(total_reward) OVER (PARTITION BY athlete_id ORDER BY competition_year, competition_quarter) AS cumulative_reward
FROM athletes_competitions
ORDER BY athlete_id, competition_year, competition_quarter;


--3n
SELECT athletes.athlete_id, athletes.name, athletes.surname, competitions.season, sports.name AS title, 
       first_value(athletes.athlete_id) OVER (PARTITION BY sports.sport_id, competitions.season ORDER BY AVG(result) ASC) AS best_athlete_id,
       first_value(athletes.athlete_id) OVER (PARTITION BY sports.sport_id, competitions.season ORDER BY AVG(result) DESC) AS worst_athlete_id
FROM results
JOIN athletes ON results.athlete_id = athletes.athlete_id
JOIN competitions ON results.competition_id = competitions.competition_id
JOIN competition_sports ON competitions.competition_id = competition_sports.competition_id
JOIN sports ON competition_sports.sport_id = sports.sport_id
GROUP BY sports.sport_id, competitions.season, athletes.athlete_id;



--4
SELECT best_worst_for_each.athlete_id, best_worst_for_each.name, best_worst_for_each.surname, best_worst_for_each.title AS sport,         
    best_worst_for_each.id_best, best_athletes.name AS best_athlete_name, best_athletes.surname AS best_athlete_surname, 
    best_worst_for_each.id_worst, worst_athletes.name AS worst_athlete_name, worst_athletes.surname AS worst_athlete_surname
FROM (
    SELECT athletes.athlete_id, athletes.name, athletes.surname, sports.name AS title,
        first_value(athletes.athlete_id) OVER (PARTITION BY sports.sport_id ORDER BY AVG(result) ASC) AS id_best,
        first_value(athletes.athlete_id) OVER (PARTITION BY sports.sport_id ORDER BY AVG(result) DESC) AS id_worst
    FROM results
        JOIN athletes ON results.athlete_id = athletes.athlete_id
        JOIN competitions ON results.competition_id = competitions.competition_id
        JOIN competition_sports ON competitions.competition_id = competition_sports.competition_id
        JOIN sports ON competition_sports.sport_id = sports.sport_id
    GROUP BY athletes.athlete_id, sports.sport_id
) AS best_worst_for_each
JOIN athletes AS best_athletes ON best_worst_for_each.id_best = best_athletes.athlete_id
JOIN athletes AS worst_athletes ON best_worst_for_each.id_worst = worst_athletes.athlete_id;





--5 Поиск лучшего результата (максимальное место из всех соревнований) и вида спорта, на котором этот результат был достигнут, для каждого спортсмена (выборка по всем спортсменам).
SELECT athletes.athlete_id, athletes.name, athletes.surname, sports.name, MIN(result) , rank() over(partition by sports.sport_id order by results asc) AS best_result
FROM athletes JOIN results
ON athletes.athlete_id = results.athlete_id
JOIN competition_sports
ON results.competition_id = competition_sports.competition_id
JOIN sports
ON sports.sport_id = competition_sports.sport_id
GROUP BY athletes.athlete_id, sports.sport_id
ORDER BY 1;


Таблицы-
Поставщики(ПС, ПС название( поставщика))
Поставки(ID, ПС, ПР, ДАТА)
Продукты(ПР, ПР_Название, Цена)

Select DISTINCT ПС.название
FROM Поставщики 
JOIN Поставки ON Поставщики.ПС = Поставки.ПС
JOIN Продукты ON Продукты.ПР = Поставки.ПР
WHERE Продукты.цена = (
    Select max(ПР2.Цена)
    FROM Продукты ПР2
    WHERE ПР2.ПР_Название LIKE 'A%'
) AND Продукты.ПР_Название LIKE 'А%'


Таблица - Students(StudentId, Faculty, Surname, Name, Group)

Select S.*
FROM Students s1
JOIN Students s2 ON s1.Surname = s2.Surname AND s1.StudentId != s2.StudentId







