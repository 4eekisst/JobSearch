-- Вставка данных пользователей
INSERT INTO authorities (id, role) VALUES (1, 'employee'), (2, 'employer');

INSERT INTO users (name, surname, age, email, password, phone_number, avatar, ENABLED, ROLE_ID)
VALUES
    ('David', 'Brown', 29, 'david.brown@example.com', 'newpassword123', '1112223333', 'david_avatar.jpg', true, 2),
    ('Emma', 'Davis', 35, 'emma.davis@example.com', 'newpassword456', '4445556666', 'emma_avatar.png', true, 1),
    ('Frank', 'Wilson', 45, 'frank.wilson@example.com', 'newpassword789', '7778889999', 'frank_avatar.png', true, 1);

-- Вставка данных контактов
INSERT INTO contact_types (type)
VALUES ('Work Email'),
       ('Work Phone'),
       ('Work LinkedIn');

-- Вставка данных категорий
INSERT INTO categories (name)
VALUES ('IT'),
       ('Marketing'),
       ('Finance');

-- Вставка данных вакансий
INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_date)
VALUES ('Software Developer', 'Exciting opportunity for software developers',
        (SELECT id FROM categories WHERE name = 'IT' LIMIT 1), 80000, 5, 8, true,
        (SELECT id FROM users WHERE name = 'David' AND surname = 'Brown' LIMIT 1), NOW(), NOW()),
       ('Marketing Manager', 'Join our dynamic marketing team',
        (SELECT id FROM categories WHERE name = 'Marketing' LIMIT 1), 70000, 4, 7, true,
        (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Davis' LIMIT 1), NOW(), NOW()),
       ('Financial Analyst', 'Lead financial analysis initiatives',
        (SELECT id FROM categories WHERE name = 'Finance' LIMIT 1), 90000, 7, 10, true,
        (SELECT id FROM users WHERE name = 'Frank' AND surname = 'Wilson' LIMIT 1), NOW(), NOW());

-- Вставка данных резюме
INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('David Brown Resume', (SELECT id FROM categories WHERE name = 'IT' LIMIT 1),
        (SELECT id FROM users WHERE name = 'David' AND surname = 'Brown' LIMIT 1), 80000, true, NOW(), NOW()),
       ('Emma Davis Resume', (SELECT id FROM categories WHERE name = 'Marketing' LIMIT 1),
        (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Davis' LIMIT 1), 70000, true, NOW(), NOW()),
       ('Frank Wilson Resume', (SELECT id FROM categories WHERE name = 'Finance' LIMIT 1),
        (SELECT id FROM users WHERE name = 'Frank' AND surname = 'Wilson' LIMIT 1), 90000, true, NOW(), NOW());

-- Вставка данных контактной информации
INSERT INTO contacts_info (info_value, type_id, resume_id)
VALUES ('david.brown@example.com', (SELECT id FROM contact_types WHERE type = 'Work Email' LIMIT 1),
        (SELECT id FROM resumes WHERE name = 'David Brown Resume' LIMIT 1)),
       ('+1112223333', (SELECT id FROM contact_types WHERE type = 'Work Phone' LIMIT 1),
        (SELECT id FROM resumes WHERE name = 'David Brown Resume' LIMIT 1)),
       ('https://www.linkedin.com/davidbrown', (SELECT id FROM contact_types WHERE type = 'Work LinkedIn' LIMIT 1),
        (SELECT id FROM resumes WHERE name = 'David Brown Resume' LIMIT 1));

-- Вставка данных об образовании
INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Tech University', 'Computer Science', (SELECT id FROM resumes WHERE name = 'David Brown Resume' LIMIT 1),
        '2005-09-01', '2009-06-01', 'Bachelor'),
       ('Marketing College', 'Marketing Management', (SELECT id FROM resumes WHERE name = 'Emma Davis Resume' LIMIT 1),
        '2008-09-01', '2012-06-01', 'Bachelor'),
       ('Finance Institute', 'Finance and Economics', (SELECT id FROM resumes WHERE name = 'Frank Wilson Resume' LIMIT 1),
        '1995-09-01', '1999-06-01', 'Bachelor');

-- Вставка данных об опыте работы
INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'David Brown Resume' LIMIT 1), 8, 'Tech Innovations', 'Software Developer',
        'Developing software solutions for various platforms'),
       ((SELECT id FROM resumes WHERE name = 'Emma Davis Resume' LIMIT 1), 10, 'Marketing Experts', 'Marketing Director',
        'Developing and executing marketing strategies'),
       ((SELECT id FROM resumes WHERE name = 'Frank Wilson Resume' LIMIT 1), 15, 'Finance Group', 'Senior Financial Analyst',
        'Leading financial analysis projects and teams');

-- Вставка ролей в таблицу authorities

-- Вставка данных в таблицу user_authority для пользователей с ролями "employer"
INSERT INTO user_authority (user_id, authority_id)
SELECT u.id, a.id
FROM users u
         JOIN authorities a ON a.role = 'employer'
WHERE u.name IN ('David', 'Frank');

-- Вставка данных в таблицу user_authority для пользователей с ролью "employee"
INSERT INTO user_authority (user_id, authority_id)
SELECT u.id, a.id
FROM users u
         JOIN authorities a ON a.role = 'employee'
WHERE u.name = 'Emma';
