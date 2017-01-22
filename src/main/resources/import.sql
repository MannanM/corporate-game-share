insert into user(id, name, login, password) values (1,'Roy','roy','$2a$10$mt55sk1mAc3iEyk7cwnwiuIY8.wImIEld97/ZDkEitJ/N4E9nMJfK');
insert into user(id, name, login, password) values (2,'Craig','craig','$2a$10$mt55sk1mAc3iEyk7cwnwiuIY8.wImIEld97/ZDkEitJ/N4E9nMJfK');
insert into user(id, name, login, password) values (3,'Greg','greg','$2a$10$mt55sk1mAc3iEyk7cwnwiuIY8.wImIEld97/ZDkEitJ/N4E9nMJfK');

insert into role(id, name) values (1,'ROLE_USER');
insert into role(id, name) values (2,'ROLE_ADMIN');
insert into role(id, name) values (3,'ROLE_GUEST');

insert into user_role(user_id, role_id) values (1,1);
insert into user_role(user_id, role_id) values (1,2);
insert into user_role(user_id, role_id) values (2,1);
insert into user_role(user_id, role_id) values (3,1);

INSERT INTO CONSOLE (console, developer, manufacturer) VALUES ('Playstation_4', 'Sony Computer Entertainment', 'Sony');
INSERT INTO CONSOLE (console, developer, manufacturer) VALUES ('Xbox_One', 'Microsoft', 'Flextronics');

INSERT INTO CONSOLE (console, developer, manufacturer) VALUES ('Playstation_4', 'Sony Computer Entertainment', 'Sony');
INSERT INTO CONSOLE (console, developer, manufacturer) VALUES ('Xbox_One', 'Microsoft', 'Flextronics');