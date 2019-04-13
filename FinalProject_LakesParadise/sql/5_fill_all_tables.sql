insert into owner (id, name, surname, phone) values
(1, "Nikita", "Vushev", "+375291234354"),
(2, "Sergey", "Kovaleuski", "+375295674321"),
(3, "Luba", "Agarova", "+375298987656"),
(4, "Dima", "Poniavin", "+375298765654");

insert into users (id, name, surname, phone, town) values
(1, "Roman", "Semizhon", "+375291844112", "Minsk"),
(2, "Gennadi", "Nicolaevich", "+375296222263", "Gomel"),
(3, "Ludmila", "Algardovna", "+375296459357", "Mogelev"),
(4, "Aleksandr", "Sherbitski", "+375294567545", "Grodno"),
(5, "Pasha", "Struk", "+375293434567", "Pinsk"),
(6, "Slava", "Kalinin", "+375292345655", "Brest");

insert into homestead (id, title, status, price, description, rating, id_owner) values
(1, "Dacha_1", false, 300, "description_1", 5, 1),
(2, "Dacha_2", false, 400, "description_2", 6, 2),
(3, "Dacha_3", false, 500, "description_3", 7, 3),
(4, "Dacha_4", false, 600, "description_4", 8, 2);

insert into profiles (id, login, password, orders) values
(2, "gena@gmail.com", "1", 1),
(3, "luda@gmail.com", "2", 1),
(4, "sasha@gmail.com", "3", 0),
(6, "slava@gmail.com", "4", 1);

insert into orders (id_user, id_home, date_start, date_end, status_pay, number_people) values
(2, 2, "2019-06-15", "2019-06-25", true, 2),
(3, 1, "2019-06-15", "2013-07-01", true, 8),
(6, 4, "2019-07-15", "2019-07-12", true, 6);


