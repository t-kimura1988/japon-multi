package japon.domain.dao;

import japon.domain.entity.Posts;
import japon.domain.entity.Users;
import japon.domain.model.params.PostDaoParameter;
import japon.domain.model.params.UserDaoParameter;
import japon.domain.model.response.PostSearchResponse;
import japon.domain.model.response.UserSearchResponse;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface PostsDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R select(PostDaoParameter param, SelectOptions options, Collector<PostSearchResponse, ?, R> collector);
    @Insert
    int insert(Posts posts);

    @Update(excludeNull = true)
    int update(Posts posts);
}
