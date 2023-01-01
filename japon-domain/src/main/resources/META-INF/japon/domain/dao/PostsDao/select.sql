select
    p.id,
    p.create_date,
    p.user_id,
    p.post_body,
    u.family_name,
    u.given_name,
    u.nick_name,
    u.user_image,
    u.user_profile_image
from
    posts p
inner join
        users u on p.user_id = u.id
        /*%if param.userId != null */
            and p.user_id = /* param.userId */0
        /*%end*/
where
/*%if param.id != null */
    p.id = /* param.id */0
/*%end*/
/*%if param.createDate != null */
    and p.create_date = /* param.createDate */'2022-01-01'
/*%end*/
/*%if param.fromDate != null && param.toDate != null*/
  and
    p.create_date between /* param.fromDate */'2022-01-01' and /* param.toDate */'2022-01-01'
/*%end*/
/*%if param.page != 0 */
    limit /* param.page */10
/*%end*/
