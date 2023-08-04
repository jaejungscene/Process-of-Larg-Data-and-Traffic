#### count is "1,001,089"
# select count(id) as count
# from Post
# where memberId=2 and createdDate between '1900-01-01' and '2024-01-01'

# select count(*)
# from Post;

select count(*) from post;
select * from post;
select * from Post
limit 2
offset 3;

#### search rows is "1,496,436"
explain select createdDate, memberId, count(id) as count
from Post use index ()
where memberId=4 and createdDate between '1900-01-01' and '2024-01-01'
group by memberId, createdDate;


explain select createdDate, memberId, count(id) as count
from POST
where memberId=4 and createdDate between '1900-01-01' and '2024-01-01'
group by memberId, createdDate;

select count(id) as count
from POST
where memberId=4 and createdDate between '1900-01-01' and '2023-01-01';

select memberId, count(id)
from post
group by memberId;

select count(distinct createdDate)
from post;

create index Post__index_member_id
    on Post (memberId);

create index Post__index_created_date
    on Post (createdDate);

create index Post__index_member_id_created_date
    on Post (memberId, createdDate);

drop index Post__index_member_id_created_date on POST;
show indexes from Post;

# drop index Post__index_member_id on Post;
# drop index Post__index_created_date on Post;
# drop index Post__index_member_id_created_date on Post;
# drop index Post__index_member_id on Post;
# show indexes from Post;

# select count(distinct createdDate)
# from Post;

# explain select createdDate, memberId, count(id) as count
# from Post use index (Post__index_member_id_created_date)
# where memberId=0 and createdDate between '1900-01-01' and '2024-01-01'
# group by memberId, createdDate;
select *
from POST
where memberId=1
order by id DESC
limit 9;


######################################################################
with Covering as (
    SELECT id
    FROM POST
    WHERE createdDate between '1900-01-01' and '1900-12-01'
    LIMIT 5
)
SELECT *
FROM POST INNER JOIN Covering ON Covering.id = POST.id;

select *
from Post use index ()
where createdDate between '1900-01-01' and '1900-12-01'
LIMIT 5;
######################################################################
explain SELECT *
FROM POST
WHERE
    memberId IN (2,3,4)
ORDER BY id DESC
LIMIT 10;

explain select id
from Post use index (Post__index_created_date)
where createdDate between '1900-01-01' and '1900-12-01'
LIMIT 5;

select *
from Follow;

INSERT INTO Follow(fromMemberId, toMemberId, createdAt)
VALUES(4, 1, '2023-05-12');

select count(*)
from POST;

select *
from Post
where memberId=3
limit 2
offset 1;

select *
from POST
where memberId=4 or memberId=3
order by id DESC ;

drop table Member;

create table Member
(
    id int auto_increment,
    email varchar(20) not null,
    nickname varchar(20) not null,
    birthday date not null,
    createdAt datetime not null,
    constraint member_id_uindex
    primary key (id)
);

create table MemberNicknameHistory
(
    id int auto_increment,
    memberId int not null,
    nickname varchar(20) not null,
    createdAt datetime not null,
    constraint memberNicknameHistory_id_uindex
        primary key (id)
);

create table Follow
(
    id int auto_increment,
    fromMemberId int not null,
    toMemberId int not null,
    createdAt datetime not null,
    constraint Follow_id_uindex
        primary key (id)
);

create unique index Follow_fromMemberId_toMemberId_uindex
    on Follow (fromMemberId, toMemberId);


create table POST
(
    id int auto_increment,
    memberId int not null,
    contents varchar(100) not null,
    createdDate date not null,
    createdAt datetime not null,
    constraint POST_id_uindex
        primary key (id)
);

create index POST__index_member_id
    on POST (memberId);

create index POST__index_created_date
    on POST (createdDate);


