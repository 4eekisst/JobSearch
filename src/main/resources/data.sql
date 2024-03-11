--users
create table if not exists users
(
  id integer auto_increment not null primary key,
  name text                 not null,
  surname text              not null,
  age integer               not null,
  email text                not null,
  password text             not null,
  phone_number varchar(55),
  avatar text,
  account_type varchar(45)  not null
);


INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type)
VALUES
    ('John', 'Doe', 30, 'john.doe@example.com', 'hashed_password', '+1234567890', 'http://example.com/avatar.jpg', 'standard'),
    ('Jane', 'Smith', 25, 'jane.smith@example.com', 'hashed_password', NULL, NULL, 'premium');

