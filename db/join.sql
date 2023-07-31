create table departments(
    id serial primary key,
    name text
);

create table employees(
    id serial primary key,
    name text,
    department_id int references departments(id)
);

insert into departments(name) values ('Department 1');
insert into departments(name) values ('Department 2');
insert into departments(name) values ('Department 3');
insert into departments(name) values ('Department 4');
insert into departments(name) values ('Department 5');
insert into departments(name) values ('Department 6');

insert into employees(name, department_id) values ('Employee 1', 1);
insert into employees(name, department_id) values ('Employee 2', 1);
insert into employees(name, department_id) values ('Employee 3', 3);
insert into employees(name, department_id) values ('Employee 4', 3);
insert into employees(name, department_id) values ('Employee 5', 3);
insert into employees(name, department_id) values ('Employee 6', 4);
insert into employees(name, department_id) values ('Employee 7', 5);
insert into employees(name, department_id) values ('Employee 8', 5);
insert into employees(name, department_id) values ('Employee 9', null);

select * from employees e left join departments d on e.department_id = d.id;
select * from departments d right join employees e on e.department_id = d.id;
select * from employees e full join departments d on e.department_id = d.id;
select * from employees e cross join departments d;

select * from departments d left join employees e on e.department_id = d.id where e.department_id is null;

select e.name, d.name from employees e left join departments d on e.department_id = d.id;
select e.name, d.name from departments d right join employees e on e.department_id = d.id;

create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) values ('Alex', 'Men');
insert into teens(name, gender) values ('Igor', 'Men');
insert into teens(name, gender) values ('Petr', 'Men');
insert into teens(name, gender) values ('Ivan', 'Men');
insert into teens(name, gender) values ('Katya', 'Women');
insert into teens(name, gender) values ('Masha', 'Women');
insert into teens(name, gender) values ('Sveta', 'Women');
insert into teens(name, gender) values ('Polina', 'Women');

select t1.name, t2.name from teens t1 cross join teens t2 where t1.gender != t2.gender;

