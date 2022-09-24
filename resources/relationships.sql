-- many-to-one
create table vehicle (
  id serial primary key,
  name varchar(255)
);
create table passenger (
  id serial primary key,
  name varchar(255),
  id_vehicle int references vehicle(id)
);
insert into vehicle (name) values
  ('Bus'),
  ('Train'),
  ('Plane');
insert into passenger (name, id_vehicle) values
  ('Ivan', 1),
  ('Egor', 2),
  ('Oleg', 1),
  ('Stepan', 3);

-- many-to-many
create table cinema (
  id serial primary key,
  name varchar(255)
);
create table movie (
  id serial primary key,
  name varchar(255)
);
create table cinema_movie (
  id serial primary key,
  id_cinema int references cinema(id),
  id_movie int references movie(id)
);
insert into cinema (name) values
  ('cinema_1'),
  ('cinema_2'),
  ('cinema_3');
insert into movie (name) values
  ('movie_1'),
  ('movie_2'),
  ('movie_3');
insert into cinema_movie (id_cinema, id_movie) VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (2, 1),
  (2, 3),
  (3, 2);
-- one-to-one
create table country (
  id serial primary key,
  name varchar(255)
);
create table capital (
  id serial primary key,
  name varchar(255)
);
create table country_capital (
  id serial primary key,
  id_country int references country (id) unique,
  id_capital int references capital (id) unique
);
insert into country (name) values
  ('Germany'),
  ('Italy'),
  ('Ukraine'),
  ('Russia');
insert into capital (name) values
  ('Moscow'),
  ('Kiev'),
  ('Rome'),
  ('Berlin');
insert into country_capital (id_country, id_capital) values
  (1, 4),
  (2, 3),
  (3, 2),
  (4, 1);
