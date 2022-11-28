create table people (
  id serial primary key,
  firstName varchar(255),
  lastName varchar(255)
);
create table phone (
  id serial primary key,
  brand varchar(255),
  model varchar(255),
  number varchar(255),
  id_people int references people(id)
);
insert into people (firstName, lastName) values
  ('Ivan', 'Petrov'),
  ('Oleg', 'Vasiliv'),
  ('Vladimir', 'Sidorov');
insert into phone (brand, model, number, id_people) values
  ('Samsung', 'Galaxy S21 Ultra', '900-555-55-15', 4),
  ('Apple', 'Iphone 12 Pro', '901-123-45-67', 3),
  ('Huawei', 'Mate 40 Pro', '902-987-65-43', 2),
  ('Samsung', 'Galaxy A72', '901-123-45-68', 3),
  ('Realme', '8 Pro', '900-001-02-03', 1);

select pp.firstName, pp.lastName, p.number from people as pp join phone p
    on pp.id = p.id_people;
select pp.firstName as Имя, pp.lastName as Фамилия, p.brand as Телефон,
  p.number as Номер from people as pp inner join phone as p on pp.id = p.id_people;
select p.brand as Марка, p.model as Модель, pp.firstName as Имя,
       pp.lastName as Фамилия from people as pp join phone as p on pp.id = p.id_people;
