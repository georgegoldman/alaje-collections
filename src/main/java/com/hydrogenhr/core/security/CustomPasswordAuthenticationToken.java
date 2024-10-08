package com.hydrogenhr.core.security;

import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.hydrogenhr.core.constants.AppConstants.Authentication.PASSWORD;
import static com.hydrogenhr.core.constants.AppConstants.Authentication.USERNAME;
import static com.hydrogenhr.core.constants.AppConstants.SecurityConstants.CUSTOM_PASSWORD;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 10/1/24
 * Time: 2:00 PM
 */
@Getter
public class CustomPasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    private final String username;
    private final String password;
    private final Set<String> scopes;

    public CustomPasswordAuthenticationToken(Authentication clientPrincipal,
                                             @Nullable Set<String> scopes, @Nullable Map<String, Object> additionalParameters) {
        super(new AuthorizationGrantType(CUSTOM_PASSWORD), clientPrincipal, additionalParameters);
        this.username = (String) additionalParameters.get(USERNAME);
        this.password = (String) additionalParameters.get(PASSWORD);
        this.scopes = Collections.unmodifiableSet(
                scopes != null ? new HashSet<>(scopes) : Collections.emptySet());
    }
}
