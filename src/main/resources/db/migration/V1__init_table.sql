CREATE SCHEMA IF NOT EXISTS access_system;

CREATE TABLE access_system.pass
(
    id     bigserial,
    room_id bigint not null CHECK ( room_id > 0 ),
    user_id bigint not null unique CHECK ( user_id > 0 )
);
