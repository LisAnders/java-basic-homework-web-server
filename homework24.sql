create table products (
	product_id serial primary key,
	name varchar(4000) not null,
	price float not null
);


insert into products (name, price) values ('Картошка', 12.5);
insert into products (name, price) values ('Капуста', 11.7);