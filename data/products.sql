create table types(
    id serial primary key,
    name varchar(255)
);
create table products(
    id serial primary key,
    name varchar(255),
    expired_date date,
    price int,
    type_id int references types(id)
);

insert into types(name) values ('Молоко'), ('Сыр'), ('Конфеты');

insert into products(name, expired_date, price, type_id) values
('Давлеканово', date '2021-12-31', 45, 1),
('Домик в деревне', date '2021-12-25', 50, 1),
('Hochland', date '2022-02-17', 120, 2),
('President', date '2022-01-10', 145, 2),
('Chio - rio', date '2021-12-15', 200, 3);

select * from products p join types t
on t.id = p.type_id
where t.name = 'Сыр';

select * from products p join types t
on t.id = p.type_id
where p.name like '%мороженое%';

select * from products p join types t
on t.id = p.type_id
where p.expired_date < current_date;

select * from products p join types t
on t.id = p.type_id
where p.price = (select max(price) from products);

select t.name, count(*) from products p join types t
on t.id = p.type_id
group by t.name;

select * from products p join types t
on t.id = p.type_id
where t.name = 'Сыр' or t.name = 'Молоко';

select t.name, count(*) from products p join types t
on t.id = p.type_id
group by t.name
having count(*) < 10;

select p.name, t.name as type from products p join types t
on t.id = p.type_id;