-- authorities
INSERT INTO authorities (id, role) VALUES (1, 'employee'), (2, 'employer');

-- users
INSERT INTO users (name, surname, age, email, password, phone_number, avatar, ENABLED, ROLE_ID)
VALUES
    ('David', 'Brown', 29, 'david.brown@example.com', '$2a$12$Bu3ARLNkNiNdoEIJ0DUSIuwQJrEarHLr52K3u/9FE.nZKzb9K3so2
', '1112223333', 'david_avatar.jpg', true, 2),
    ('Emma', 'Davis', 35, 'emma.davis@example.com', '$2a$12$yY2Q654/Th2zYAasJ.sAqOo.POTRVN7C2h58vaVxocTHJMEYhuCBa
', '4445556666', 'emma_avatar.png', true, 1),
    ('Frank', 'Wilson', 45, 'frank.wilson@example.com', '$2a$12$4CuQU6Ybk.VWYJ8R/wbCa.7.ENAt2kIKjavKMqTy8RB5JOx2.9ZXG
', '7778889999', 'frank_avatar.png', true, 1);

-- contact_types
INSERT INTO contact_types (type)
VALUES ('Work Email'),
       ('Work Phone'),
       ('Work LinkedIn');

-- categories
INSERT INTO categories (name)
VALUES ('IT'),
       ('Marketing'),
       ('Finance');

-- vacancies
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

-- resumes
INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('David Brown Resume', (SELECT id FROM categories WHERE name = 'IT' LIMIT 1),
        (SELECT id FROM users WHERE name = 'David' AND surname = 'Brown' LIMIT 1), 80000, true, NOW(), NOW()),
       ('Emma Davis Resume', (SELECT id FROM categories WHERE name = 'Marketing' LIMIT 1),
        (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Davis' LIMIT 1), 70000, true, NOW(), NOW()),
       ('Frank Wilson Resume', (SELECT id FROM categories WHERE name = 'Finance' LIMIT 1),
        (SELECT id FROM users WHERE name = 'Frank' AND surname = 'Wilson' LIMIT 1), 90000, true, NOW(), NOW());

-- contacts_info
INSERT INTO contacts_info (info_value, type_id, resume_id)
VALUES ('david.brown@example.com', (SELECT id FROM contact_types WHERE type = 'Work Email' LIMIT 1),
        (SELECT id FROM resumes WHERE name = 'David Brown Resume' LIMIT 1)),
       ('+1112223333', (SELECT id FROM contact_types WHERE type = 'Work Phone' LIMIT 1),
        (SELECT id FROM resumes WHERE name = 'David Brown Resume' LIMIT 1)),
       ('https://www.linkedin.com/davidbrown', (SELECT id FROM contact_types WHERE type = 'Work LinkedIn' LIMIT 1),
        (SELECT id FROM resumes WHERE name = 'David Brown Resume' LIMIT 1));

-- education_info
INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Tech University', 'Computer Science', (SELECT id FROM resumes WHERE name = 'David Brown Resume' LIMIT 1),
        '2005-09-01', '2009-06-01', 'Bachelor'),
       ('Marketing College', 'Marketing Management', (SELECT id FROM resumes WHERE name = 'Emma Davis Resume' LIMIT 1),
        '2008-09-01', '2012-06-01', 'Bachelor'),
       ('Finance Institute', 'Finance and Economics', (SELECT id FROM resumes WHERE name = 'Frank Wilson Resume' LIMIT 1),
        '1995-09-01', '1999-06-01', 'Bachelor');

-- work_experience_info
INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'David Brown Resume' LIMIT 1), 8, 'Tech Innovations', 'Software Developer',
        'Developing software solutions for various platforms'),
       ((SELECT id FROM resumes WHERE name = 'Emma Davis Resume' LIMIT 1), 10, 'Marketing Experts', 'Marketing Director',
        'Developing and executing marketing strategies'),
       ((SELECT id FROM resumes WHERE name = 'Frank Wilson Resume' LIMIT 1), 15, 'Finance Group', 'Senior Financial Analyst',
        'Leading financial analysis projects and teams');

-- user_authority for "employer"
INSERT INTO user_authority (EMAIL, authority_id)
SELECT u.EMAIL, a.id
FROM users u
         JOIN authorities a ON a.role = 'employer'
WHERE u.name IN ('David', 'Frank');

-- user_authority for "employee"
INSERT INTO user_authority (EMAIL, authority_id)
SELECT u.EMAIL, a.id
FROM users u
         JOIN authorities a ON a.role = 'employee'
WHERE u.name = 'Emma';
