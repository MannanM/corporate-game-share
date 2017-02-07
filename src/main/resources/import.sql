insert into users(id, name, login, password, organisation) values (nextval('users_id_seq'), 'Tom','tom@ex.com.au','$2a$10$mt55sk1mAc3iEyk7cwnwiuIY8.wImIEld97/ZDkEitJ/N4E9nMJfK', 'ex.com.au');
insert into users(id, name, login, password, organisation) values (nextval('users_id_seq'), 'Craig','craig@ex.com.au','$2a$10$mt55sk1mAc3iEyk7cwnwiuIY8.wImIEld97/ZDkEitJ/N4E9nMJfK', 'ex.com.au');
insert into users(id, name, login, password, organisation) values (nextval('users_id_seq'), 'Greg','greg@ex.com.au','$2a$10$mt55sk1mAc3iEyk7cwnwiuIY8.wImIEld97/ZDkEitJ/N4E9nMJfK', 'ex.com.au');

insert into role(id, name) values (nextval('role_id_seq'), 'ROLE_USER');

insert into user_role(user_id, role_id) values (1,1);
insert into user_role(user_id, role_id) values (2,1);
insert into user_role(user_id, role_id) values (3,1);

insert into console (id, name, short_name, wiki_name, developer, manufacturer) values (nextval('console_id_seq'), 'PlayStation 4', 'PS4', 'Playstation_4', 'Sony Computer Entertainment', 'Sony');
insert into console (id, name, short_name, wiki_name, developer, manufacturer) values (nextval('console_id_seq'), 'Xbox One', 'X1', 'Xbox_One', 'Microsoft', 'Flextronics');

insert into user_console (user_id, console_id) values (1, 1);
insert into user_console (user_id, console_id) values (1, 2);
insert into user_console (user_id, console_id) values (2, 1);
insert into user_console (user_id, console_id) values (3, 2);

insert into game (id, name, developer, publisher, exclusive, console_id) values (nextval('game_id_seq'), 'Destiny', 'Bungie', 'Activision', false, 1);
insert into game_genres (game_id, genres) values (1, 'action rpg');
insert into game_genres (game_id, genres) values (1, 'first person shooter');
insert into library (id, game_id, owner_id, state, created) values (nextval('library_id_seq'), 1, 1, 'AVAILABLE', CURRENT_TIMESTAMP);
insert into borrow (id, requester_id, library_id, state, start, finish, created) values (nextval('borrow_id_seq'), 2, 1, 'PENDING', '2017-01-03', '2017-01-28', CURRENT_TIMESTAMP);
