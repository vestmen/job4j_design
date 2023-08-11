create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) VALUES ('product_2', 'producer_4', 8, 100);

create or replace function tax_statement()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_statement_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax_statement();

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 8, 200);

create or replace function tax_row()
    returns trigger as
$$
    BEGIN
        new.price = new.price * 1.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_row_trigger
    before insert
    on products
    for each row
    execute procedure tax_row();

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function history()
    returns trigger as
$$
    BEGIN
		insert into history_of_price(name, price, date)
		values
		(new.name,
		new.price,
		current_timestamp);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create or replace trigger history_trigger
    after insert on products
    for each row
    execute procedure history();

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 8, 50);

