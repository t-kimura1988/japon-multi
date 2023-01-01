package japon.domain.model.response;

import japon.domain.enums.DelFlg;
import lombok.Data;
import org.seasar.doma.Entity;

import java.time.LocalDate;

@Data
@Entity
public class PostSearchResponse {
    Long id;
    Long userId;
    LocalDate createDate;
    String postBody;
    String familyName;
    String givenName;
    String nickName;
    String userImage;
    String userProfileImage;
}
