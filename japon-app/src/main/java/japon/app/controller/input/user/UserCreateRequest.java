package japon.app.controller.input.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import japon.app.service.input.UserCreateServiceInput;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserCreateRequest {
    @NotNull
    @Size(max = 100)
    String familyName;
    @NotNull
    @Size(max = 100)
    String givenName;
    @Size(max = 100)
    String nickName;

    public UserCreateServiceInput toService(String uid, String email) {
        return UserCreateServiceInput.builder()
                .uid(uid)
                .familyName(familyName)
                .givenName(givenName)
                .email(email)
                .build();
    }
}
