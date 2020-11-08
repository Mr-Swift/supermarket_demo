-- 创建数据库
create database if not exists supermarket;

use supermarket;

-- 创建用户表
-- 检查性别为男、女或者第三性别，其余的不可以
-- 检查年龄是否成年 
-- 检查电话号码是否为11位，且必须是数字 
-- 检查权限是否非法 
create table if not exists tb_user (
user_id int primary key auto_increment,
user_name varchar(50) not null,
password varchar(50) not null, 
sex varchar(30) not null,
age int not null,
user_telphone varchar(50) not null ,
user_address varchar(100) not null,
authority varchar(50) not null ,
constraint ck_tb_user_sex check(sex in('男','女','第三性别')),
constraint ck_tb_user_age check(sex>=18),
constraint ck_tb_user_user_telphone check(user_telphone like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
constraint ck_tb_user_authority check(authority in('普通员工','部门经理'))
)engine=innodb default charset=utf8;


-- drop table tb_user;

-- 创建账单表 
-- 检查货的数量和价格，必须大于零 
-- 检查是否付款必须是是或否 
create table if not exists tb_account(
account_id int primary key auto_increment,
commodity_name varchar(50) not null ,
num int not null,
price double not null ,
pay_check varchar(30) not null ,
supplier_name varchar(50) not null,
account_description varchar(300) ,
time date not null,
constraint ck_tb_account_num check(num>0),
constraint ck_tb_account_price check(price>0),
constraint ck_tb_account_pay_check check (pay_check in('是','否')),
constraint fk_tb_account_supplier_name foreign key(supplier_name) REFERENCES tb_supplier(supplier_name)
)engine=innodb default charset=utf8;

-- drop table tb_account;

-- 创建供货商表 
-- 检查电话号码是否是11位且都是数字 
-- 设定supplier_name为唯一
create table if not exists tb_supplier (
supplier_id int primary key auto_increment, 
supplier_name varchar(50) not null,
supplier_description varchar(300) ,
linkman varchar(50) not null, 
supplier_telphone varchar(50) not null,
supplier_address varchar(100) not null,
constraint ck_tb_supplier_supplier_telphone check(supplier_telphone like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
constraint uk_tb_supplier_supplier_name unique(supplier_name)
)engine=innodb default charset=utf8;

-- drop table tb_supplier;