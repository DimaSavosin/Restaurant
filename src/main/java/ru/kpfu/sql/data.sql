create table users(
        id serial primary key,
        name varchar(100),
        email varchar(100) unique ,
        password varchar(100),
        role varchar(20) default 'user'

);
CREATE TABLE bookings (
        id SERIAL PRIMARY KEY,
        user_id INTEGER NOT NULL,
        table_id INTEGER NOT NULL,
        booking_date DATE NOT NULL,
        booking_time TIME NOT NULL,
        duration INTEGER NOT NULL DEFAULT 1,
        status VARCHAR(20) DEFAULT 'pending',
        FOREIGN KEY (user_id) REFERENCES users(id),
        FOREIGN KEY (table_id) REFERENCES tables(id)
);




create table tables(
        id serial primary key,
        table_number integer unique not null,
        seating_capacity integer,
        location varchar(100)
);

CREATE TABLE reviews (
            id SERIAL PRIMARY KEY,
            user_id INTEGER NOT NULL,
            rating INTEGER NOT NULL CHECK (rating >= 1 AND rating <= 5),
            comment TEXT,
            created_at TIMESTAMP DEFAULT (CURRENT_TIMESTAMP AT TIME ZONE 'Europe/Moscow'),
            FOREIGN KEY (user_id) REFERENCES users(id)
);



CREATE TABLE files (
        id SERIAL PRIMARY KEY,
        file_name VARCHAR(255) NOT NULL,
        file_path VARCHAR(255) NOT NULL


);

CREATE TABLE menu (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price INTEGER NOT NULL,
    file_id INTEGER NOT NULL ,
    FOREIGN KEY (file_id) REFERENCES files(id) ON DELETE SET NULL
);


CREATE TABLE user_favorite_tables (
    user_id INTEGER NOT NULL,
    table_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, table_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (table_id) REFERENCES tables(id) ON DELETE CASCADE
);