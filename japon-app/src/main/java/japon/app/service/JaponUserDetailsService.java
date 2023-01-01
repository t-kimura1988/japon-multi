package japon.app.service;

import japon.domain.entity.JaponUserDetails;
import japon.domain.model.params.UserDaoParameter;
import japon.domain.model.response.UserSearchResponse;
import japon.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class JaponUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("User Details Service: Load user");

        if(Objects.isNull(username)) {
            log.error("username is empty");
            throw new UsernameNotFoundException("username is empty");
        }

        UserSearchResponse user = userRepository.selectOptional(UserDaoParameter.builder()
                .uid(username).build())
                .orElseThrow(() -> new UsernameNotFoundException("user info is empty"));

        return new JaponUserDetails(
                username,
                user,
                AuthorityUtils.createAuthorityList("JAPON_USER")

        );
    }
}
