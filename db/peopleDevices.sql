create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Ноутбук Asus', 7800);
insert into devices(name, price) values ('Macbook', 32800);
insert into devices(name, price) values ('Телефон Samsung', 12800);
insert into devices(name, price) values ('Iphone', 22800);
insert into devices(name, price) values ('Mi band', 1800);

insert into people(name) values ('Алексей');
insert into people(name) values ('Александр');
insert into people(name) values ('Василий');
insert into people(name) values ('Виктор');
insert into people(name) values ('Иван');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (1, 3);
insert into devices_people(device_id, people_id) values (1, 5);
insert into devices_people(device_id, people_id) values (2, 2);
insert into devices_people(device_id, people_id) values (2, 4);
insert into devices_people(device_id, people_id) values (3, 2);
insert into devices_people(device_id, people_id) values (3, 4);
insert into devices_people(device_id, people_id) values (4, 1);
insert into devices_people(device_id, people_id) values (4, 3);
insert into devices_people(device_id, people_id) values (4, 5);
insert into devices_people(device_id, people_id) values (5, 1);
insert into devices_people(device_id, people_id) values (5, 2);
insert into devices_people(device_id, people_id) values (5, 4);
insert into devices_people(device_id, people_id) values (5, 5);

select avg(price) from devices;

select p.name, avg(dev.price)
from people as p
join devices_people dp on p.id= dp.people_id
join devices as dev on dp.device_id = dev.id
group by p.name;

select p.name, avg(dev.price)
from people as p
join devices_people dp on p.id= dp.people_id
join devices as dev on dp.device_id = dev.id
group by p.name
having avg(dev.price) > 5000;

