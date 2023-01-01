package japon.app.service.input.post;

import japon.domain.entity.Posts;
import japon.domain.entity.Users;
import japon.domain.enums.DelFlg;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class PostCreateServiceInput {
    Long userId;
    String postBody;

    public Posts toEntity() {
        Posts post = new Posts();
        post.setPostBody(postBody);
        post.setCreateDate(LocalDate.now());
        post.setUserId(userId);
        post.setDelFlg(DelFlg.NOT_DELETED);

        return post;
    }
}
