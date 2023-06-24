create or replace function tax_after()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_after_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax_after();
	
create or replace function tax_before()
    returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.2;
		return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_before_trigger
    before insert
    on products
    for each row
    execute procedure tax_before();

create or replace function add_history_data()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date)
        values (new.name, new.price, current_date);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_history_trigger
    after insert on products
    for each row
    execute procedure add_history_data();
