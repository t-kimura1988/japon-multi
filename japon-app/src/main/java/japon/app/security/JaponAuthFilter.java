package japon.app.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import japon.app.entity.ErrorResponse;
import japon.app.service.JaponUserDetailsService;
import japon.domain.entity.JaponUserDetails;
import japon.domain.enums.AccountType;
import japon.domain.model.response.UserSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.oauth2.jwt.Jwt;

import java.io.IOException;
import java.util.Objects;

@Component
public class JaponAuthFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Autowired
    JaponUserDetailsService userDetailsService;

    public JaponAuthFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        if(Objects.nonNull(context.getAuthentication()) && context.getAuthentication().getPrincipal() instanceof Jwt) {
            UserDetails user = null;
            String uid = ((Jwt) context.getAuthentication().getPrincipal()).getClaimAsString("user_id");
            String email = ((Jwt) context.getAuthentication().getPrincipal()).getClaimAsString("email");
            String apiKey = request.getHeader("X-SPOC-API-KEY");
//            if(apiKey == null || !iosApiKey.equals(apiKey)) {
//                handleApiKeyError(response);
//                return;
//            }
            if(!request.getRequestURI().equals("/api/user/create")) {
                String accountType = ((Jwt) context.getAuthentication().getPrincipal()).getClaimAsString("account_type");
                user = userDetailsService.loadUserByUsername(uid);
                if(accountType == null || Objects.isNull(AccountType.of(accountType))) {
                    handleAccountTypeError(response);
                    return;
                }
            } else {
                var userRes = new UserSearchResponse();
                userRes.setUid(uid);
                userRes.setEmail(email);
                user = new JaponUserDetails(uid, userRes, AuthorityUtils.createAuthorityList("CREATE_ACCOUNT"));
            }

            var authRequest = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            context.setAuthentication(authRequest);
        }

        filterChain.doFilter(request, response);

    }

    private void handleAccountTypeError(HttpServletResponse response) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCd("E0003")
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("アカウント種別が不正です").build();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));

    }

    private void handleApiKeyError(HttpServletResponse response) throws IOException {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCd("E0006")
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("Api Keyが不正です").build();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));

    }
}
