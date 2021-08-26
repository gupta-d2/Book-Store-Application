CREATE TABLE author (author_id integer NOT NULL,
create_time datetime,
email varchar(255),
first_name varchar(255),
last_name varchar(255),
modify_time datetime,
PRIMARY KEY (author_id))


CREATE TABLE book (book_id integer NOT NULL,
book_description varchar(255),
book_title varchar(255),
created_time datetime,
launch_date datetime,
price float,
quantity integer,
modify_time datetime,
author_author_id integer,
PRIMARY KEY (book_id))


CREATE TABLE hibernate_sequence (next_val bigint)
INSERT
	INTO
	hibernate_sequence
VALUES (1)


CREATE TABLE image (image_id integer NOT NULL,
book_id integer,
create_time datetime,
image longblob,
modify_time datetime,
PRIMARY KEY (image_id))

ALTER TABLE book ADD CONSTRAINT FKef9c1v09t9gdkor9ul80hsj4n FOREIGN KEY (author_author_id) REFERENCES author (author_id)

ALTER TABLE image ADD CONSTRAINT FK56boxkje8rys2n78amvgkk913 FOREIGN KEY (book_id) REFERENCES book (book_id)