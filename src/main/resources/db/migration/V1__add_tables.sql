drop table if exists mentor;
drop table if exists project;
drop table if exists projects_mentors;
drop table if exists student;

create table mentor (
                        id   SERIAL PRIMARY KEY,
                        name varchar(255)
);

create table project (
                         id SERIAL PRIMARY KEY,
                         description varchar(255),
                         project_name varchar(255),
                         team_name varchar(255)
);

create table projects_mentors (
                                  mentors_id bigint not null,
                                  projects_id bigint not null
);

create table student (
                         id SERIAL PRIMARY KEY,
                         age integer not null,
                         course varchar(255) not null ,
                         name varchar(255) not null ,
                         project_id bigint
);

alter table if exists projects_mentors
    add constraint FK9_projects_mentors_project
        foreign key (projects_id)
            references project;

alter table if exists projects_mentors
    add constraint FK_projects_mentors_mentor
        foreign key (mentors_id)
            references mentor;

alter table if exists student
    add constraint FK_student_project
        foreign key (project_id)
            references project;