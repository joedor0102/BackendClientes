
create database testdb;

create table testdb.cliente (id bigint not null AUTO_INCREMENT, business_id varchar(255), creation_date date, email varchar(255), end_date date, phone bigint, shared_key varchar(255), start_date date, primary key (id));


insert into testdb.cliente (id, shared_key, business_id, email, phone, start_date, end_date, creation_date) values (1, 'jgutierrez', 'Juliana Gutierrez','jgutierrez@gmail.com', 3219876543,'2019-05-01', '2019-06-28','2019-05-20');
insert into testdb.cliente (id, shared_key, business_id, email, phone, start_date, end_date, creation_date) values (2, 'mmartinez', 'Manuel Martinez','mmartinez@gmail.com', 3219876543,'2019-05-02', '2019-06-28','2019-05-20');
insert into testdb.cliente (id, shared_key, business_id, email, phone, start_date, end_date, creation_date) values (3, 'aruiz', 'Ana Ruiz','aruiz@gmail.com', 3219876543,'2019-05-01', '2019-06-29','2019-05-20');
insert into testdb.cliente (id, shared_key, business_id, email, phone, start_date, end_date, creation_date) values (4, 'ogarcia', 'Oscar Garcia','ogarcia@gmail.com', 3219876543,'2019-05-03', '2019-06-29','2019-05-20');
insert into testdb.cliente (id, shared_key, business_id, email, phone, start_date, end_date, creation_date) values (5, 'tramos', 'Tania Ramos','tramos@gmail.com', 3219876543,'2019-05-03', '2019-06-29','2019-05-20');
insert into testdb.cliente (id, shared_key, business_id, email, phone, start_date, end_date, creation_date) values (6, 'cariza', 'Carlos Ariza','cariza@gmail.com', 3219876543,'2019-05-01', '2019-06-30','2019-05-20');
insert into testdb.cliente (id, shared_key, business_id, email, phone, start_date, end_date, creation_date) values (7, 'rvillaneda', 'Rodrigo Villaneda','rvillaneda@gmail.com', 3219876543,'2019-05-02', '2019-06-30','2019-05-20');
insert into testdb.cliente (id, shared_key, business_id, email, phone, start_date, end_date, creation_date) values (8, 'mfonseca', 'Mauricio Fonseca','mfonseca@gmail.com', 3219876543,'2019-05-05', '2019-07-31','2019-05-20');
insert into testdb.cliente (id, shared_key, business_id, email, phone, start_date, end_date, creation_date) values (9, 'mafonseca', 'Maria Alejandra Fonseca','mfonseca@gmail.com', 3219876543,'2019-05-04', '2019-07-30','2019-05-20');





