package japon.domain.model.params;

import japon.domain.enums.DelFlg;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class PostDaoParameter {
    private Long id;
    private LocalDate createDate;
    private Long userId;
    private String uid;
    private DelFlg delFlg;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int page;
}
