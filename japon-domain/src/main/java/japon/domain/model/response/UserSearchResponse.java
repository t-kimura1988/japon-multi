package japon.domain.model.response;

import japon.domain.enums.DelFlg;
import lombok.Data;
import org.seasar.doma.Entity;

@Data
@Entity
public class UserSearchResponse {
    Long userId;
    String uid;
    String familyName;
    String givenName;
    String nickName;
    String userImage;
    String email;
    String userProfileImage;
    DelFlg delFlg;
}
