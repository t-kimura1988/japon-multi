package japon.domain.repository;

import japon.domain.dao.UserDao;
import japon.domain.entity.Users;
import japon.domain.model.params.UserDaoParameter;
import japon.domain.model.response.UserSearchResponse;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static japon.domain.utils.CollectorUtil.toOptional;

@Repository
public class UserRepository {
    @Autowired
    private UserDao userDao;

    public Optional<UserSearchResponse> selectOptional(UserDaoParameter parameter) {
        return userDao.select(parameter, SelectOptions.get(), toOptional());
    }
    public int save(Users users) {
        if(users.getId() == null) {
            return userDao.insert(users);
        }else {
            return userDao.update(users);
        }
    }

}
