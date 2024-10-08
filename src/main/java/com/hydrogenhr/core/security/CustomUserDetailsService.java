package com.hydrogenhr.core.security;

import com.hydrogenhr.config.AuthorizationServerConfig;
import com.hydrogenhr.core.utils.Oauth2Utils;
import com.hydrogenhr.core.utils.TranslatorUtil;
import com.hydrogenhr.model.enums.AccountType;
import com.hydrogenhr.model.enums.AppStatus;
import com.hydrogenhr.model.response.CustomPrincipal;
import com.hydrogenhr.persistence.entity.Organization;
import com.hydrogenhr.persistence.entity.User;
import com.hydrogenhr.persistence.repository.OrganizationRepository;
import com.hydrogenhr.persistence.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:31 PM
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final AuthorityDetails authorityDetails;
    private final LoginAttemptService loginAttemptService;

    @Getter
    private String username;

    @Override
    public CustomPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {

        if (loginAttemptService.isBlocked(username)) {
            throw new BadCredentialsException(TranslatorUtil.toLocale("app.exception.account-blocked"));
        }
        this.username = username;

        String principal = Oauth2Utils.getPrincipal(AuthorizationServerConfig.decodeJwt);
        if (principal != null) {
            username = principal;
            this.username = username;
        }
        User user;
        if (username.contains("@")) {
            user = userRepository.findByEmail(username.toLowerCase(Locale.ENGLISH).trim()).orElseThrow(() ->
                    new BadCredentialsException(TranslatorUtil.toLocale("app.exception.incorrect-username")));
        } else {
            user = userRepository.findByUsername(username.toLowerCase(Locale.ENGLISH).trim()).orElseThrow(() ->
                    new BadCredentialsException(TranslatorUtil.toLocale("app.exception.incorrect-username")));
        }

        Organization organization = organizationRepository.findById(user.getOrganization().getId()).orElseThrow(() ->
                new BadCredentialsException(TranslatorUtil.toLocale("app.exception.application.not-setup")));

        if (organization.getStatus().equals(AppStatus.ACTIVE)) {
            if (user.getAccountType().equals(AccountType.NORMAL_ACCOUNT)) {
                if (user.getStatus().getStatus().equals(AppStatus.ACTIVE.getStatus())) {
                    UserDetails userDetails = org.springframework.security.core.userdetails.User
                            .withUsername(user.getUsername())
                            .password(user.getPassword())
                            .authorities(authorityDetails.getAuthorities(user))
                            .accountLocked(false)
                            .accountExpired(false)
                            .credentialsExpired(false)
                            .disabled(false)
                            .build();
                    return new CustomPrincipal(userDetails.getAuthorities(), user);
                } else {
                    throw new BadCredentialsException(TranslatorUtil.toLocale("app.exception.user-inactive"));
                }
            } else {
                throw new BadCredentialsException(TranslatorUtil.toLocale("app.exception.service-account-login"));
            }
        } else {
            throw new BadCredentialsException(TranslatorUtil.toLocale("app.exception.application.not-active"));
        }
    }
}
