package japon.app.controller.input.post;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import japon.app.controller.input.groups.CreateGroups;
import japon.app.controller.input.groups.UpdateGroups;
import japon.app.service.input.post.HomePostListServiceInput;
import japon.app.service.input.post.PostCreateServiceInput;
import lombok.Builder;
import lombok.Value;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.YearMonth;

@Value
@Builder
public class HomePostListParameter {
    int year;
    @Nullable
    int month;
    int page;

    public HomePostListServiceInput toService() {
        LocalDate from;
        LocalDate to;
        if(month == 0) {
            from = LocalDate.of(year, 1, 1);
            to = LocalDate.of(year, 12, 31);
        } else {
            YearMonth yyyyMM = YearMonth.of(year, month);
            from = LocalDate.of(year, month, 1);
            to = LocalDate.of(year, month, yyyyMM.lengthOfMonth());
        }
        return HomePostListServiceInput.builder()
                .fromDate(from)
                .toDate(to)
                .page(page)
                .build();
    }
}
