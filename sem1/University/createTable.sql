DROP TABLE IF EXISTS days_of_week, "group", student, group_leader, teacher, subject, teacher_subject, schedule, performance;

CREATE TABLE "group" (
    group_id SERIAL PRIMARY KEY,
    faculty TEXT,
    number INTEGER
);

CREATE TABLE student (
    student_id SERIAL PRIMARY KEY,
    group_id INTEGER,
    name TEXT,
    patronymic TEXT,
    surname TEXT,
    status TEXT CHECK (status IN ('active', 'inactive')),
    FOREIGN KEY (group_id) REFERENCES "group" (group_id)
);

CREATE TABLE group_leader (
    group_id INTEGER,
    leader_id INTEGER,
    change_data DATE,
    PRIMARY KEY (group_id, change_data),
    FOREIGN KEY (group_id) REFERENCES "group"(group_id),
    FOREIGN KEY (leader_id) REFERENCES student(student_id)
);

CREATE TABLE teacher (
    teacher_id SERIAL PRIMARY KEY,
    name TEXT,
    patronymic TEXT,
    surname TEXT,
    specialization VARCHAR(50)
);

CREATE TABLE subject (
    subject_id SERIAL PRIMARY KEY,
    name VARCHAR(20)
);

CREATE TABLE teacher_subject (
    subject_id INTEGER,
    teacher_id INTEGER,
    semester INTEGER,
    year INTEGER,
    PRIMARY KEY (subject_id, teacher_id, semester, year),
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id)
);

CREATE TABLE schedule (
    entry_id SERIAL PRIMARY KEY,
    class_type VARCHAR(10) NOT NULL CHECK (class_type IN ('lection', 'seminar', 'lab')),
    day_of_week CHAR(1), 
    group_id INTEGER,
    pair_id INTEGER,    
    semester CHAR(2),
    subject_id INTEGER, 
    teacher_id INTEGER, 
    year INTEGER,
    FOREIGN KEY (group_id) REFERENCES "group"(group_id), 
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id)
);

CREATE TABLE performance (
    student_id INTEGER,
    subject_id INTEGER,
    teacher_id INTEGER,
    mark INTEGER,
    date DATE,
    PRIMARY KEY (student_id, subject_id, date),
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id)
);
