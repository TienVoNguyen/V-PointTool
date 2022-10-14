create table category
(
    id            bigint auto_increment
        primary key,
    create_by     varchar(255) null,
    create_date   varchar(255) null,
    modified_by   varchar(255) null,
    modified_date datetime(6)  null,
    name          varchar(100) null
);

create table department
(
    id            bigint auto_increment
        primary key,
    create_by     varchar(255) null,
    create_date   varchar(255) null,
    modified_by   varchar(255) null,
    modified_date datetime(6)  null,
    name          varchar(50)  null
);

create table item
(
    id            bigint auto_increment
        primary key,
    create_by     varchar(255) null,
    create_date   varchar(255) null,
    modified_by   varchar(255) null,
    modified_date datetime(6)  null,
    name          varchar(100) null,
    category_id   bigint       null,
    constraint FK2n9w8d0dp4bsfra9dcg0046l4
        foreign key (category_id) references category (id)
);

create table role
(
    id            bigint auto_increment
        primary key,
    create_by     varchar(255) null,
    create_date   varchar(255) null,
    modified_by   varchar(255) null,
    modified_date datetime(6)  null,
    name          varchar(50)  null
);

create table symbol
(
    id            bigint auto_increment
        primary key,
    create_by     varchar(255) null,
    create_date   varchar(255) null,
    modified_by   varchar(255) null,
    modified_date datetime(6)  null,
    comparison    bit          null,
    description   longtext     null,
    end           float        null,
    name          varchar(50)  null,
    point         float        null,
    start         float        null
);

create table item_symbol
(
    item_id   bigint not null,
    symbol_id bigint not null,
    primary key (item_id, symbol_id),
    constraint FK1j0hyhn1d1r2b00jdf506b7lx
        foreign key (item_id) references item (id),
    constraint FKg5hsn7s9iicefnjtpicmmlhaw
        foreign key (symbol_id) references symbol (id)
);

create table users
(
    id            bigint auto_increment
        primary key,
    create_by     varchar(255) null,
    create_date   varchar(255) null,
    modified_by   varchar(255) null,
    modified_date datetime(6)  null,
    full_name     varchar(50)  null,
    password      varchar(150) null,
    staff_id      varchar(120) null,
    department_id bigint       null,
    constraint FKfi832e3qv89fq376fuh8920y4
        foreign key (department_id) references department (id)
);

create table mark
(
    id            bigint auto_increment
        primary key,
    create_by     varchar(255) null,
    create_date   varchar(255) null,
    modified_by   varchar(255) null,
    modified_date datetime(6)  null,
    point         float        null,
    item_id       bigint       null,
    symbol_id     bigint       null,
    user_id       bigint       null,
    constraint FKcghew8urk27qmcq39g02itrm
        foreign key (item_id) references item (id),
    constraint FKtij84kvg8qb72vklfep6ibgaf
        foreign key (symbol_id) references symbol (id),
    constraint FKtkioe39v18a3xggp01op1r9iu
        foreign key (user_id) references users (id)
);

create table user_role
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FKa68196081fvovjhkek5m97n3y
        foreign key (role_id) references role (id),
    constraint FKj345gk1bovqvfame88rcx7yyx
        foreign key (user_id) references users (id)
);


