create database staff;
create table staff(
  id serial primary key,
  name varchar(255),
  birthday date,
  salary money,
  employment boolean
);
insert into staff (name, birthday, salary, employment)
  values ('Ivanov Ivan', '1980-01-01', 49999.99, true);
select * from staff;
update staff set name = 'Ivanov Ivan';
delete from staff where birthday > '1979-12-31';