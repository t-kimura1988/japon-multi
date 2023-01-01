package japon.app.service.output.post;

import japon.domain.model.response.PostSearchResponse;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class HomePostListServiceOutput {
    List<PostSearchResponse> list;

    public List<PostSearchResponse> toRes() {
        return list;
    }
}
