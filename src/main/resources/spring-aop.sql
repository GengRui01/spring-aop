# 删除spring_aop数据库
drop database if exists spring_aop;

# 创建spring_aop数据库
create database spring_aop;

# 使用spring_aop数据库
use spring_aop;

# 创建account表
create table account (
    id int(11) auto_increment primary key,
    accountNum varchar(20) default NULL,
    money int(8) default 0
);

# 新增数据
insert into account (accountNum, money) values
("622200001",1000),("622200002",1000);