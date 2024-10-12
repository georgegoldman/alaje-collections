package com.hydrogenhr.config;

import com.hydrogenhr.core.security.clientRegistration.CustomClientRegistrationConverter;
import com.hydrogenhr.core.security.CustomPassordAuthenticationConverter;
import com.hydrogenhr.core.security.CustomRedirectUriValidator;
import com.hydrogenhr.core.security.clientRegistration.CustomRegisteredClientConverter;
import com.hydrogenhr.core.security.CustomUserDetailsService;
import com.hydrogenhr.core.security.oauth2.JpaOAuth2AuthorizationService;
import com.hydrogenhr.core.security.provider.CustomPasswordAuthenticationProvider;
import com.hydrogenhr.core.utils.RsaKeyUtils;
import com.hydrogenhr.model.response.CustomPrincipal;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationValidator;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcClientConfigurationAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcClientRegistrationAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.DelegatingOAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.JwtGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2AccessTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2RefreshTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.hydrogenhr.core.constants.AppConstants.Oauth2Uri.LOGIN;
import static com.hydrogenhr.core.constants.AppConstants.Oauth2Uri.OAUTH2_AUTHORIZATION_ENDPOINT;
import static com.hydrogenhr.core.constants.AppConstants.Oauth2Uri.OAUTH2_INTROSPECT_ENDPOINT;
import static com.hydrogenhr.core.constants.AppConstants.Oauth2Uri.OAUTH2_JWK_SET_ENDPOINT;
import static com.hydrogenhr.core.constants.AppConstants.Oauth2Uri.OAUTH2_LOGOUT_ENDPOINT;
import static com.hydrogenhr.core.constants.AppConstants.Oauth2Uri.OAUTH2_REGISTRATION_ENDPOINT;
import static com.hydrogenhr.core.constants.AppConstants.Oauth2Uri.OAUTH2_REVOKE_ENDPOINT;
import static com.hydrogenhr.core.constants.AppConstants.Oauth2Uri.OAUTH2_TOKEN_ENDPOINT;
import static com.hydrogenhr.core.constants.AppConstants.Oauth2Uri.OAUTH2_USER_INFO;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:46 PM
 */
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfig {

    public static JwtDecoder decodeJwt;
    private final CustomUserDetailsService userDetailsService;
    private final JpaOAuth2AuthorizationService auth2AuthorizationService;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.security.jwt-endpoint}")
    private String jwtEndpoint;

    @Value("${app.security.issuer}")
    private String issuer;

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http
                .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .tokenEndpoint(tokenEndpoint -> tokenEndpoint
                        .accessTokenRequestConverter(new CustomPassordAuthenticationConverter())
                        .authenticationProvider(new CustomPasswordAuthenticationProvider(auth2AuthorizationService, tokenGenerator(), userDetailsService, passwordEncoder))
                        .authenticationProviders(configureAuthenticationValidator())
                ).oidc((oidc) -> oidc
                        .userInfoEndpoint((userInfo) -> userInfo
                                .userInfoMapper(userInfoMapper()))
                        .clientRegistrationEndpoint((registration) -> registration
                                .authenticationProviders(configureCustomClientMetadataConverters())
                        ));

        http
                .exceptionHandling(e -> e.defaultAuthenticationEntryPointFor(
                        new LoginUrlAuthenticationEntryPoint(LOGIN),
                        new MediaTypeRequestMatcher(MediaType.TEXT_HTML))
                ).oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()));
        return http.build();
    }

    private Function<OidcUserInfoAuthenticationContext, OidcUserInfo> userInfoMapper() {
        return context -> {
            OidcUserInfoAuthenticationToken authentication = context.getAuthentication();
            JwtAuthenticationToken principal = (JwtAuthenticationToken) authentication.getPrincipal();
            return new OidcUserInfo(principal.getToken().getClaims());
        };
    }

    private Consumer<List<AuthenticationProvider>> configureCustomClientMetadataConverters() {
        List<String> customClientMetadata = List.of("logo_uri", "contacts");

        return (authenticationProviders) -> {
            CustomRegisteredClientConverter registeredClientConverter = new CustomRegisteredClientConverter(customClientMetadata);
            CustomClientRegistrationConverter clientRegistrationConverter = new CustomClientRegistrationConverter(customClientMetadata);

            authenticationProviders.forEach((authenticationProvider) -> {
                if (authenticationProvider instanceof OidcClientRegistrationAuthenticationProvider provider) {
                    provider.setRegisteredClientConverter(registeredClientConverter);
                    provider.setClientRegistrationConverter(clientRegistrationConverter);
                }
                if (authenticationProvider instanceof OidcClientConfigurationAuthenticationProvider provider) {
                    provider.setClientRegistrationConverter(clientRegistrationConverter);
                }
            });
        };
    }

    private Consumer<List<AuthenticationProvider>> configureAuthenticationValidator() {
        return (authenticationProviders) ->
                authenticationProviders.forEach((authenticationProvider) -> {
                    if (authenticationProvider instanceof OAuth2AuthorizationCodeRequestAuthenticationProvider oauth2Provider) {
                        Consumer<OAuth2AuthorizationCodeRequestAuthenticationContext> authenticationValidator =
                                // Override default redirect_uri validator
                                new CustomRedirectUriValidator()
                                        // Reuse default scope validator
                                        .andThen(OAuth2AuthorizationCodeRequestAuthenticationValidator.DEFAULT_SCOPE_VALIDATOR);

                        oauth2Provider.setAuthenticationValidator(authenticationValidator);
                    }
                });
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return converter;
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .jwkSetEndpoint(jwtEndpoint)
                .issuer(issuer)
                .authorizationEndpoint(OAUTH2_AUTHORIZATION_ENDPOINT)
                .tokenEndpoint(OAUTH2_TOKEN_ENDPOINT)
                .tokenIntrospectionEndpoint(OAUTH2_INTROSPECT_ENDPOINT)
                .tokenRevocationEndpoint(OAUTH2_REVOKE_ENDPOINT)
                .jwkSetEndpoint(OAUTH2_JWK_SET_ENDPOINT)
                .oidcUserInfoEndpoint(OAUTH2_USER_INFO)
                .oidcClientRegistrationEndpoint(OAUTH2_REGISTRATION_ENDPOINT)
                .oidcLogoutEndpoint(OAUTH2_LOGOUT_ENDPOINT)
                .build();
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
        return context -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType()) || OAuth2TokenType.REFRESH_TOKEN.equals(context.getTokenType())) {
                Authentication principal = context.getPrincipal();
                CustomPrincipal user = userDetailsService.loadUserByUsername(principal.getName());
                context.getClaims()
                        .claim("id", user.getId())
                        .claim("authorities", user.getPrivileges())
                        .claim("firstName", user.getFirstName())
                        .claim("lastName", user.getFirstName())
                        .claim("username", user.getUsername())
                        .claim("email", user.getEmail())
                        .claim("status", user.getStatus())
                        .claim("mobileNumber", user.getMobileNumber())
                        .claim("passwordReset", user.getPasswordReset())
                        .claim("isAdmin", user.isAdmin())
                        .claim("isUsing2FA", user.isUsing2FA())
                        .claim("fullName", user.getFullName())
                        .claim("countryCode", user.getCountry())
                        .claim("organization", user.getOrganization())
                        .claim("accountType", user.getAccountType())
                        .claim("passwordLastResetDate", user.getPasswordLastResetDate() != null ? user.getPasswordLastResetDate() : "");
            }
        };
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        JwtDecoder decoder = OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
        decodeJwt = decoder;
        return decoder;
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = RsaKeyUtils.generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    public OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator() {
        NimbusJwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSource());
        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder);
        jwtGenerator.setJwtCustomizer(tokenCustomizer());
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
    }
}
