CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);
insert into customers(first_name, last_name, age, country) values ('Иван', 'Иванов', 25, 'Россия');
insert into customers(first_name, last_name, age, country) values ('Петр', 'Степанов', 23, 'Украина');
insert into customers(first_name, last_name, age, country) values ('Максим', 'Смирнов', 27, 'Беларусь');
insert into customers(first_name, last_name, age, country) values ('Екатерина', 'Петрова', 23, 'Россия');

SELECT first_name, last_name FROM customers WHERE age = (SELECT MIN(age) FROM customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount, customer_id) values (2500, 1);
insert into orders(amount, customer_id) values (1500, 1);
insert into orders(amount, customer_id) values (100, 3);
insert into orders(amount, customer_id) values (10000, 3);

SELECT first_name, last_name FROM customers WHERE customers.id NOT IN (SELECT customer_id FROM orders);