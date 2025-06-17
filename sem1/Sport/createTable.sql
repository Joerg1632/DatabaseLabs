DROP TABLE IF EXISTS athletes, sports, competitions, competition_sports, results;

CREATE TABLE sports (
    sport_id SERIAL PRIMARY KEY, 
    name VARCHAR(100) NOT NULL  
);

CREATE TABLE athletes (
    athlete_id SERIAL PRIMARY KEY,  
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL, 
    patronymic VARCHAR(50)
);

CREATE TABLE competitions (
    competition_id SERIAL PRIMARY KEY,  
    name VARCHAR(100) NOT NULL, 
    season VARCHAR(20) NOT NULL, 
    competition_date DATE NOT NULL
);

CREATE TABLE competition_sports (
    competition_id INTEGER NOT NULL, 
    sport_id INTEGER NOT NULL,
    PRIMARY KEY (competition_id, sport_id),
    FOREIGN KEY (competition_id) REFERENCES competitions(competition_id),
    FOREIGN KEY (sport_id) REFERENCES sports(sport_id)
);


CREATE TABLE results (
    athlete_id INTEGER NOT NULL,  
    competition_id INTEGER NOT NULL,
    result INTEGER NOT NULL,  
    prize_money DECIMAL(10, 2), 
    PRIMARY KEY (athlete_id, competition_id), 
    FOREIGN KEY (athlete_id) REFERENCES athletes(athlete_id),  
    FOREIGN KEY (competition_id) REFERENCES competitions(competition_id) 
);

