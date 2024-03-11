create table if not exists users
(
  id int auto_increment primary key,

    email varchar(255),
    phone_number varchar(20),
    adress varchar(255)
);

insert into users(email, phone_number, adress)
values('john@gmail.com', '66666', 'krupskaya'),
      ('jane@gmail.com', '555555', 'mirnaya');

