create database items_db;


create table state (
  state_code serial primary key,
  description varchar (31)
);
create table category (
  category_code serial primary key,
  description varchar (255)
);
create table role (
  role_code serial primary key,
  name varchar (31)
);
create table rules (
  rules_code serial primary key,
  description varchar (255)
);
create table role_rules (
  role_rules serial primary key,
  role_code integer references role (role_code),
  rules_code integer references rules (rules_code)
);
create table users (
  user_id serial primary key,
  role_code integer references role (role_code),
  name varchar (255) not null,
  login varchar (31) not null,
  password varchar (31),
  unique (name, login)
);
create table item (
  item_id serial primary key,
  user_id integer references users (user_id),
  state_code integer references state (state_code),
  category_code integer references category (category_code),
  date timestamp,
  description text
);
create table attaches (
  attach_id serial primary key,
  item_id integer references item (item_id),
  date timestamp,
  file varchar (255)
);
create table comments (
  comment_id serial primary key,
  item_id integer references item (item_id),
  user_id integer references users (user_id),
  date timestamp,
  content text
);


insert into state (description) values
  ('created'),
  ('accepted'),
  ('rejected'),
  ('in progress'),
  ('suspended'),
  ('completed');
insert into category (description) values
  ('paramount'),
  ('secondary'),
  ('tertiary');
insert into role (name) values
  ('customer'),
  ('executor'),
  ('administrator');
insert into rules (description) values
  ('create'),
  ('cancel'),
  ('accept'),
  ('reject'),
  ('delete'),
  ('suspend'),
  ('execute');
insert into role_rules (role_code, rules_code) values
  (
    (select role_code from role where name = 'customer'),
    (select rules_code from rules where description = 'create')
  ),
  (
    (select role_code from role where name = 'customer'),
    (select rules_code from rules where description = 'cancel')
  ),
  (
    (select role_code from role where name = 'executor'),
    (select rules_code from rules where description = 'suspend')
  ),
  (
    (select role_code from role where name = 'executor'),
    (select rules_code from rules where description = 'execute')
  ),
  (
    (select role_code from role where name = 'administrator'),
    (select rules_code from rules where description = 'accept')
  ),
  (
    (select role_code from role where name = 'administrator'),
    (select rules_code from rules where description = 'reject')
  ),
  (
    (select role_code from role where name = 'administrator'),
    (select rules_code from rules where description = 'delete')
  );
insert into users (role_code, name, login, password) values
  ((select role_code from role where name = 'customer'),
   'Ramil Sitdikov', 'ramil.sitdikov', '1111-2222');
insert into item (user_id, state_code, category_code, date, description) values
  ((select user_id from users where name = 'Ramil Sitdikov'),
   (select state_code from state where description = 'created'),
   (select category_code from category where description = 'secondary'),
   now(),
   'item 1'
  );
insert into attaches (item_id, date, file) values
  ((select item_id from item where description = 'item 1'),
   now(),
   'c:\projects\job4j_design\chapter_003\data\test.JPG'
  );
insert into comments (item_id, user_id, date, content) values
  ((select item_id from item where description = 'item 1'),
   (select user_id from users where name = 'Ramil Sitdikov'),
   now(),
   'comment 1'
  );