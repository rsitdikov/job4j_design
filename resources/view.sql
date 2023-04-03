create table person (
	id serial primary key,
	name varchar(50),
	birthday date
);
insert into person (name, birthday) values 
	('Ivan', '25.01.1979'),
	('Vladimir', '12.11.1985'),
	('Artem', '04.08.2001'),
	('Oleg', '22.05.1996');
create table employee (
	id serial primary key,
	id_person integer references person(id),
	name varchar(50),
	e_date date,
	salary integer
);
insert into employee (id_person, name, e_date, salary) values
	(1, 'engineer', '13.06.2016', 70000),
	(2, 'operator', '03.10.2019', 45000),
	(3, 'engineer', '09.12.2022', 60000),
	(4, 'operator', '01.02.2020', 49999);
create table bank (
	id serial primary key,
	name varchar(50)
);
insert into bank (name) values
	('Sberbank'),
	('Tinkov'),
	('Alfa');
create table account (
id serial primary key,
	id_employee integer references employee(id),
	id_bank integer references bank(id)
);
insert into account (id_employee, id_bank) values
	(1, 1),
	(2, 3),
	(3, 2),
	(4, 3);
create view selection_by_employees as
	select p.name, date_part('year', age(p.birthday)) as age, e.e_date as enrollment_date, e.salary, b.name as bank from person as p 
		join employee e on p.id = e.id_person
		join account a on e.id = a.id_employee
		join bank b on b.id = a.id_bank
		where (date_part('year', age(p.birthday)) >= 40 or e.e_date > '31.12.2019') and (b.name like 'Sberbank' or e.salary < 50000)
		order by e.e_date desc;
select * from selection_by_employees;
  
