-- Create Tables
-- ================================================================
-- Document
create table if not exists pets
(
    id              uuid              not null default gen_random_uuid(),
    name            varchar(255)      not null,
    strength        integer           not null,
    profile_id      uuid              not null,


    constraint pets_pk primary key (id),
    constraint pets_fk_profiles foreign key (profile_id) references profiles (id)
        on delete cascade
);