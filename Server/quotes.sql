create database quotes;


use quotes;


create table user_tbl
(
    user_id int auto_increment,
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    password varchar(255),
    mobile varchar(255),
    primary key(user_id)

);

create table quotes_tbl
(
    q_id int auto_increment,
    text varchar(255),
    author varchar(255),
    user_id int,
    primary key(q_id),
    foreign key (user_id) references user_tbl(user_id) ON DELETE CASCADE

);


create table fav_quotes
(
    f_id int auto_increment,
    user_id int,
    q_id int,
    primary key(f_id),
    foreign key(user_id) references user_tbl(user_id) ON DELETE CASCADE,
    foreign key (q_id) references quotes_tbl(q_id) ON DELETE CASCADE
);


insert into user_tbl (first_name,last_name,email,password,mobile) values("test","test","test","test","test");
insert into quotes_tbl(text,author,user_id) values("some text","author",1);

insert into fav_quotes(user_id,q_id) values(1,1);



select qt.q_id,qt.text,qt.author,qt.user_id from quotes_tbl qt join fav_quotes fq on qt.q_id = fq.q_id where fq.user_id = 2;

UPDATE user_tbl set first_name = "c", last_name = "c", email = "c", password = "c", mobile = "c" where user_id =1;


UPDATE quotes_tbl set text = "sdasdasd", author = "hank" where q_id = 1;
