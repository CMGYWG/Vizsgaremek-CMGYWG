drop table if exists mentor cascade;
drop table if exists project cascade;
drop table if exists projects_mentors cascade;
drop table if exists student cascade;

create table mentor (
                        id long generated by default as identity,
                        name varchar(255) not null,
                        primary key (id)
);

create table project (
                         id long generated by default as identity,
                         description varchar(255) not null,
                         project_name varchar(255) not null,
                         team_name varchar(255) not null,
                         primary key (id)
);

create table projects_mentors (
                                  mentors_id long not null,
                                  projects_id long not null
);

create table student (
                         id long generated by default as identity,
                         age integer not null,
                         course varchar(255) not null,
                         name varchar(255) not null,
                         project_id long,
                         primary key (id)
);

alter table if exists projects_mentors
    add constraint FK_projects_mentors_project
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