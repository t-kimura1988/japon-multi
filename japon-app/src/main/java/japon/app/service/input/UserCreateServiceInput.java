package japon.app.service.input;

import japon.domain.entity.Users;
import japon.domain.enums.DelFlg;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserCreateServiceInput {
    Long userId;
    String uid;
    String familyName;
    String givenName;
    String email;

    public Users toEntity() {
        Users user = new Users();
        user.setUid(uid);
        user.setFamilyName(familyName);
        user.setGivenName(givenName);
        user.setEmail(email);
        user.setDelFlg(DelFlg.NOT_DELETED);

        return user;
    }
}
