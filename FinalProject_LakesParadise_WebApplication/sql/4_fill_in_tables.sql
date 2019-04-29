insert into profiles (id, login, password, role) values
(1, "pasha@gmail.com", "111", 1),
(2, "gena@gmail.com", "222", 1),
(3, "luda@gmail.com", "333", 2),
(4, "roma@gmail.com", "444", 0),
(5, "pasha1@gmail.com", "555", 2),
(6, "slava@gmail.com", "666", 2),
(7, "masha@gmail.com", "777", 2);

insert into users (id, name, surname, phone) values
(1, "Pasha", "Mulkin", "+375291878912"),
(2, "Gennadi", "Nicolaevich", "+375296222263"),
(3, "Ludmila", "Algardovna", "+375296459357"),
(5, "Pasha", "Struk", "+375293434567"),
(6, "Slava", "Kalinin", "+375292345655"),
(7, "Masha", "Petrushko", "+375291505789");

insert into homesteads (id, title, price, description, people_number, rating,
                        owner_id) values
(1, "Dacha_1", 300, "description_1", 5, 0, 1),
(2, "Dacha_2", 400, "description_2", 6, 0, 2),
(3, "Dacha_3", 500, "description_3", 7, 0, 2),
(4, "Dacha_4", 600, "description_4", 8, 0, 2);

insert into orders (id, user_id, home_id, date_start, date_end,
                    status_pay) values
(1, 3, 2, "2019-06-15", "2019-06-25", true),
(2, 7, 1, "2019-06-15", "2013-07-01", true),
(3, 6, 4, "2019-07-15", "2019-07-12", true);

insert into reviews (text, user_name, date_of_comment, home_id) values
("review1", "Pasha", "2000-11-11", 1),
("review2", "Masha", "2001-11-11", 2),
("review3", "Slava", "2002-11-11", 3),
("review1", "Ludmila", "2003-11-11", 4),
("review2", "Gennadi", "2004-11-11", 3),
("review3", "Slava", "2005-11-11", 2),
("review1", "Pasha", "2006-11-11", 3),
("review2", "Ludmila", "2007-11-11", 2);


