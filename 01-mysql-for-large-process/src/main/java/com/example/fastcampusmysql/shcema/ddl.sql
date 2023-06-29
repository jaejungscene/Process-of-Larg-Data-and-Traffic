#### count is "1,001,089"
# select count(id) as count
# from Post
# where memberId=2 and createdDate between '1900-01-01' and '2024-01-01'

# select count(*)
# from Post;

#### search rows is "1,496,436"
explain select createdDate, memberId, count(id) as count
from Post use index (Post__index_member_id_created_date)
where memberId=2 and createdDate between '1900-01-01' and '2024-01-01'
group by memberId, createdDate;

create index Post__index_member_id
    on Post (memberId);

create index Post__index_created_date
    on Post (createdDate);

create index Post__index_member_id_created_date
    on Post (memberId, createdDate);

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
from Post
where memberId=3
limit 2
offset 1;
