create table devices(
  id serial primary key,
  name varchar(255),
  price float
);

create table people(
  id serial primary key,
  name varchar(255)
);

create table devices_people(
  id serial primary key,
  device_id int references devices(id),
  people_id int references people(id)
);

insert into devices (name, price) values
  ('phone', 4500),
  ('tablet', 3500),
  ('notebook', 11500),
  ('eBook', 4200);

insert into people (name) values
  ('Oleg'), ('Marina'), ('Viсtor'),('Galina');

insert into devices_people (device_id, people_id) values
  (1, 1), (4, 1),
  (1, 2), (2, 2), (3, 2),
  (2, 3), (4, 3),
  (1, 4), (2, 4), (4, 4);

-- average device price
select avg(price) from devices;

-- average price of devices for each person
select p.name, avg(d.price) from devices_people as dp
  join people p on p.id = dp.people_id join devices as d
    on d.id = dp.device_id group by p.name;

-- average price of devices for each person, provided that
--  it must be more than 5000.
select p.name, avg(d.price) from devices_people as dp
  join people p on p.id = dp.people_id join devices as d
    on d.id = dp.device_id group by p.name having avg(d.price) > 5000 ;