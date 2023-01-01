package japon.app.config;

import japon.app.security.JaponAuthFilter;
import japon.app.security.JaponAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JaponAuthFilter japonAuthFilter;

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeHttpRequests(authz -> {
                    try {
                        authz
                                .requestMatchers("/actuator/**").permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .oauth2ResourceServer()
                                .authenticationEntryPoint(new JaponAuthenticationEntryPoint())
                                .jwt();
                    } catch (Exception e) {
                        new JaponAuthenticationEntryPoint();
                    }
                }
        );
        http.addFilterBefore(japonAuthFilter, SwitchUserFilter.class);

        return http.build();
    }
}
