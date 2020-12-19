-- 1. Создать таблицы departaments, emploees и заполнить начальными данными
create table departaments (
id serial primary key,
name varchar(255)
);
create table emploees (
id serial primary key,
name varchar(255),
departament_id integer references departaments(id)
);
insert into departaments (name) values
('managment'),
('accounting'),
('IT'),
('transportation');
insert into emploees (name, departament_id) values
('Igor', 1),
('Marina', 2),
('Victor', 3),
('Alexander', null),
('Maxim', 4);

--2. Выполнить запросы с left, right, full, cross соединениями
select * from departaments d left join emploees e on d.id = e.departament_id;
select * from emploees e right join departaments d on d.id = e.departament_id;

select * from emploees e left join departaments d on d.id = e.departament_id;
select * from departaments d right join emploees e on d.id = e.departament_id;

select * from departaments d full join emploees e on d.id = e.departament_id;
select * from emploees e full join departaments d on d.id = e.departament_id;

select * from departaments cross join emploees;
select * from emploees cross join departaments;

--3. Используя left join найти сотрудников, которые не относятся ни к одному из департаментов 
select * from emploees e left join departaments d on d.id = e.departament_id where d.id is null;

--4. Используя left и right join написать запросы, которые давали бы одинаковый результат
select * from emploees e left join departaments d on d.id = e.departament_id where d.id is not null;
select * from departaments d right join emploees e on d.id = e.departament_id where d.id is not null;
select * from departaments d left join emploees e on d.id = e.departament_id;
select * from emploees e right join departaments d on d.id = e.departament_id;

--5. Создать таблицу teens c атрибутами name, gender и заполнить ее. Используя cross join, составить
-- все возможные разнополые пары
create table teens (
id serial primary key,
name varchar(255),
gender char(1)
);
select * from teens t1 cross join teens t2 where t1.gender ='M' and t1.gender <> t2.gender;




