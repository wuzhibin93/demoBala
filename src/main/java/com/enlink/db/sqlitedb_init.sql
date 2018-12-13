-- 初始化数据库
drop table users;
create table users(
  id integer primary key autoincrement,
  name text,
  username text,
  password text,
  configs text,
  create_at integer,
  update_at integer
);

insert into users(id, name, username, password, configs, create_at, update_at)
  values(1, '超级管理员', 'spadmin', '591ad57b353a85af86881987d4a5c1f2', '', 1542183202198, 1542183202198);

drop table modules;
create table modules(
  id integer primary key autoincrement,
  parent_id integer,
  name text,
  configs text,
  create_at integer,
  update_at integer
);

drop table panels;
create table panels(
  id integer primary key autoincrement,
  name text,
  configs text,
  create_at integer,
  update_at integer
);

drop table charts;
create table charts(
  id integer primary key autoincrement,
  name text,
  configs text,
  create_at integer,
  update_at integer
);

drop table panel_chart_mapping;
create table panel_chart_mapping(
  id integer primary key autoincrement,
  panel_id integer,
  chart_id integer,
  create_at integer,
  update_at integer
);