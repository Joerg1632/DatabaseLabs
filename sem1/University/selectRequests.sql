--------------------------------------------------------------------1 
SELECT DISTINCT s.*
FROM student s
JOIN schedule sch ON sch.group_id = s.group_id
JOIN teacher t ON sch.teacher_id = t.teacher_id
WHERE t.surname LIKE 'А%' AND sch.year >= EXTRACT(YEAR FROM CURRENT_DATE) - 2;

------------------------------------------------------------------------2 

SELECT g.group_id, AVG(p.mark) AS average_mark  
FROM "group" g
JOIN student s ON g.group_id = s.group_id
JOIN performance p ON s.student_id = p.student_id
GROUP BY g.group_id;
-------------------------------------------------------------------------------3 --
SELECT 
    t.teacher_id, t.name, t.surname, 
    COUNT(DISTINCT p.student_id) AS excellent_students_count
FROM teacher t
JOIN schedule sch ON t.teacher_id = sch.teacher_id and (sch.class_type = 'seminar' OR sch.class_type = 'lab') 
JOIN performance p ON sch.subject_id = p.subject_id 
WHERE p.student_id NOT IN (
    SELECT p2.student_id
    FROM performance p2
    WHERE p2.subject_id = sch.subject_id
      AND p2.mark <> 5 
)
GROUP BY t.teacher_id, t.name, t.surname
HAVING COUNT(p.student_id) > 0; 


-----------------------------------------------------------------------4 
SELECT g.number AS number_group, g.faculty 
FROM "group" g
JOIN group_leader gl ON g.group_id = gl.group_id 
WHERE gl.leader_id IS NULL AND gl.change_data >= CURRENT_DATE - INTERVAL '1 year';

---------------------------------------------------------------------------5 

SELECT t.name, t.surname, COUNT(DISTINCT p.student_id) / 3 AS avg_students_per_year
FROM performance p
JOIN teacher t ON p.teacher_id = t.teacher_id
WHERE p.date >= (CURRENT_DATE - INTERVAL '3 years')
GROUP BY t.teacher_id;

-------------------------------------------------------------6 

SELECT t.teacher_id, t.name AS teacher_name, COUNT(s.entry_id) * 2 AS total_academic_hours 
FROM schedule s
JOIN teacher t ON s.teacher_id = t.teacher_id
WHERE s.year >= EXTRACT(YEAR FROM CURRENT_DATE) - 2 
GROUP BY t.teacher_id, t.name
ORDER BY total_academic_hours DESC
LIMIT 10;

-----------------------------------------------------------------7 

SELECT t.*, 
       (SELECT COUNT(*)
        FROM schedule s
        LEFT JOIN teacher_subject ts ON t.teacher_id = ts.teacher_id AND s.subject_id = ts.subject_id
        WHERE s.year = EXTRACT(YEAR FROM CURRENT_DATE) AND s.teacher_id = t.teacher_id AND ts.subject_id IS NULL) AS mismatch_count
FROM teacher t
ORDER BY mismatch_count DESC;

-----------------------------------------------8 
WITH ranked_groups AS (
    SELECT g.group_id, AVG(p.mark) AS average_mark,
           RANK() OVER (ORDER BY AVG(p.mark) DESC) AS rank
    FROM "group" g
    JOIN student s ON g.group_id = s.group_id
    JOIN performance p ON s.student_id = p.student_id
    GROUP BY g.group_id
)
SELECT group_id
FROM ranked_groups
WHERE rank = 1;


----------------------------------------------------------9 --

SELECT s.name, s.surname, s.patronymic, t.name AS teacher_name, t.surname AS teacher_surname, sch.semester
FROM student s
JOIN performance p ON s.student_id = p.student_id
JOIN teacher t ON t.teacher_id = p.teacher_id
JOIN schedule sch ON sch.subject_id = p.subject_id  
WHERE p.mark = 2 
GROUP BY s.student_id, EXTRACT(YEAR FROM p."date"),  sch.semester, EXTRACT(MONTH FROM p."date"), t.teacher_id
HAVING COUNT(DISTINCT CASE WHEN p.mark = 2 THEN t.teacher_id END) = 1;
