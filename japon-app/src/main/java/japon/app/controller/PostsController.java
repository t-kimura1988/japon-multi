package japon.app.controller;

import japon.app.controller.input.post.HomePostListParameter;
import japon.app.controller.input.post.PostCreateRequest;
import japon.app.controller.input.user.UserCreateRequest;
import japon.app.exceptions.JaponIntegrityException;
import japon.app.exceptions.JaponNotFountException;
import japon.app.service.PostsService;
import japon.app.service.UserService;
import japon.domain.entity.JaponUserDetails;
import japon.domain.model.response.PostSearchResponse;
import japon.domain.model.response.UserSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/post")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @PostMapping(value = "/create")
    public PostSearchResponse create(
            @RequestBody @Validated PostCreateRequest param,
            @AuthenticationPrincipal JaponUserDetails userDetails) throws Exception {
        return postsService.create(param.toService(userDetails.user().getUserId())).toRes();
    }

    @GetMapping(value = "/home-list")
    public List<PostSearchResponse> homeList(
            HomePostListParameter parameter
    ) {
        return postsService.homeList(parameter.toService()).toRes();
    }
}
