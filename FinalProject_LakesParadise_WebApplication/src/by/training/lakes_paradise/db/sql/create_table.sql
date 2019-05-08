# use example_lakes_paradise;
#
# create table if not exists orders (
# id int AUTO_INCREMENT,
# profile_id int(11) not null ,
# home_id int(11) not null ,
# date_start DATE not null,
# date_end DATE not null,
# status_pay TINYINT(4),
# CONSTRAINT id primary key(id)/*ключевое слово constraint предназначено, только для присвоения имени внешнему ключу,
# чтобы в дальнейшем была возможность удалить это ограничение.*/
# );
#
# alter table orders create constraint orders_profile_id foreign key (profile_id) references profiles (id);
#
# create table if not exists profiles (
#   id INT(11),
#   login VARCHAR(45),
#   password VARCHAR(45),
#   role TINYINT(4),
#   CONSTRAINT id PRIMARY KEY (id)
# );
#
# SET FOREIGN_KEY_CHECKS = 0;
# alter table profiles change column id id int(11) auto_increment;
# set foreign_key_checks = 1;
# create index login_inx on profiles (login);
# drop index login_inx on profiles;
# alter table profiles drop index id;
#
# alter table profiles change column login login varchar(45) not null;
# alter table profiles change column password password varchar(45) not null;
# alter table profiles change column role role TINYINT(4) not null;
#
# create table if not exists users (
#   id INT(11),
#   name VARCHAR(15),
#   surname VARCHAR(30),
#   phone INT(11),
#   town VARCHAR(40),
#   image MEDIUMBLOB,
#   CONSTRAINT profile_id FOREIGN KEY (id) REFERENCES
#     profiles(id) on delete cascade on update cascade
# );
#
# alter table users change column name name varchar(15) not null;
# alter table users change column surname surname VARCHAR(30) not null;
# alter table users change column phone phone INT(11) not null;
# alter table users change column town town VARCHAR(40) not null;
#
# create table homesteads (
#   id INT(11) AUTO_INCREMENT,
#   title VARCHAR(45) NOT NULL,
#   price DECIMAL(10, 0) NOT NULL,
#   description MEDIUMTEXT NOT NULL,
#   people_number INT(11) NOT NULL,
#   rating DOUBLE NOT NULL,
#   profile_id INT(11) NOT NULL,
#   CONSTRAINT PK_id PRIMARY KEY (id),
#   constraint FK_profile_id FOREIGN KEY (profile_id) references profiles (id),
#   KEY price (homesteads_price),
#   KEY title (homesteads_title),
#   KEY rating ()
# )

alter table homesteads rename column profile_id to user_id;

ALTER TABLE homesteads ADD CONSTRAINT FK_user_id FOREIGN KEY (user_id) references users (id);

