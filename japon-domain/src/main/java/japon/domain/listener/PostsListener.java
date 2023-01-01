package japon.domain.listener;


import japon.domain.entity.JaponUserDetails;
import japon.domain.entity.Posts;
import japon.domain.entity.Users;
import lombok.val;
import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

public class PostsListener<E extends Posts> implements EntityListener<E> {

    @Override
    public void preInsert(E entity, PreInsertContext<E> context) {
        val timestamp = LocalDateTime.now();
        var japonUser = (JaponUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        entity.setCreatedAt(timestamp);
        entity.setCreatedBy(japonUser.user().getUserId());
        entity.setUpdatedAt(timestamp);
        entity.setUpdatedBy(japonUser.user().getUserId());
    }

    @Override
    public void preUpdate(E entity, PreUpdateContext<E> context) {
        val timestamp = LocalDateTime.now();
        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            var japonUser = (JaponUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            entity.setUpdatedBy(japonUser.user().getUserId());
        } else {
            entity.setUpdatedBy(0L);
        }
        entity.setUpdatedAt(timestamp);
    }
}
