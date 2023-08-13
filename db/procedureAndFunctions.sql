delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);
select f_insert_data('product_3', 'producer_3', 0, 115);
select f_insert_data('product_2', 'producer_2', 5, 32);

create or replace procedure delete_data_p()
language 'plpgsql'
as $$
    BEGIN
        delete from products where count = 0;
    END;
$$;

call delete_data_p();

create or replace function f_delete_data(u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        delete from products where id = u_id;
        select into result sum(price) from products;
        return result;
    end;
$$;

select f_delete_data(1);