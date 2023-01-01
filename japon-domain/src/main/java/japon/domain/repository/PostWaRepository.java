package japon.domain.repository;

import japon.domain.dao.PostWaDao;
import japon.domain.dao.PostsDao;
import japon.domain.entity.PostWa;
import japon.domain.entity.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostWaRepository {
    @Autowired
    private PostWaDao postWaDao;

    public int save(PostWa postWa) {
        if(postWa.getId() == null) {
            return postWaDao.insert(postWa);
        }else {
            return postWaDao.update(postWa);
        }
    }
}
