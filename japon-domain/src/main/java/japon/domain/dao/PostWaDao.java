package japon.domain.dao;

import japon.domain.entity.PostWa;
import japon.domain.entity.Posts;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

@ConfigAutowireable
@Dao
public interface PostWaDao {

    @Insert
    int insert(PostWa postWa);

    @Update(excludeNull = true)
    int update(PostWa postWa);
}
