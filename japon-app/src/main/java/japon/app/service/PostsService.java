package japon.app.service;

import japon.app.exceptions.JaponAlreadyExistException;
import japon.app.exceptions.JaponIntegrityException;
import japon.app.exceptions.JaponNotFountException;
import japon.app.service.input.UserCreateServiceInput;
import japon.app.service.input.post.HomePostListServiceInput;
import japon.app.service.input.post.PostCreateServiceInput;
import japon.app.service.output.UserCreateServiceOutput;
import japon.app.service.output.post.HomePostListServiceOutput;
import japon.app.service.output.post.PostCreateServiceOutput;
import japon.domain.entity.Posts;
import japon.domain.enums.DelFlg;
import japon.domain.model.params.PostDaoParameter;
import japon.domain.model.params.UserDaoParameter;
import japon.domain.model.response.UserSearchResponse;
import japon.domain.repository.FirebaseRepository;
import japon.domain.repository.PostsRepository;
import japon.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class PostsService {
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private FirebaseRepository firebaseRepository;

    public PostCreateServiceOutput create(PostCreateServiceInput input) throws Exception{
        Posts entity = input.toEntity();
        var res = postsRepository.save(entity);
        if(res == 0) {
            Map<String, String> param = new LinkedHashMap<>();
            throw  new JaponIntegrityException("登録エラー", "投稿失敗", param, "");
        }

        return PostCreateServiceOutput.builder()
                .postSearchResponse(postsRepository.selectOptional(
                        PostDaoParameter.builder()
                                .id(entity.getId())
                                .createDate(entity.getCreateDate())
                                .userId(entity.getUserId()).build()
                ).orElseThrow(() -> {
                    Map<String, String> param = new LinkedHashMap<>();
                    param.put("post.id:", entity.getId().toString());
                    return new JaponNotFountException("データ未登録", "投稿データ未登録", param);
                }))
                .build();
    }

    public HomePostListServiceOutput homeList(HomePostListServiceInput input) {
        var list = postsRepository.selectHomePostList(input.toDaoParam());

        return HomePostListServiceOutput.builder()
                .list(list)
                .build();
    }

}
