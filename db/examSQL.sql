CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name) values (1, 'A');
insert into company (id, name) values (2, 'B');
insert into company (id, name) values (3, 'C');
insert into company (id, name) values (4, 'D');
insert into company (id, name) values (5, 'E');
insert into company (id, name) values (6, 'F');

insert into person (id, name, company_id) values (1, 'Алексей', 1);
insert into person (id, name, company_id) values (2, 'Александр', 1);
insert into person (id, name, company_id) values (3, 'Анатолий', 2);
insert into person (id, name, company_id) values (4, 'Богдан', 3);
insert into person (id, name, company_id) values (5, 'Василий', 3);
insert into person (id, name, company_id) values (6, 'Виктор', 4);
insert into person (id, name, company_id) values (7, 'Владимир', 5);
insert into person (id, name, company_id) values (8, 'Виталий', 5);
insert into person (id, name, company_id) values (9, 'Владислав', 1);
insert into person (id, name, company_id) values (10, 'Глеб', 2);
insert into person (id, name, company_id) values (11, 'Денис', 4);
insert into person (id, name, company_id) values (12, 'Петр', 4);

select person.name from person where company_id != 5;

select p.name as Сотрудник, c.name as Компания from person p
join company c on p.company_id = c.id;

select c.name as Компания, count(p) as "Количество сорудников"
from company c
join person p on p.company_id = c.id
group by c.name
having count(p) = (select max(a.cnt)
from (select count(p) as cnt
from company c
join person p on p.company_id = c.id
group by c.name) as a);