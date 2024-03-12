--users
create table if not exists users
(
    id           integer auto_increment not null primary key,
    name         text                   not null,
    surname      text                   not null,
    age          integer                not null,
    email        text                   not null,
    password     text                   not null,
    phone_number varchar(55),
    avatar       text,
    account_type varchar(45)            not null
);

--resumes
create table if not exists resumes
(
    id           integer auto_increment not null primary key,
    applicant_id integer                not null,
    name         text                   not null,
    category_id  integer                not null,
    salary       double,
    is_active    boolean                not null default true,
    created_date date                   not null,
    update_time  date                   not null,
    foreign key (applicant_id) references users (id)
);


--vacancies
create table if not exists vacancies
(
    id           SERIAL primary key,
    name         TEXT    not null,
    description  text,
    category_id  integer not null,
    salary       double precision,
    exp_from     integer,
    exp_to       integer,
    is_active    boolean not null default true,
    author_id    integer,
    created_date date    not null,
    update_time  date    not null,
    foreign key (author_id) references users (id)
);


CREATE TABLE IF NOT EXISTS responded_applicant
(
    id           SERIAL PRIMARY KEY,
    applicant_id INTEGER NOT NULL,
    vacancy_id   INTEGER NOT NULL,
    CONSTRAINT fk_applicant FOREIGN KEY (applicant_id) REFERENCES users (id),
    CONSTRAINT fk_vacancy FOREIGN KEY (vacancy_id) REFERENCES vacancies (id)
);


CREATE TABLE IF NOT EXISTS сategory (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        parent_id BIGINT
);
