CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name)
values(1, 'Google'), (2, 'Amazon'),
(5, 'Apple');

insert into person(id, name, company_id)
values(1, 'Mike', 1), (2, 'Vika', 2),
(3, 'Zhenya', 5), (4, 'Kate', 2);

select p.name, c.name as company from
company as c join person as p
on c.id = p.company_id
where c.id <> 5;

with sample as (select c.name as company, count(*) from
company as c join person as p
on c.id = p.company_id
group by c.id)
select * from sample where
sample.count = (select max(sample.count) from sample);