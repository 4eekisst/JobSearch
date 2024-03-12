INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type)
VALUES ('Илья', 'Беликов', 20, 'ilia@example.com', 'qwerty', '+1234567890', 'http://example.com/avatar.jpg',
        'standard'),
       ('Александр', 'Крайнюков', 20, 'alexander@example.com', 'qwerty', NULL, NULL, 'premium');


INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES (1, 'Software Engineer', 1, 60000, true, '2024-03-12', '2024-03-12'),
       (2, 'Marketing Specialist', 2, 45000, true, '2024-03-12', '2024-03-12');


INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date,
                       update_time)
VALUES ('Software Engineer', 'We are looking for a skilled software engineer to join our team.', 1, 80000, 2, 5, true,
        2, '2024-03-12', '2024-03-12'),
       ('Marketing Specialist', 'We are hiring a marketing specialist to manage our marketing campaigns.', 2, 60000, 3,
        7, true, 2, '2024-03-12', '2024-03-12');



INSERT INTO сategory (name, parent_id) VALUES ('IT', NULL);
INSERT INTO сategory (name, parent_id) VALUES ('Marketing', NULL);
