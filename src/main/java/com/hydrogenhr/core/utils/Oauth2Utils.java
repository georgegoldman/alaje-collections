package com.hydrogenhr.core.utils;

import com.hydrogenhr.model.response.Principal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 10:54 PM
 */
public class Oauth2Utils {
    public static Principal getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            if (authentication.getPrincipal() instanceof User user) {
                return Principal.builder()
                        .username(user.getUsername())
                        .authorities(new HashSet<>(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())))
                        .build();
            } else if (authentication instanceof Jwt jwt) {
                return AppUtils.fromJson(jwt.getClaims(), Principal.class);
            }
        }
        return null;
    }

    public static String getAccessToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return jwt.getTokenValue();
        }
        return null;
    }

    public static String getPrincipal(JwtDecoder jwtDecoder) {
        try {
            String header = HttpHeaders.AUTHORIZATION;
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            String authorization = request.getHeader(header);
            if (authorization == null) {
                return null;
            }
            String accessToken = authorization.substring(7);
            Jwt jwt = jwtDecoder.decode(accessToken);
            return jwt.getClaims().get("sub").toString();
        } catch (Exception e) {
            return null;
        }
    }
}
