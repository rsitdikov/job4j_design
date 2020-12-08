-- 1. Написать запрос получение всех продуктов с типом "СЫР"
select P.* from product as P
join type as T on
P.type_id = T.id
where T.name ~~ '%СЫР%';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product
where ~* '%мороженое%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product
where extract (month from expired_date) = extract (month from (current_date + interval '1 month'));

-- 4. Написать запрос, который выводит самый дорогой продукт.
select * from product
where price = (select max (price) from product);


-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
select T.name, count (P.type_id) from type as T
left outer join product as P
on T.id = P.type_id
group by T.name;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select P.* from product as P
join type as T on
P.type_id = T.id
where T.name like '%СЫР%' or T.name like '%МОЛОКО%';

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select T.name, count (P.type_id) from type as T
left outer join product as P
on T.id = P.type_id
group by T.name having count(P.type_id) < 10;

-- 8. Вывести все продукты и их тип.
select P.*, T.name as type_name from product as P
join type as T on
P.type_id = T.id;
