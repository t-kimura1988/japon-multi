package japon.domain.model.params;

import japon.domain.enums.DelFlg;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDaoParameter {
    private Long userId;
    private String uid;
    private DelFlg delFlg;
}
