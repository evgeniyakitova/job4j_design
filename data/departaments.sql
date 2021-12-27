create table departments(
    id serial primary key,
    name varchar(255)
);
create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('Finance'), ('IT'), ('Investigation');

insert into employees(name, department_id) values ('Irina', 1), ('Mariya', 2), ('Petr', 2);

select * from departments dep join employees emp
on dep.id = emp.department_id;

select * from departments dep left join employees emp
on dep.id = emp.department_id;

select * from departments dep right join employees emp
on dep.id = emp.department_id;

select * from departments dep full join employees emp
on dep.id = emp.department_id;

select * from departments dep cross join employees emp;

select emp.name, dep.name as departament
from departments dep right join employees emp
on dep.id = emp.department_id;

select emp.name, dep.name as departament
from employees emp left join departments dep
on dep.id = emp.department_id;

select dep.name from departments dep left join employees emp
on dep.id = emp.department_id
where emp.department_id is null;

create table teens(
  name varchar(255),
  gender varchar(10)
);

insert into teens(name, gender) values ('Irina', 'female'), ('Mariya', 'female'), ('Petr', 'male');

select * from teens t1 cross join teens t2
where t1.gender != t2.gender;