insert into profiles (id, login, password, role) values
(1, "user1", "11111", 2),
(2, "user2", "22222", 2),
(3, "user3", "33333", 2),
(4, "user4", "44444", 2),
(5, "owner1", "11111", 1),
(6, "owner2", "22222", 1),
(7, "admin", "11111", 0);

insert into users (id, name, surname, phone) values
(1, "Pasha", "Mulkin", "375291878912"),
(2, "Gennadi", "Nicolaevich", "375296222263"),
(3, "Ludmila", "Algardovna", "375296459357"),
(4, "Vlad", "Radion", "375291456734"),
(5, "Pasha", "Struk", "375293434567"),
(6, "Slava", "Kalinin", "375292345655");

insert into homesteads (id, title, price, description, people_number,
                        owner_id) values
(1, "Dacha_1", 300, "description_1", 5, 5),
(2, "Dacha_2", 400, "description_2", 6, 5),
(3, "Dacha_3", 500, "description_3", 7, 6),
(4, "Dacha_4", 600, "description_4", 8, 6);

insert into orders (id, user_id, home_id, date_start, date_end) values
(1, 1, 2, "2019-06-15", "2019-06-25"),
(2, 2, 1, "2019-07-15", "2013-07-25"),
(3, 4, 3, "2019-08-15", "2019-08-25"),
(4, 4, 4, "2019-09-15", "2019-09-25");

insert into reviews (text, user_name, date_of_comment, home_id) values
("review1", "Pasha", "2000-11-11", 1),
("review2", "Gennadi", "2001-11-11", 2),
("review3", "Slava", "2002-11-11", 3),
("review1", "Ludmila", "2003-11-11", 4),
("review2", "Gennadi", "2004-11-11", 3),
("review3", "Slava", "2005-11-11", 2),
("review1", "Pasha", "2006-11-11", 3),
("review2", "Ludmila", "2007-11-11", 2);

insert into images (id, pathToImage, home_id) values
(1, "../img/1.1_farmstead.jpg", 1),
(2, "../img/1.3_farmstead.jpg", 1),
(3, "../img/2.0_farmstead.jpg", 2),
(4, "../img/2.1_farmstead.jpg", 2),
(5, "../img/2.2_farmstead.jpg", 2),
(6, "../img/2.3_farmstead.jpg", 2),
(7, "../img/3.0_farmstead.jpg", 3),
(8, "../img/3.1_farmstead.jpg", 3),
(9, "../img/3.2_farmstead.jpg", 3),
(10, "../img/4.0_farmstead.jpg", 4),
(11, "../img/4.1_farmstead.jpg", 4),
(12, "../img/4.2_farmstead.jpg", 4);



