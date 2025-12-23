-- Create Tables
-- ================================================================
-- Document
create table if not exists profiles
(
    id              uuid              not null default gen_random_uuid(),
    login           varchar(255)      not null,
    password        varchar(255)      not null,

    constraint profiles_pk primary key (id)
);