create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('Седан');
insert into car_bodies(name) values ('Хэтчбек');
insert into car_bodies(name) values ('Пикап');

insert into car_engines(name) values ('Двигатель 1');
insert into car_engines(name) values ('Двигатель 2');
insert into car_engines(name) values ('Двигатель 3');

insert into car_transmissions(name) values ('Механика');
insert into car_transmissions(name) values ('Автомат');
insert into car_transmissions(name) values ('Робот');

insert into cars(name, body_id, engine_id, transmission_id) values ('Лада', 1, null, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('BMV', 2, 3, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('Mercedes', 1, 2, 2);

select c.id, c.name as Автомобиль, b.name as Кузов, e.name as Двигатель, t.name as "Коробка передач"
from cars c left join car_bodies b on c.body_id = b.id
left join car_engines e on c.engine_id = e.id
left join car_transmissions t on c.transmission_id = t.id;

select b.name from car_bodies b left join cars c on c.body_id = b.id where c.name is null;
select e.name from car_engines e left join cars c on c.engine_id = e.id where c.name is null;
select t.name from car_transmissions t left join cars c on c.transmission_id = t.id where c.name is null;