package japon.domain.dao;

import japon.domain.entity.Users;
import japon.domain.model.params.UserDaoParameter;
import japon.domain.model.response.UserSearchResponse;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface UserDao {
    @Select(strategy = SelectType.COLLECT)
    <R> R select(UserDaoParameter param, SelectOptions options, Collector<UserSearchResponse, ? , R> collector);

    @Insert
    int insert(Users users);

    @Update(excludeNull = true)
    int update(Users users);
}
