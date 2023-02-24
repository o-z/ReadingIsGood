create table CATEGORY
(
    ID          bigint auto_increment
        primary key,
    CREATE_DATE datetime     null,
    UPDATE_DATE datetime     null,
    DETAIL      varchar(200) null,
    NAME        varchar(50)  null
);
create table BOOK
(
    ID          bigint auto_increment
        primary key,
    CREATE_DATE datetime       null,
    UPDATE_DATE datetime       null,
    COUNT       bigint         null,
    DETAIL      varchar(200)   null,
    END_DATE    datetime       null,
    NAME        varchar(50)    null,
    PRICE       decimal(19, 2) null,
    STATUS      varchar(255)   null,
    CATEGORY_ID bigint         null,
    constraint FKprh5cdnlwefrxo30ausnijl3d
        foreign key (CATEGORY_ID) references CATEGORY (ID)
);

create table CUSTOMER
(
    ID          bigint auto_increment
        primary key,
    CREATE_DATE datetime     null,
    UPDATE_DATE datetime     null,
    EMAIL       varchar(255) null,
    FIRSTNAME   varchar(255) null,
    LASTNAME    varchar(255) null,
    PASSWORD    varchar(255) null,
    ROLE        varchar(255) null,
    constraint UKmk3cgvpjoy0vr5tbjwp5g13i1
        unique (EMAIL)
);

create table CUSTOMER_ADDRESS
(
    ID          bigint auto_increment
        primary key,
    CREATE_DATE datetime     null,
    UPDATE_DATE datetime     null,
    DETAIL      varchar(200) null,
    NAME        varchar(50)  null,
    CUSTOMER_ID  bigint         null
);

create table CUSTOMER_ORDER
(
    ID           bigint auto_increment
        primary key,
    CREATE_DATE  datetime       null,
    UPDATE_DATE  datetime       null,
    ADDRESS_ID   bigint         null,
    BOOK_ID      bigint         null,
    COUNT        bigint         null,
    CUSTOMER_ID  bigint         null,
    STATUS       varchar(255)   null,
    TOTAL_AMOUNT decimal(19, 2) null
);

create table STATISTIC
(
    ID          bigint auto_increment
        primary key,
    CREATE_DATE datetime null,
    UPDATE_DATE datetime null,
    BOOK_ID     bigint   null,
    COUNT       bigint   null,
    CUSTOMER_ID bigint   null
);


INSERT INTO GETIR.CATEGORY (ID, CREATE_DATE, UPDATE_DATE, DETAIL, NAME) VALUES (1, '2023-02-23 21:53:47', '2023-02-23 21:53:47', 'TARİH', 'TARİH');

INSERT INTO GETIR.BOOK (ID, CREATE_DATE, UPDATE_DATE, COUNT, DETAIL, END_DATE, NAME, PRICE, STATUS, CATEGORY_ID) VALUES (1, '2023-02-23 21:54:02', '2023-02-23 21:54:42', 8, 'Nutuk', '2023-02-23 18:53:10', 'Nutuk', 100.00, 'ACTIVE', 1);

INSERT INTO GETIR.CUSTOMER (ID, CREATE_DATE, UPDATE_DATE, EMAIL, FIRSTNAME, LASTNAME, PASSWORD, ROLE) VALUES (1, '2023-02-23 21:52:46', '2023-02-23 21:52:46', 'oguzzeyveli@gmail.com', 'oguz', 'zeyveli', '$2a$10$RsSKcFKuFRI1BGqqLN32wO572UxFCFNBLNLI5hU3AIBF8a58WwC02', 'USER');

INSERT INTO GETIR.CUSTOMER_ADDRESS (ID, CREATE_DATE, UPDATE_DATE, DETAIL, NAME,CUSTOMER_ID) VALUES (1, '2023-02-23 21:54:24', '2023-02-23 21:54:24', 'EV', 'EV',1);

INSERT INTO GETIR.CUSTOMER_ORDER (ID, CREATE_DATE, UPDATE_DATE, ADDRESS_ID, BOOK_ID, COUNT, CUSTOMER_ID, STATUS, TOTAL_AMOUNT) VALUES (1, '2023-02-23 21:54:42', '2023-02-23 21:54:42', 1, 1, 2, 1, 'SUCCESS', 200.00);

INSERT INTO GETIR.STATISTIC (ID, CREATE_DATE, UPDATE_DATE, BOOK_ID, COUNT, CUSTOMER_ID) VALUES (1, '2023-02-23 21:54:42', '2023-02-23 21:54:42', 1, 2, 1);






