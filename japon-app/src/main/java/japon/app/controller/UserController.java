package japon.app.controller;

import japon.app.controller.input.user.UserCreateRequest;
import japon.app.exceptions.JaponIntegrityException;
import japon.app.exceptions.JaponNotFountException;
import japon.app.service.UserService;
import japon.domain.entity.JaponUserDetails;
import japon.domain.model.response.UserSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    public UserSearchResponse create(
            @RequestBody @Validated UserCreateRequest param,
            @AuthenticationPrincipal JaponUserDetails userDetails) throws Exception {
        return userService.create(param.toService(userDetails.user().getUid(), userDetails.user().getEmail())).toRes();
    }

    @GetMapping(value = "/show")
    public UserSearchResponse show(
        @AuthenticationPrincipal JaponUserDetails userDetails
    ) throws JaponIntegrityException, JaponNotFountException {
        return userService.show(userDetails.user().getUid());
    }

}
