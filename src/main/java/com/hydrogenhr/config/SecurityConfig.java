package com.hydrogenhr.config;

import com.hydrogenhr.core.security.CustomCorsFilter;
import com.hydrogenhr.core.security.CustomRememberMeService;
import com.hydrogenhr.core.security.CustomUserDetailsService;
import com.hydrogenhr.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import static com.hydrogenhr.core.constants.AppConstants.Oauth2Uri.ASSETS;
import static com.hydrogenhr.core.constants.AppConstants.Oauth2Uri.LOGIN;
import static com.hydrogenhr.core.constants.AppConstants.Oauth2Uri.OAUTH2_TOKEN_ENDPOINT;
import static com.hydrogenhr.core.constants.AppConstants.Oauth2Uri.SWAGGER;
import static com.hydrogenhr.core.constants.AppConstants.SecurityConstants.REMEMBER_ME;
import static com.hydrogenhr.core.constants.AppConstants.SecurityConstants.SECURITY_POLICY;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:43 PM
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .oauth2ResourceServer((resourceServer) -> resourceServer.jwt(Customizer.withDefaults()))
                .formLogin((formLogin -> formLogin.loginPage(LOGIN).permitAll()))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(ASSETS, LOGIN, SWAGGER, OAUTH2_TOKEN_ENDPOINT).permitAll()
                        .anyRequest().authenticated())
                .headers((header) -> header
                        .defaultsDisabled()
                        .cacheControl(HeadersConfigurer.CacheControlConfig::disable)
                        .xssProtection(HeadersConfigurer.XXssConfig::disable)
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                        .httpStrictTransportSecurity(strict ->
                                strict.includeSubDomains(true).maxAgeInSeconds(31536000)
                        ).contentSecurityPolicy(contentPolicy ->
                                contentPolicy.policyDirectives(SECURITY_POLICY)
                                        .reportOnly()))
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .rememberMe(rememberMe -> rememberMe.rememberMeServices(rememberMeService()))
                .addFilterBefore(new CustomCorsFilter(), ChannelProcessingFilter.class)
                .build();
    }

    @Bean
    public RememberMeServices rememberMeService() {
        return new CustomRememberMeService(REMEMBER_ME, userDetailsService, new InMemoryTokenRepositoryImpl(), userRepository);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.httpFirewall(allowUrlEncodedSlashHttpFirewall())
                .ignoring()
                .requestMatchers("/v1/api-docs")
                .requestMatchers("/v2/api-docs")
                .requestMatchers("/swagger-resources/**")
                .requestMatchers("/swagger-ui/index.html")
                .requestMatchers("/swagger-ui.html")
                .requestMatchers("/configuration/**")
                .requestMatchers("/webjars/**")
                .requestMatchers("/images/**")
                .requestMatchers("/css/**")
                .requestMatchers("/assets/**")
                .requestMatchers("/favicon.ico")
                .requestMatchers("/public/**")
                .requestMatchers("/actuator/**");
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
