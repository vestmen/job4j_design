create table fauna(
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('Cat', 3650, null);
insert into fauna(name, avg_age, discovery_date)
values ('Horse', 11650, '1916-09-01');
insert into fauna(name, avg_age, discovery_date)
values ('Anchovy fish', 1500, '1932-09-01');
insert into fauna(name, avg_age, discovery_date)
values ('Bream fish', 1500, null);

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';