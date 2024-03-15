--liquibase formatted sql
--changeset Adil:add_data

INSERT INTO users (name, surname, age, email, password, phone_number, avatar, acc_type)
VALUES
    ('David', 'Brown', 29, 'david.brown@example.com', 'newpassword123', '1112223333', 'david_avatar.jpg', 'employer'),
    ('Emma', 'Davis', 35, 'emma.davis@example.com', 'newpassword456', '4445556666', 'emma_avatar.png', 'employee'),
    ('Frank', 'Wilson', 45, 'frank.wilson@example.com', 'newpassword789', '7778889999', NULL, 'employer');

INSERT INTO contact_types (type)
VALUES ('Work Email'),
       ('Work Phone'),
       ('Work LinkedIn');

INSERT INTO categories (name)
VALUES ('IT'),
       ('Marketing'),
       ('Finance');

INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date,
                       update_date)
VALUES ('Software Developer', 'Exciting opportunity for software developers',
        (SELECT id FROM categories WHERE name = 'IT'), 80000, 5, 8, 1,
        (SELECT id FROM users WHERE name = 'David' AND surname = 'Brown'), NOW(), NOW()),
       ('Marketing Manager', 'Join our dynamic marketing team',
        (SELECT id FROM categories WHERE name = 'Marketing'), 70000, 4, 7, 1,
        (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Davis'), NOW(), NOW()),
       ('Financial Analyst', 'Lead financial analysis initiatives',
        (SELECT id FROM categories WHERE name = 'Finance'), 90000, 7, 10, 1,
        (SELECT id FROM users WHERE name = 'Frank' AND surname = 'Wilson'), NOW(), NOW());

INSERT INTO resumes (name, category_id, applicant_id, salary, is_active, created_date, update_date)
VALUES ('David Brown Resume', (SELECT id FROM categories WHERE name = 'IT'),
        (SELECT id FROM users WHERE name = 'David' AND surname = 'Brown'), 80000, 1, NOW(), NOW()),
       ('Emma Davis Resume', (SELECT id FROM categories WHERE name = 'Marketing'),
        (SELECT id FROM users WHERE name = 'Emma' AND surname = 'Davis'), 70000, 1, NOW(), NOW()),
       ('Frank Wilson Resume', (SELECT id FROM categories WHERE name = 'Finance'),
        (SELECT id FROM users WHERE name = 'Frank' AND surname = 'Wilson'), 90000, 1, NOW(), NOW());

INSERT INTO contacts_info (INFO_VALUE, type_id, resume_id)
VALUES ('david.brown@example.com', (SELECT id FROM contact_types WHERE type = 'Work Email'),
        (SELECT id FROM resumes WHERE name = 'David Brown Resume')),
       ('+1112223333', (SELECT id FROM contact_types WHERE type = 'Work Phone'),
        (SELECT id FROM resumes WHERE name = 'David Brown Resume')),
       ('https://www.linkedin.com/davidbrown', (SELECT id FROM contact_types WHERE type = 'Work LinkedIn'),
        (SELECT id FROM resumes WHERE name = 'David Brown Resume'));

INSERT INTO responded_applicants (vacancy_id, resume_id, confirmation)
VALUES ((SELECT id FROM vacancies WHERE name = 'Software Developer'),
        (SELECT id FROM resumes WHERE name = 'David Brown Resume'), 1),
       ((SELECT id FROM vacancies WHERE name = 'Marketing Manager'),
        (SELECT id FROM resumes WHERE name = 'Emma Davis Resume'), 1),
       ((SELECT id FROM vacancies WHERE name = 'Financial Analyst'),
        (SELECT id FROM resumes WHERE name = 'Frank Wilson Resume'), 0);

INSERT INTO education_info (institution, program, resume_id, start_date, end_date, degree)
VALUES ('Tech University', 'Computer Science', (SELECT id FROM resumes WHERE name = 'David Brown Resume'),
        '2005-09-01', '2009-06-01', 'Bachelor'),
       ('Marketing College', 'Marketing Management', (SELECT id FROM resumes WHERE name = 'Emma Davis Resume'),
        '2008-09-01', '2012-06-01', 'Bachelor'),
       ('Finance Institute', 'Finance and Economics', (SELECT id FROM resumes WHERE name = 'Frank Wilson Resume'),
        '1995-09-01', '1999-06-01', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES ((SELECT id FROM resumes WHERE name = 'David Brown Resume'), 8, 'Tech Innovations', 'Software Developer',
        'Developing software solutions for various platforms'),
       ((SELECT id FROM resumes WHERE name = 'Emma Davis Resume'), 10, 'Marketing Experts', 'Marketing Director',
        'Developing and executing marketing strategies'),
       ((SELECT id FROM resumes WHERE name = 'Frank Wilson Resume'), 15, 'Finance Group', 'Senior Financial Analyst',
        'Leading financial analysis projects and teams');