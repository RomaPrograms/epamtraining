insert into owners (id, name, surname, phone) values
(1, "Nikita", "Vushev", "+375291234354"),
(2, "Sergey", "Kovaleuski", "+375295674321"),
(3, "Luba", "Agarova", "+375298987656"),
(4, "Dima", "Poniavin", "+375298765654");

insert into profiles (id, login, password, orders) values
(1, "pasha@gmail.com", "111", 1),
(2, "gena@gmail.com", "222", 1),
(3, "luda@gmail.com", "333", 1),
(4, "sasha@gmail.com", "444", 0),
(5, "pasha@gmail.com", "555", 1),
(6, "slava@gmail.com", "666", 1);

insert into users (id, name, surname, phone, town) values
(1, "Roman", "Semizhon", "+375291844112", "Minsk"),
(2, "Gennadi", "Nicolaevich", "+375296222263", "Gomel"),
(3, "Ludmila", "Algardovna", "+375296459357", "Mogelev"),
(4, "Aleksandr", "Sherbitski", "+375294567545", "Grodno"),
(5, "Pasha", "Struk", "+375293434567", "Pinsk"),
(6, "Slava", "Kalinin", "+375292345655", "Brest");

insert into homesteads (id, title, price, description, people_number, rating,
                        id_owner) values
(1, "Dacha_1", 300, "description_1", 5, 0, 1),
(2, "Dacha_2", 400, "description_2", 6, 0, 2),
(3, "Dacha_3", 500, "description_3", 7, 0, 3),
(4, "Dacha_4", 600, "description_4", 8, 0, 2);

insert into orders (id, id_profile, id_home, date_start, date_end,
                    status_pay) values
(1, 2, 2, "2019-06-15", "2019-06-25", true),
(2, 3, 1, "2019-06-15", "2013-07-01", true),
(3, 6, 4, "2019-07-15", "2019-07-12", true);


