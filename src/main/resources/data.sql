--users
create table if not exists users
(
  id integer auto_increment not null primary key,
  name text not null,
  surname text not null,
  age integer not null,
  email text not null,
  password text not null,
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


--vacancies
CREATE TABLE IF NOT EXISTS vacancies
(
    id SERIAL primary key,
    name TEXT not null,
    description text,
    category_id integer not null,
    salary DOUBLE PRECISION,
    exp_from INTEGER,
    exp_to INTEGER,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    author_id INTEGER,
    created_date DATE NOT NULL,
    update_time DATE NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users (id)
    );




INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type)
VALUES
    ('Илья', 'Беликов',20, 'ilia@example.com', 'qwerty', '+1234567890', 'http://example.com/avatar.jpg', 'standard'),
    ('Александр', 'Крайнюков', 20, 'alexander@example.com', 'qwerty', NULL, NULL, 'premium');


INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES
    (1, 'Software Engineer', 1, 60000, true, '2024-03-12', '2024-03-12'),
    (2, 'Marketing Specialist', 2, 45000, true, '2024-03-12', '2024-03-12');


INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
VALUES
    ('Software Engineer', 'We are looking for a skilled software engineer to join our team.', 1, 80000, 2, 5, true, 2, '2024-03-12', '2024-03-12'),
    ('Marketing Specialist', 'We are hiring a marketing specialist to manage our marketing campaigns.', 2, 60000, 3, 7, true, 2, '2024-03-12', '2024-03-12');