create table clients(
    id serial primary key,
    name varchar(255),
    gender varchar (255),
    age smallint
);

create table phones(
    id serial primary key,
    num bigint,
    client_id int references clients(id)
);

insert into clients(name, gender, age) values ('Anna', 'female', 27);
insert into clients(name, gender, age) values ('Dmitriy', 'male', 35);
insert into clients(name, gender, age) values ('Elena', 'female', 45);

insert into phones(num, client_id) values (89500933627, 1);
insert into phones(num, client_id) values (89500944570, 1);
insert into phones(num, client_id) values (89080937455, 2);
insert into phones(num, client_id) values (89523566723, 3);

select * from clients join phones
on clients.id = phones.client_id;

select * from clients as cl join phones as ph
on cl.id = ph.client_id
where cl.gender = 'female';

select clients.name as Имя, phones.num as Номер from clients join phones
on clients.id = phones.client_id
where clients.age < 40;