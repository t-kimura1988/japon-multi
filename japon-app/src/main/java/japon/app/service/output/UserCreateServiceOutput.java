package japon.app.service.output;

import japon.domain.model.response.UserSearchResponse;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

@Value
@Builder
public class UserCreateServiceOutput {
    UserSearchResponse user;

    public UserSearchResponse toRes() {
        return user;
    }
}
