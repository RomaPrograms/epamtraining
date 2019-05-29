insert into orders (user_id, home_id, date_start, date_end) values (?, ?, ?, ?, ?);

select user_id, home_id, date_start, date_end from orders where id = (?);

update orders set user_id = ?, home_id = ?, date_start = ?, date_end = ? where id = ?;

INSERT INTO homesteads (title, price, description, people_number, owner_id) values (?, ?, ?, ?, ?, ?, ?);

SELECT title, people_number, price, description, owner_id FROM homesteads WHERE id = (?);

UPDATE homesteads SET title = ?, people_number = ?, price = ?, description = ?, owner_id = ? where id = ?;

select id, text, user_name, date_of_comment from reviews where home_id = (?);

select text, user_name, date_of_comment, home_id from reviews where id = (?);

update reviews set text = ?, user_name = ?, date_of_comment = ?, home_id = ? where id = ?;