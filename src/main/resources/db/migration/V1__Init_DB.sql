create table car_makers
(
    id   serial              not null,
    name varchar(127) unique not null,
    primary key (id)
);

create table car_models
(
    id       bigserial    not null,
    name     varchar(255) not null,
    maker_id int4         not null,
    primary key (id)
);

create table cars
(
    id                 bigserial not null,
    engine_type        varchar(255),
    price              int4      not null,
    transmission       varchar(255),
    year_of_production int4      not null,
    maker_id           int4      not null,
    model_id           int8      not null,
    user_id            int8,
    primary key (id)
);

create table user_roles
(
    user_id     int8 not null,
    authorities varchar(255)
);

create table users
(
    id       bigserial    not null,
    active   boolean      not null,
    password varchar(255) not null,
    username varchar(50)  not null,
    primary key (id)
);

alter table if exists car_models
    add constraint models_makers_fk
        foreign key (maker_id) references car_makers;

alter table if exists cars
    add constraint cars_models_fk
        foreign key (model_id)
            references car_models
            on delete cascade;

alter table if exists cars
    add constraint cars_users_fk
        foreign key (user_id)
            references users
            on delete cascade;

alter table if exists user_roles
    add constraint roles_users_fk
        foreign key (user_id)
            references users;
