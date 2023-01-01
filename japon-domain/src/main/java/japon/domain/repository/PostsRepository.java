package japon.domain.repository;

import japon.domain.dao.PostsDao;
import japon.domain.dao.UserDao;
import japon.domain.entity.Posts;
import japon.domain.entity.Users;
import japon.domain.model.params.PostDaoParameter;
import japon.domain.model.params.UserDaoParameter;
import japon.domain.model.response.PostSearchResponse;
import japon.domain.model.response.UserSearchResponse;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static japon.domain.utils.CollectorUtil.toOptional;
import static java.util.stream.Collectors.toList;

@Repository
public class PostsRepository {
    @Autowired
    private PostsDao postsDao;

    public Optional<PostSearchResponse> selectOptional(PostDaoParameter param) {
        return postsDao.select(param, SelectOptions.get(), toOptional());
    }

    public List<PostSearchResponse> selectHomePostList(PostDaoParameter param) {
        return postsDao.select(param, SelectOptions.get(), toList());
    }

    public int save(Posts posts) {
        if(posts.getId() == null) {
            return postsDao.insert(posts);
        }else {
            return postsDao.update(posts);
        }
    }

}
