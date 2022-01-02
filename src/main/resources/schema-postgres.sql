DROP TABLE IF EXISTS microsub;
DROP TABLE IF EXISTS twitch_user;

DROP TYPE IF EXISTS user_level CASCADE;

CREATE TYPE user_level as ENUM ('OWNER', 'PLEB');
CREATE CAST (character varying as user_level) WITH INOUT AS IMPLICIT;


CREATE TABLE microsub (
    microsub_id serial PRIMARY KEY,
    channel VARCHAR(255) NOT NULL,
    broadcaster_user_id VARCHAR(255) NOT NULL
);


CREATE TABLE twitch_user (
    user_id serial PRIMARY KEY,
    twitch_user_id VARCHAR(255) NOT NULL UNIQUE,
    user_level user_level NOT NULL
);

INSERT INTO twitch_user VALUES (0, '116672490', 'OWNER');