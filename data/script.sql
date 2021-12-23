create table students(
	id serial primary key,
	name varchar(255),
	age smallint,
	averageScore numeric(2, 1)
);

insert into students(name, age, averageScore)
values ('Ирина', 21, 4.2);

select * from students;

update students set averageScore = 4.7;

select * from students;

delete from students;

select * from students;