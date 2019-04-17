CREATE DATABASE lakes_paradise_db default character set utf8;

create user 'lakes_paradise_user'@'localhost' identified by 'lakes_paradise_password1';

grant select, insert, delete, update, alter
on lakes_paradise_db.*
to 'lakes_paradise_user'@'localhost';

grant select, insert, delete, update, alter
on lakes_paradise_db.*
to 'lakes_paradise_user'@'%';


/*lakes_paradise_db*/