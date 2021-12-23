create table driving_licenses(
    id serial primary key,
    num int
);

create table people(
    id serial primary key,
    name varchar(255),
    license_id int references driving_licenses(id) unique
);