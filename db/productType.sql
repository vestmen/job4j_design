create table type(
    id serial primary key,
    name text
);

product(id, name, type_id, expired_date, price)
create table product(
    id serial primary key,
    name text,
    type_id int references type(id),
    expired_date timestamp,
    price int
);

insert into type(name) values ('СЫР');
insert into type(name) values ('МОЛОКО');
insert into type(name) values ('МЯСО');

insert into product(name, type_id, expired_date, price) values ('Сыр плавленный', 1, '2023-09-01', 120);
insert into product(name, type_id, expired_date, price) values ('Сыр моцарелла', 1, '2023-06-01', 820);
insert into product(name, type_id, expired_date, price) values ('Клубничное мороженое', 2, '2023-09-21', 45);
insert into product(name, type_id, expired_date, price) values ('Шоколадное мороженое', 2, '2023-08-21', 40);
insert into product(name, type_id, expired_date, price) values ('Фисташковое мороженое', 2, '2023-08-21', 40);
insert into product(name, type_id, expired_date, price) values ('Сметана 10%', 2, '2023-10-21', 140);
insert into product(name, type_id, expired_date, price) values ('Сметана 25%', 2, '2023-10-21', 170);
insert into product(name, type_id, expired_date, price) values ('Творог обезжиреный', 2, '2023-07-29', 90);
insert into product(name, type_id, expired_date, price) values ('Творог 5%', 2, '2023-08-14', 110);
insert into product(name, type_id, expired_date, price) values ('Творог 9%', 2, '2023-08-14', 130);
insert into product(name, type_id, expired_date, price) values ('Молоко 9%', 2, '2023-08-29', 90);
insert into product(name, type_id, expired_date, price) values ('Молоко 20%', 2, '2023-08-29', 130);
insert into product(name, type_id, expired_date, price) values ('Свинина', 3, '2023-07-30', 390);
insert into product(name, type_id, expired_date, price) values ('Говядина', 3, '2023-08-30', 690);
insert into product(name, type_id, expired_date, price) values ('Индейка', 3, '2023-09-30', 490);
insert into product(name, type_id, expired_date, price) values ('Мраморная говядина', 3, '2023-09-30', 820);

select * from product
where type_id = 1;

select * from product
where name like '%мороженое%';

select * from product
where expired_date < current_date;

select * from product
where price=(select max(price) from product);

select t.name, count(p.id)
from type t
join product p
on p.type_id = t.id
group by t.name;

select * from product
where type_id = 1 or type_id = 2;

select t.name, count(p.id)
from type t
join product p
on p.type_id = t.id
group by t.name
having count(p.id) < 10;

select * from product p
join type t
on t.id = p.type_id;