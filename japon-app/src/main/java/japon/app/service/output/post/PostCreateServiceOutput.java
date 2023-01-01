package japon.app.service.output.post;

import japon.domain.model.response.PostSearchResponse;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PostCreateServiceOutput {
    PostSearchResponse postSearchResponse;

    public PostSearchResponse toRes() {
        return postSearchResponse;
    }
}
