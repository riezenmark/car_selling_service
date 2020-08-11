create table cars (
    id int8,
    engine_type varchar(63),
    filename varchar(255),
    price int4 not null,
    transmission varchar(63),
    year_of_production int4 not null,
    maker_id int4 not null,
    model_id int8 not null,
    user_id varchar(255),
    primary key (id)
);

create table makers (
    id int4,
    name varchar(63) unique not null,
    primary key (id)
);

create table models (
    id int8,
    name varchar(63) not null,
    maker_id int4 not null,
    primary key (id)
);

create table users (
    id varchar(255) not null,
    email varchar(255) not null,
    gender varchar(255),
    last_visit timestamp,
    locale varchar(255),
    name varchar(255) not null,
    userpic varchar(255),
    primary key (id)
);

alter table if exists cars
    add constraint cars_makers_fk
    foreign key (maker_id) references makers;

alter table if exists cars
    add constraint cars_models_fk
    foreign key (model_id) references models;

alter table if exists cars
    add constraint cars_users_fk
    foreign key (user_id) references users;

alter table if exists models
    add constraint models_makers_fk
    foreign key (maker_id) references makers;
