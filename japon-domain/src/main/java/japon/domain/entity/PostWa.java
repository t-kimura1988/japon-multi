package japon.domain.entity;


import japon.domain.listener.PostWaListener;
import lombok.Data;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity(listener = PostWaListener.class, naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "post_wa")
public class PostWa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "posts_wa_id_seq")
    private Long id;

    private Long postId;

    private LocalDate postCreateDate;

    private Long userId;

    private LocalDateTime createdAt;

    private Long createdBy;

    private LocalDateTime updatedAt;

    private Long updatedBy;
}
