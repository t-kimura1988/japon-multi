package japon.domain.entity;


import japon.domain.enums.DelFlg;
import japon.domain.listener.PostsListener;
import japon.domain.listener.UsersListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(listener = PostsListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "posts_id_seq")
    private Long id;

    private LocalDate createDate;

    private Long userId;

    private String postBody;

    private DelFlg delFlg;

    private LocalDateTime createdAt;

    private Long createdBy;

    private LocalDateTime updatedAt;

    private Long updatedBy;
}
