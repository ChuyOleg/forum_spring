CREATE TABLE IF NOT EXISTS users (
    id serial PRIMARY KEY ,
    qwe varchar(255),
    password varchar(255),
    email varchar(255),
    role varchar(255),
    is_blocked boolean
);

CREATE TABLE IF NOT EXISTS topics (
    id serial PRIMARY KEY ,
    title varchar(255),
    category varchar(255),
    creation_date date,
    is_actual boolean,
    creator_id int references users(id)
);

CREATE TABLE IF NOT EXISTS posts (
    id serial PRIMARY KEY ,
    topic_id int references topics(id),
    creator_id int references users(id),
    text text,
    creation_date date
);
