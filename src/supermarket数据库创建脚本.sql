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
                                         constraint ck_tb_account_pay_check check (pay_check in('是','否'))
)engine=innodb default charset=utf8;

-- constraint fk_tb_account_supplier_name foreign key(supplier_name) REFERENCES tb_supplier(supplier_name)

drop table tb_account;

select account_id,commodity_name,num,price,pay_check,supplier_name,account_description,time
from tb_account;

insert  into tb_account(commodity_name,num,price,pay_check,supplier_name,account_description,time)
values ('macbook pro 2020',10,276490,'是','苹果中国','顶配定制机','2020-11-1');

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

drop table tb_supplier;

select supplier_id,supplier_name,supplier_description,linkman,supplier_telphone,supplier_address
from tb_supplier;

insert into tb_supplier (supplier_name,supplier_description,linkman,supplier_telphone,supplier_address)
values ('苹果中国','macbook pro 定制机','蒂姆.库克','17312297707','加利福尼亚');

insert into tb_supplier (supplier_name,supplier_description,linkman,supplier_telphone,supplier_address)
values('三星','三星盖乐世','李在镕','12345678911','韩国首尔');


-- 商品分组查询
select commodity_name,sum(num),sum(price)
from tb_account
group by commodity_name;

select commodity_name,supplier_name from tb_account

select t2.commodity_name,t2.sumofnum,t2.sumofprice,t1.supplier_name
from (select commodity_name,supplier_name from tb_account) t1
         inner join
     (select commodity_name,sum(num) as sumofnum,sum(price) as sumofprice from tb_account group by commodity_name) t2
     on t1.commodity_name=t2.commodity_name;
ORDER BY t2.commodity_name;

select t2.commodity_name,t2.sumofnum,t2.sumofprice,t1.supplier_name
from (select commodity_name,supplier_name from tb_account) t1
         inner join
     (select commodity_name,sum(num) as sumofnum,sum(price) as sumofprice from tb_account group by commodity_name) t2
     on t1.commodity_name=t2.commodity_name;
ORDER BY t2.commodity_name limit 1,5;

select COUNT(t2.commodity_name)
from (select commodity_name,supplier_name from tb_account) t1
         inner join
     (select commodity_name,sum(num) as sumofnum,sum(price) as sumofprice from tb_account group by commodity_name) t2
     on t1.commodity_name=t2.commodity_name;
ORDER BY t2.commodity_name;





-- 供应商分组查询
select supplier_id,supplier_name
from tb_supplier
order by supplier_id;

select supplier_name,sum(price),count(commodity_name),sum(num)
from tb_account
group by supplier_name;

select t1.supplier_id,t1.supplier_name,t2.sumofprice,t2.type,t2.sumofnum
from (
         select supplier_id,supplier_name
         from tb_supplier
         order by supplier_id) t1
         inner JOIN(
    select supplier_name,sum(price) as sumofprice,count(commodity_name) as type,sum(num) as sumofnum
    from tb_account
    group by supplier_name) t2
                   on t1.supplier_name=t2.supplier_name
ORDER BY t1.supplier_id


select count(t1.supplier_id)
from (
         select supplier_id,supplier_name
         from tb_supplier
         order by supplier_id) t1
         inner JOIN(
    select supplier_name,sum(price) as sumofprice,count(commodity_name) as type,sum(num) as sumofnum
    from tb_account
    group by supplier_name) t2
                   on t1.supplier_name=t2.supplier_name
ORDER BY t1.supplier_id










