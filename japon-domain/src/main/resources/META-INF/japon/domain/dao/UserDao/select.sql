select
    u.id as user_id,
    u.uid,
    u.family_name,
    u.given_name,
    u.nick_name,
    u.user_image,
    u.email,
    u.user_profile_image,
    u.del_flg
from users u
where
/*%if param.userId != null */
    u.id = /* param.userId */0
/*%end*/
/*%if param.uid != null */
    u.uid = /* param.uid */0
/*%end*/
/*%if param.delFlg != null */
    u.del_flg = /* param.delFlg */0
/*%end*/