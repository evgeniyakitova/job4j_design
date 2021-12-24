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

insert into people(name) values ('Anna') , ('Petr'), ('Evgeniya');

insert into devices(name, price) values ('Notebook', 50000) , ('Headphones', 3000), ('Smart watch', 7000);

insert into devices_people(device_id, people_id) values (1, 1) , (1, 3), (2, 2), (3, 3);

select avg(price) from devices;

select p.name, avg(d.price)
from people as p join devices_people as dp
on p.id = dp.people_id
join devices as d on d.id = dp.device_id
group by p.name;

select p.name, avg(d.price)
from people as p join devices_people as dp
on p.id = dp.people_id
join devices as d on d.id = dp.device_id
group by p.name
having avg(d.price) > 5000;