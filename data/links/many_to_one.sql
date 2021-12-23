create table clients(
    id serial primary key,
    name varchar(255)
);

create table phones(
    id serial primary key,
    num smallint,
    client_id int references clients(id)
);