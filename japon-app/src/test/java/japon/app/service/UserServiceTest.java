package japon.app.service;

import japon.app.JaponAppApplication;
import japon.app.exceptions.JaponAlreadyExistException;
import japon.app.service.input.UserCreateServiceInput;
import japon.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest(classes = JaponAppApplication.class)
@AutoConfigureMockMvc
@SpringJUnitWebConfig
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Autowired
    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("既存ユーザーが存在している")
    void test1() {
        var res = assertThrows(JaponAlreadyExistException.class, () -> {
           userService.create(UserCreateServiceInput.builder()
                           .userId(1L)
                           .uid("testuid_111113")
                   .build());
        });

        assertEquals("存在エラー", res.getTitle());
        assertEquals("すでにユーザーが登録されています。", res.getMessage());
        assertEquals("testuid_111113", res.getDetail().get("User.uid"));
    }
}
