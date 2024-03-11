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

--resumes
create table if not exists resumes
(
    id integer auto_increment not null primary key,
    applicant_id integer not null,
    name text not null,
    category_id integer not null,
    salary double,
    is_active boolean not null default true,
    created_date date not null,
    update_time date not null,
    foreign key (applicant_id) references users (id)
    );



INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type)
VALUES
    ('Илья', 'Беликов',20, 'ilia@example.com', 'hashed_password', '+1234567890', 'http://example.com/avatar.jpg', 'standard'),
    ('Александр', 'Крайнюков', 20, 'alexander@example.com', 'hashed_password', NULL, NULL, 'premium');


INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES
    (1, 'Software Engineer', 1, 60000, true, '2024-03-12', '2024-03-12'),
    (2, 'Marketing Specialist', 2, 45000, true, '2024-03-12', '2024-03-12');
