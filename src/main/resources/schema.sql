drop table USER if exists;

create table USER (ID serial, Name varchar(5), Dept varchar (10) NOT NULL, Salary bigint)