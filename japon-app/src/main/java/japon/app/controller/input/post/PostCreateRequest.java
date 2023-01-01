package japon.app.controller.input.post;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import japon.app.controller.input.groups.CreateGroups;
import japon.app.controller.input.groups.UpdateGroups;
import japon.app.service.PostsService;
import japon.app.service.input.UserCreateServiceInput;
import japon.app.service.input.post.PostCreateServiceInput;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PostCreateRequest {
    @NotNull(groups = UpdateGroups.class)
    Long postId;
    @NotNull(groups = {UpdateGroups.class, CreateGroups.class})
    @Size(max = 1000, groups = {UpdateGroups.class, CreateGroups.class})
    String postBody;

    public PostCreateServiceInput toService(Long userId) {
        return PostCreateServiceInput.builder()
                .userId(userId)
                .postBody(postBody)
                .build();
    }
}
