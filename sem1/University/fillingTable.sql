INSERT INTO "group" (faculty, number) VALUES
('Факультет ИТ', 1),
('Факультет ИТ', 2),
('Факультет ИТ', 3),
('Факультет ИТ', 4);

INSERT INTO student (group_id, name, patronymic, surname, status) VALUES
(1, 'Иван', 'Иванович', 'Иванов', 'active'),
(1, 'Петр', 'Петрович', 'Петров', 'active'),
(1, 'Сергей', 'Сергеевич', 'Сергеев', 'active'),
(1, 'Алексей', 'Алексеевич', 'Алексеев', 'active'),
(1, 'Дмитрий', 'Дмитриевич', 'Дмитриев', 'active'),
(2, 'Анна', 'Алексеевна', 'Алексеева', 'active'),
(2, 'Ольга', 'Викторовна', 'Викторова', 'active'),
(2, 'Мария', 'Сергеевна', 'Сергеев', 'active'),
(2, 'Екатерина', 'Петровна', 'Петрова', 'active'),
(2, 'Татьяна', 'Ивановна', 'Иванова', 'active'),
(3, 'Виктор', 'Николаевич', 'Николаев', 'active'),
(3, 'Станислав', 'Олегович', 'Станиславов', 'active'),
(3, 'Ирина', 'Павловна', 'Павлова', 'active'),
(3, 'Ксения', 'Владимировна', 'Владимирова', 'active'),
(3, 'Светлана', 'Юрьевна', 'Юрьева', 'active'),
(4, 'Даниил', 'Григорьевич', 'Григорьев', 'active'),
(4, 'Артем', 'Станиславович', 'Станиславов', 'active'),
(4, 'Анастасия', 'Дмитриевна', 'Дмитриева', 'active'),
(4, 'Елизавета', 'Федоровна', 'Федорова', 'active'),
(4, 'Николай', 'Александрович', 'Александров', 'active');

INSERT INTO teacher (name, patronymic, surname, specialization) VALUES
('Станислав', 'Игоревич', 'Петров', 'Математика'),
('Елена', 'Михайловна', 'Адонина', 'Программирование'),
('Александр', 'Петрович', 'Иванов', 'Алгебра'),
('Мария', 'Валерьевна', 'Кузнецова', 'Физика'),
('Виктор', 'Сергеевич', 'Федоров', 'История');

INSERT INTO subject (name) VALUES
('Математика'),
('Программирование'),
('Алгебра'),
('Физика'),
('История');

INSERT INTO teacher_subject (semester, subject_id, teacher_id, year) VALUES
(1, 1, 1, 2024),
(1, 2, 2, 2024),
(1, 5, 2, 2024),
(1, 3, 3, 2024),
(1, 4, 4, 2024),
(1, 5, 5, 2024);

INSERT INTO schedule (class_type, day_of_week, group_id, pair_id, semester, subject_id, teacher_id, year) VALUES
('lection', '1', 1, 1, '3', 5, 2, 2024),
('lection', '1', 1, 1, '3', 1, 2, 2024),
('seminar', '1', 1, 2, '3', 2, 2, 2024),
('lab', '2', 1, 3, '3', 3, 2, 2024),
('lection', '3', 2, 4, '3', 4, 3, 2024),
('seminar', '3', 2, 5, '2', 5, 5, 2024),
('lab', '4', 2, 1, '2', 1, 1, 2024),
('lection', '4', 3, 2, '2', 2, 2, 2024),
('seminar', '5', 3, 3, '2', 3, 3, 2024),
('lab', '5', 4, 4, '2', 4, 4, 2024),
('lection', '6', 4, 5, '2', 5, 5, 2024),
('seminar', '7', 4, 1, '3', 1, 1, 2024),
('lection', '1', 1, 1, '2', 1, 1, 2023),
('seminar', '1', 1, 2, '2', 2, 2, 2023),
('lab', '2', 1, 3, '2', 3, 3, 2023),
('lection', '3', 2, 4, '2', 4, 4, 2023),
('seminar', '3', 2, 5, '2', 5, 1, 2023),
('lab', '4', 2, 1, '1', 2, 1, 2023),
('lection', '4', 3, 2, '1', 2, 2, 2023),
('seminar', '5', 3, 3, '1', 3, 1, 2023),
('lab', '5', 4, 4, '1', 4, 4, 2023),
('lection', '6', 4, 5, '1', 5, 5, 2023),
('seminar', '7', 4, 1, '1', 1, 1, 2023);

INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-05-15', 4, 1, 1, 1);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-05-16', 4, 2, 1, 1);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-05-17', 3, 3, 1, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-05-18', 2, 4, 1, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-05-19', 5, 4, 1, 1);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-05-20', 4, 6, 2, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-05-21', 3, 8, 2, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-05-22', 5, 7, 2, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-05-23', 2, 9, 2, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-05-24', 4, 11, 2, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-06-01', 5, 13, 3, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-06-02', 4, 10, 3, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-06-03', 3, 14, 3, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-06-04', 5, 12, 3, 5);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-06-05', 2, 15, 3, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-06-10', 5, 16, 4, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-06-11', 5, 17, 4, 4);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-06-12', 4, 18, 4, 4);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-06-13', 3, 19, 4, 4);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2023-06-14', 2, 20, 5, 5);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-10', 3, 1, 1, 1);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-11', 3, 2, 1, 1);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-12', 2, 4, 1, 1);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-13', 4, 5, 1, 1);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-14', 5, 6, 1, 1);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-15', 3, 7, 2, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-16', 4, 8, 2, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-17', 5, 3, 2, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-18', 3, 9, 2, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-19', 5, 10, 2, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-20', 5, 12, 3, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-21', 4, 11, 3, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-22', 3, 13, 3, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-23', 4, 14, 3, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-24', 5, 15, 3, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-25', 5, 16, 4, 4);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-26', 2, 17, 4, 4);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-27', 3, 18, 4, 4);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-28', 4, 20, 4, 4);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-01-29', 5, 19, 5, 5);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-01', 5, 1, 1, 1);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-02', 2, 2, 1, 1);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-03', 3, 3, 1, 1);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-04', 4, 4, 1, 1);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-05', 5, 5, 1, 1);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-06', 4, 7, 2, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-07', 3, 6, 2, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-08', 5, 8, 2, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-09', 4, 9, 2, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-10', 2, 10, 2, 2);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-11', 5, 11, 3, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-12', 3, 12, 3, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-13', 4, 13, 3, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-14', 5, 14, 3, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-15', 4, 15, 3, 3);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-16', 5, 18, 4, 1);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-17', 3, 16, 4, 4);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-18', 4, 17, 4, 4);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-19', 2, 19, 4, 4);
INSERT INTO performance (date, mark, student_id, subject_id, teacher_id) VALUES ('2024-06-20', 5, 20, 5, 5);



INSERT INTO group_leader (change_data, group_id, leader_id) VALUES
('2023-01-15', 1, 1),  
('2023-02-01', 2, 7),  
('2023-09-20', 3, NULL),  
('2024-01-01', 4, 17), 
('2024-04-01', 1, NULL), 
('2024-05-01', 2, 6),  
('2024-05-03', 3, 13),
('2024-05-04', 4, 17);
