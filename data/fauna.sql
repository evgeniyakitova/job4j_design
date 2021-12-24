create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values('tardigrada', 73000, date '1773-07-23');

insert into fauna(name, avg_age, discovery_date)
values('rhinopithecus_strykeri', 8000, date '2010-03-12');

insert into fauna(name, avg_age)
values('lucanus_cervus', 1820);

insert into fauna(name, avg_age, discovery_date)
values('swordfish', 3640, date '1758-04-28');

select * from fauna where name like '%fish%';

select * from fauna where avg_age between 10000 and 21000;

select * from fauna where discovery_date is null;

select * from fauna where extract(year from discovery_date) < '1950';