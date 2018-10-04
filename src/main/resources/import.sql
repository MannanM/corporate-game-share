insert into role(id, name) values (nextval('role_id_seq'), 'ROLE_USER');
insert into role(id, name) values (nextval('role_id_seq'), 'ROLE_ADMIN');
insert into role(id, name) values (nextval('role_id_seq'), 'ROLE_FACEBOOK');

insert into console (id, name, short_name, wiki_name, developer, manufacturer) values (nextval('console_id_seq'), 'PlayStation 4', 'PS4', 'Playstation_4', 'Sony Computer Entertainment', 'Sony');
insert into console (id, name, short_name, wiki_name, developer, manufacturer) values (nextval('console_id_seq'), 'Xbox One', 'X1', 'Xbox_One', 'Microsoft', 'Flextronics');

insert into game (id, name, developer, publisher, exclusive, console_id) values (nextval('game_id_seq'), 'Destiny', 'Bungie', 'Activision', false, 1);
insert into game_genres (game_id, genres) values (1, 'action rpg');
insert into game_genres (game_id, genres) values (1, 'first person shooter');

insert into users(id, name, auth_id, auth_provider, created_at, email, image_link, last_login, login_count) values (nextval('users_id_seq'), 'Mannan Mackie',	'10156598466866678',	'Facebook',	CURRENT_TIMESTAMP, 'whatabout@gmail.com',	'https://platform-lookaside.fbsbx.com/platform/profilepic/?asid=10156598466866678&height=50&width=50&ext=1541153709&hash=AeS6TfKdjE3aDWrN', CURRENT_TIMESTAMP, 10);

insert into user_role(user_id, role_id) values (1, 1);
insert into user_role(user_id, role_id) values (1, 2);
insert into user_role(user_id, role_id) values (1, 3);