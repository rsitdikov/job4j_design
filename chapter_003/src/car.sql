-- 1. Создать структуры данных в базе - таблицы Кузов, Двигатель, Коробка передач.
create table body (
id serial primary key,
type varchar(255)
);
create table engine (
id serial primary key,
type varchar(255)
);
create table transmission (
id serial primary key,
type varchar(255)
);
-- 2. Создать структуру Машина. Машина не может существовать без данных из п.1.
create table car (
id serial primary key,
model varchar(255),
body_id integer references body(id),
engine_id integer references engine(id),
transmission_id integer references transmission(id)
);
-- 3. Заполнить таблицы через insert. 
insert into body(type) values
('sedan'),
('station vagon'),
('hatchback'),
('minivan'),
('liftback'),
('pickup');
insert into engine(type) values
('gasoline'),
('diesel'),
('hybrid');
insert into transmission(type) values
('automatic'),
('manual'),
('robotic'),
('variator');
insert into car(model, body_id, engine_id, transmission_id) values
('Toyota Camry', 
(select id from body where type like 'sedan'),
(select id from engine where type like 'gasoline'),
(select id from transmission where type like 'automatic')
),
('Chevrolet Captiva', 
(select id from body where type like 'station vagon'),
(select id from engine where type like 'diesel'),
(select id from transmission where type like 'manual')
),
('Opel Astra', 
(select id from body where type like 'hatchback'),
(select id from engine where type like 'gasoline'),
(select id from transmission where type like 'automatic')
),
('Toyota Sienna 1.5 Dice', 
(select id from body where type like 'minivan'),
(select id from engine where type like 'gasoline'),
(select id from transmission where type like 'variator')
),
('Scoda Octavia', 
(select id from body where type like 'liftback'),
(select id from engine where type like 'gasoline'),
(select id from transmission where type like 'manual')
),
('Volkswagen Jetta', 
(select id from body where type like 'sedan'),
(select id from engine where type like 'gasoline'),
(select id from transmission where type like 'automatic')
),
('UAZ Patriot', 
(select id from body where type like 'station vagon'),
(select id from engine where type like 'diesel'),
(select id from transmission where type like 'manual')
);
-- 4. Вывести список всех машин и все привязанные к ним детали.
select c.model, b.type as "body", e.type as "engine", t.type as "transmission" from car c 
join body b on c.body_id = b.id 
join engine e on c.engine_id = e.id 
join transmission t on c.transmission_id = t.id;
-- 6. Вывести отдельно детали, которые не используются в машине: кузова, двигатели, коробки передач.
select b.type as "unused bodies" from body b left join car c on b.id = c.body_id where c.model is null;
select e.type as "not used engines" from engine e left join car c on e.id = c.engine_id where c.model is null; 
select t.type as "not used transmissions" from transmission t left join car c on t.id = c.transmission_id where c.model is null;