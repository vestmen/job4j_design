create table authors(
    id serial primary key,
    name text,
    birthday timestamp
);

create table books(
    id serial primary key,
    name text,
    pages int,
    author_id int references authors(id)
);

insert into authors(name, birthday)
values ('Александр Сергеевич Пушкин', '1799-06-06');
insert into authors(name, birthday)
values ('Михаил Юрьевич Лермонтов', '1814-10-15');

insert into books(name, pages, author_id)
values ('Герой нашего времени', 254, 2);
insert into books(name, pages, author_id)
values ('Бородино', 326, 2);
insert into books(name, pages, author_id)
values ('Руслан и Людмила', 486, 1);
insert into books(name, pages, author_id)
values ('Кавказский пленник', 526, 1);

select a.name, b.name
from authors as a join books as b on b.author_id = a.id;
select b.name, b.pages, a.name, a.birthday
from books as b join authors as a on b.author_id = a.id;
select b.name as "Название книги", b.pages as "Количество страниц", a.name as Автор
from books as b join authors as a on b.author_id = a.id;