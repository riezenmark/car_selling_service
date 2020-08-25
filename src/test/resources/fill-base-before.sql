DELETE FROM cars;
DELETE FROM car_models;
DELETE FROM car_makers;
DELETE FROM user_roles;
DELETE FROM users;

alter sequence users_id_seq restart with 1;
alter sequence cars_id_seq restart with 1;
alter sequence car_makers_id_seq restart with 1;
alter sequence car_models_id_seq restart with 1;

INSERT INTO users (username, password, active) values
('admin', 123, true);
create extension if not exists pgcrypto;
update users set password = crypt(password, gen_salt('bf', 8));

INSERT INTO user_roles (user_id, authorities)
values (1, 'USER'),
       (1, 'ADMIN');

INSERT INTO car_makers (id, name) values (1, 'Honda');

INSERT INTO car_models (id, name, maker_id) values (1, 'CR-V', 1);

INSERT INTO cars (engine_type, price, transmission, year_of_production, maker_id, model_id, user_id) values
('GASOLINE', 500000, 'AUTOMATIC', 2014, 1, 1, 1);
