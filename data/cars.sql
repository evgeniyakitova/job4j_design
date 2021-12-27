create table bodies(
    id serial primary key,
    name varchar(255)
);

create table engines(
    id serial primary key,
    name varchar(255)
);

create table gearboxes(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
	body_id int references bodies(id),
	engine_id int references engines(id),
	gearbox_id int references gearboxes(id)
);

insert into bodies(name) values ('Body1'), ('Body2'), ('Body3');

insert into engines(name) values ('Engine1'), ('Engine2'), ('Engine3');

insert into gearboxes(name) values ('Gearbox1'), ('Gearbox2'), ('Gearbox3');

insert into cars(name, body_id, engine_id, gearbox_id) values
('Toyota', 1, 1, 2),
('BMW', 2, 3, 2),
('Audi', 3, 1, 1);

select c.name as car, b.name as body, g.name as gearbox, en.name as engine
from cars c join bodies b on c.body_id = b.id
join gearboxes g on c.gearbox_id = g.id
join engines en on c.engine_id = en.id;

select b.name from bodies b left join cars c
on c.body_id = b.id
where c.body_id is null;

select en.name from engines en left join cars c
on c.engine_id = en.id
where c.engine_id is null;

select g.name from gearboxes g left join cars c
on c.gearbox_id = g.id
where c.gearbox_id is null;

