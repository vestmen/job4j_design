create table writer(
    id serial primary key,
    name varchar(255)
);

create table book(
    id serial primary key,
    name varchar(255),
    writer_id int references writer(id)
);


 create table persons(
     id serial primary key,
     name varchar(255)
 );

 create table addresses(
     id serial primary key,
     name varchar(255)
 );

 create table persons_addresses(
     id serial primary key,
     person_id int references persons(id),
     address_id int references addresses(id)
 );


create table snils(
    id serial primary key,
    number int
);

create table persons(
    id serial primary key,
    name varchar(255),
    snils_id int references snils(id) unique
);