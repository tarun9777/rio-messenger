create database if not exists messenger;

commit;

use messenger;

create table if not exists user (
    username varchar(20),
    salt varchar(40) not null,
    hash varchar(100) not null,
    primary key (username)
);

create table if not exists message (
    msg_id varchar(40),
    msg_from varchar(20),
    msg_to varchar(20),
    msg varchar(2000),
    time_sent bigint,
    is_read char(1) default 'N',
    primary key(msg_id)
);

create index ind_msg_from on message (msg_from);
create index ind_msg_to on message (msg_to);
create index ind_time_sent on message (time_sent);

commit;
