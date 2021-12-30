DROP TABLE IF EXISTS microsub;

CREATE TABLE microsub (
    microsub_id serial PRIMARY KEY,
    channel VARCHAR(255) NOT NULL,
    broadcaster_user_id VARCHAR(255) NOT NULL
);