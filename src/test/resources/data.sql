INSERT INTO mentor (name)
VALUES ('Giovanni Bocacchio'),
       ('F. Scott Fitzgerald'),
       ('Marc'),
       ('Lucas'),
       ('Paul'),
       ('Matthew');

INSERT INTO project (description, project_name, team_name)
VALUES ('Biblhhe', 'asd', 'ASD1'),
       ('Bible', 'asd2', 'ASD2'),
       ('Bible', 'asd3', 'ASD3'),
       ('dgd', 'asd4', 'ASD34'),
       ('Bibbble', 'asd5', 'ASD4');

INSERT INTO projects_mentors(mentors_id, projects_id)
VALUES (1, 2),
       (1, 3),
       (2, 4),
       (2, 5),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1);

INSERT INTO student (age, course, name, project_id)
VALUES (21, 'Engineer', 'Matyi', 1),
       (49, 'Doctor', 'Attila', 1),
       (23, 'Teacher', 'Dóri', 2),
       (21, 'HR', 'Balázs', 3);

INSERT INTO student (age, course, name)
VALUES (23, 'Doctor', 'András');