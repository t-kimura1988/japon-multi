package japon.app.service.input.post;

import japon.domain.entity.Posts;
import japon.domain.enums.DelFlg;
import japon.domain.model.params.PostDaoParameter;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class HomePostListServiceInput {
    LocalDate fromDate;
    LocalDate toDate;
    int page;

    public PostDaoParameter toDaoParam() {
        return PostDaoParameter.builder()
                .fromDate(fromDate)
                .toDate(toDate)
                .page(page).build();
    }

}
