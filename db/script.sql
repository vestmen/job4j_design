create table exceptedStudents(
	id serial primary key,
	name text,
	age integer,
	excepted bool
);
insert into exceptedStudents (name, age, excepted) values ('Alex', 25, false);
update exceptedStudents set age = 28;
delete from exceptedStudents;
