insert into users (username, password, active)
values ('admin', '123', true);

insert into user_roles (user_id, authorities)
values (1, 'USER'),
       (1, 'ADMIN');

alter sequence users_id_seq restart with 2;
