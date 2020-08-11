DELETE FROM cars;
DELETE FROM models;
DELETE FROM makers;
DELETE FROM users;

INSERT INTO users (id, email, last_visit, locale, name, userpic) values
('111111970190884860956',
 'riezenmark@gmail.com',
 '2020-08-11 11:48:31.818811',
 'ru', 'riezenmark',
 'https://lh5.googleusercontent.com/--_onv4zTak4/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucnSEw7JzdRo6jRlkImoO0ZLe8xpfg/photo.jpg');

INSERT INTO makers (id, name) values (1, 'Honda');

INSERT INTO models (id, name, maker_id) values (1, 'CR-V', 1);

INSERT INTO cars (id, engine_type, price, transmission, year_of_production, maker_id, model_id, user_id) values
(1, 'GASOLINE', 500000, 'AUTOMATIC', 2014, 1, 1, '111111970190884860956');
