insert into orders (user_id, home_id, date_start, date_end, status_pay) values (?, ?, ?, ?, ?)ж;

select user_id, home_id, date_start, date_end, status_pay from orders where id = (?);

update orders set user_id = ?, home_id = ?, date_start = ?, date_end = ?, status_pay = ? where id = ?;

INSERT INTO homesteads (title, price, description, people_number, rating, number_of_voted_users, owner_id) values (?, ?, ?, ?, ?, ?, ?);

SELECT title, people_number, price, description, rating, number_of_voted_users, owner_id FROM homesteads WHERE id = (?);

UPDATE homesteads SET title = ?, people_number = ?, price = ?, description = ?, rating = ?, number_of_voted_users = ?, owner_id = ? where id = ?;

select id, text, user_name, date_of_comment from reviews where home_id = (?);

select text, user_name, date_of_comment, home_id from reviews where id = (?);

update reviews set text = ?, user_name = ?, date_of_comment = ?, home_id = ? where id = ?;