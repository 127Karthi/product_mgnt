drop table if exists product;
drop table if exists approval_request;

create sequence product_seq minvalue 1 maxvalue 99999 increment by 1 start with 1 nocache nocycle;

create sequence apprvl_req_seq minvalue 1 maxvalue 99999 increment by 1 start with 1 nocache nocycle;

create table product (product_id int AUTO_INCREMENT primary key,
sku_id varchar(25) null,
prdct_name varchar(50) null,
prdct_desc varchar(100) null,
prdct_color varchar(20) null,
prdct_price numeric null,
prdct_sts varchar(15) null,
prdct_post_dt date null,
prdct_apprvd_dt date null,
prdct_apprvd_by varchar(30) null,
prdct_features varchar(200) null,
prdct_notes varchar(200) null,
prdct_in_stock varchar(1) null,
created_dt timestamp null,
created_by varchar(30) null,
updated_dt timestamp null,
updated_by varchar(30) null,
isdeleted varchar(1) null
);

insert into product (product_id, sku_id, prdct_name, prdct_desc, prdct_color,prdct_price, prdct_sts,prdct_post_dt,
prdct_features, prdct_notes, prdct_in_stock, created_dt, created_by)
values(1001,'456-6352-6376','Bike','Kids Bike','RED',80.00,'APPRVD','2023-02-01','','','Y',CURDATE(),'ADMIN');


create table users (usr_id int auto_increment primary key ,
usr_name varchar(20) null,
usr_email varchar(50) null,
usr_role varchar(10) null,
is_active varchar(1) null,
created_dt timestamp null,
created_by varchar(30) null,
updated_dt timestamp null,
updated_by varchar(30) null,
isdeleted varchar(1) null);

create table approval_request (apprvl_req_id int auto_increment primary key ,
prdct_id int null,
prdct_name varchar(50) null,
prdct_price numeric null,
prdct_sts varchar(5) null,
apprvl_req_type varchar(10) null,
apprvl_init_dt date null,
apprvl_init_by int null,
apprvl_sts varchar(10) null,
apprvd_dt date null,
apprvd_by varchar(30) null,
rjcted_dt date null,
rjcted_by varchar(30) null,
rjcted_rsn varchar(200) null,
created_dt timestamp null,
created_by varchar(30) null,
updated_dt timestamp null,
updated_by varchar(30) null,
isdeleted varchar(1) null);

