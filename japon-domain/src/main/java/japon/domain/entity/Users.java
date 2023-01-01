package japon.domain.entity;


import japon.domain.enums.DelFlg;
import japon.domain.listener.UsersListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(listener = UsersListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "users_id_seq")
    private Long id;

    private String uid;

    private String familyName;

    private String givenName;

    private String nickName;

    private String userImage;

    private String userProfileImage;

    private String email;

    private String tel;

    private DelFlg delFlg;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long updatedBy;
}
