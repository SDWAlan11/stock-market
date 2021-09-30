create sequence hibernate_sequence start with 1 increment by 1;

    create table orders (
       id integer not null,
        created_on timestamp not null,
        price double,
        quantity integer,
        sense integer,
        symbol_id varchar(10),
        primary key (id)
    );

    create table quote (
       id integer not null,
        created_on timestamp not null,
        ask double,
        bid double,
        currency integer,
        last_price double,
        symbol_id varchar(10),
        primary key (id)
    );

    create table symbol (
       id varchar(10) not null,
        name varchar(255),
        primary key (id)
    );

    alter table orders
       add constraint FK6e9diu68jqyijxgobrmfhx9co
       foreign key (symbol_id);
       references symbol;

    alter table quote
       add constraint FKtafm2v81orih44smr9u1pgfpk
       foreign key (symbol_id)
       references symbol;