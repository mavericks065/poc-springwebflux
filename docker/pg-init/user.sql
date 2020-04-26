create table user_entity
(
    id           bigint not null
        constraint user_pkey
            primary key,
    created_date timestamp,
    email        text,
    firstname     text,
    lastname     text,
    preferred_name     text,
    phone_number        text
);

INSERT INTO user_entity (id, created_date, email, firstname, lastname, preferred_name, phone_number) VALUES (0, '2020-01-03 09:16:18.000000', 'test@email.com', 'T', 'Est', 'Mister T', '');
INSERT INTO user_entity (id, created_date, email, firstname, lastname, preferred_name, phone_number) VALUES (1, '2020-01-03 09:16:18.000000', 'nicolas@email.com', 'nicolas', 'guig', 'NIG', '0404040302');
INSERT INTO user_entity (id, created_date, email, firstname, lastname, preferred_name, phone_number) VALUES (2, '2020-01-04 09:16:18.000000', 'ilya@email.com', '', '', 'ILTR', '0445640302');
INSERT INTO user_entity (id, created_date, email, firstname, lastname, preferred_name, phone_number) VALUES (3, '2020-01-06 09:16:18.000000', 'nicolas_other@email.com', '', '', 'NPA', '0124040302');
INSERT INTO user_entity (id, created_date, email, firstname, lastname, preferred_name, phone_number) VALUES (4, '2020-01-03 09:16:18.000000', 'lyman@email.com', '', '', 'LYGI', '0404040123');
INSERT INTO user_entity (id, created_date, email, firstname, lastname, preferred_name, phone_number) VALUES (5, '2020-01-07 09:16:18.000000', 'miranda@email.com', '', '', '', '0404022202');
INSERT INTO user_entity (id, created_date, email, firstname, lastname, preferred_name, phone_number) VALUES (6, '2020-01-03 09:16:18.000000', 'surya@email.com', '', '', '', '0404040999');
INSERT INTO user_entity (id, created_date, email, firstname, lastname, preferred_name, phone_number) VALUES (7, '2020-01-09 09:16:18.000000', 'william@email.com', '', '', '', '0406640302');
INSERT INTO user_entity (id, created_date, email, firstname, lastname, preferred_name, phone_number) VALUES (8, '2020-01-10 09:16:18.000000', 'aditi@email.com', '', '', 'Aditai', '0404055302');
INSERT INTO user_entity (id, created_date, email, firstname, lastname, preferred_name, phone_number) VALUES (9, '2020-01-03 09:16:18.000000', 'mykyta@email.com', '', '', 'Nick', '');
INSERT INTO user_entity (id, created_date, email, firstname, lastname, preferred_name, phone_number) VALUES (10, '2020-01-03 09:16:18.000000', 'erwan@email.com', '', '', 'R1', '');
