package com.hydrogenhr.core.installer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydrogenhr.core.utils.AppUtils;
import com.hydrogenhr.core.utils.SecretKeyGenerator;
import com.hydrogenhr.model.enums.AccountType;
import com.hydrogenhr.persistence.entity.Client;
import com.hydrogenhr.persistence.entity.Country;
import com.hydrogenhr.persistence.entity.Organization;
import com.hydrogenhr.persistence.entity.Privilege;
import com.hydrogenhr.persistence.entity.Role;
import com.hydrogenhr.persistence.entity.User;
import com.hydrogenhr.persistence.repository.ClientRepository;
import com.hydrogenhr.persistence.repository.CountryRepository;
import com.hydrogenhr.persistence.repository.OrganizationRepository;
import com.hydrogenhr.persistence.repository.PrivilegeRepository;
import com.hydrogenhr.persistence.repository.RoleRepository;
import com.hydrogenhr.persistence.repository.UserRepository;
import io.micrometer.core.instrument.util.IOUtils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/30/24
 * Time: 3:22 PM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultInstaller implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final OrganizationRepository organizationRepository;
    private final ClientRepository clientRepository;
    private final CountryRepository countryRepository;
    private final PasswordEncoder passwordEncoder;
    private boolean alreadySetup = false;
    private Country country;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

        createCountryIfNotExist();
        Optional<Country> optionalCountry = countryRepository.findByNumCode("566");
        optionalCountry.ifPresent(value -> country = value);

        Organization organization = creatOrganisationIfNotExist("HydrogenHR", "A001");
        Organization customerOrganization = creatOrganisationIfNotExist("Ondo State Govt", "C001");

        Privilege createUserPrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.OauthUserAuthority.CREATE_USER.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.OauthUserAuthority.CREATE_USER.getAuthority().replaceAll("_", " ")), true, organization);
        Privilege updateUserPrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.OauthUserAuthority.UPDATE_USER.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.OauthUserAuthority.UPDATE_USER.getAuthority().replaceAll("_", " ")), true, organization);
        Privilege changeUserPasswordPrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.OauthUserAuthority.CHANGE_PASSWORD.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.OauthUserAuthority.CHANGE_PASSWORD.getAuthority().replaceAll("_", " ")), true, organization);
        Privilege resetUserPasswordPrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.OauthUserAuthority.RESET_USER_PASSWORD.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.OauthUserAuthority.RESET_USER_PASSWORD.getAuthority().replaceAll("_", " ")), true, organization);
        Privilege viewUserPrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.OauthUserAuthority.VIEW_USER.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.OauthUserAuthority.VIEW_USER.getAuthority().replaceAll("_", " ")), true, organization);
        Privilege viewUsersPrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.OauthUserAuthority.VIEW_USERS.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.OauthUserAuthority.VIEW_USERS.getAuthority().replaceAll("_", " ")), true, organization);

        Privilege createRolePrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.RoleAuthority.CREATE_ROLE.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.RoleAuthority.CREATE_ROLE.getAuthority().replaceAll("_", " ")), true, organization);
        Privilege updateRolePrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.RoleAuthority.UPDATE_ROLE.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.RoleAuthority.UPDATE_ROLE.getAuthority().replaceAll("_", " ")), true, organization);
        Privilege viewRolePrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.RoleAuthority.VIEW_ROLE.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.RoleAuthority.VIEW_ROLE.getAuthority().replaceAll("_", " ")), true, organization);
        Privilege viewRolesPrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.RoleAuthority.VIEW_ROLES.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.RoleAuthority.VIEW_ROLES.getAuthority().replaceAll("_", " ")), true, organization);

        Privilege createPrivilegePrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.PrivilegeAuthority.CREATE_PRIVILEGE.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.PrivilegeAuthority.CREATE_PRIVILEGE.getAuthority().replaceAll("_", " ")), true, organization);
        Privilege updatePrivilegePrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.PrivilegeAuthority.UPDATE_PRIVILEGE.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.PrivilegeAuthority.UPDATE_PRIVILEGE.getAuthority().replaceAll("_", " ")), true, organization);
        Privilege viewPrivilegePrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.PrivilegeAuthority.VIEW_PRIVILEGE.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.PrivilegeAuthority.VIEW_PRIVILEGE.getAuthority().replaceAll("_", " ")), true, organization);
        Privilege viewPrivilegesPrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.PrivilegeAuthority.VIEW_PRIVILEGES.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.PrivilegeAuthority.VIEW_PRIVILEGES.getAuthority().replaceAll("_", " ")), true, organization);

        Privilege createApplicationPrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.OrganizationAuthority.CREATE_ORGANIZATION.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.OrganizationAuthority.CREATE_ORGANIZATION.getAuthority().replaceAll("_", " ")), true, organization);
        Privilege updateApplicationPrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.OrganizationAuthority.UPDATE_ORGANIZATION.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.OrganizationAuthority.UPDATE_ORGANIZATION.getAuthority().replaceAll("_", " ")), true, organization);
        Privilege viewApplicationPrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.OrganizationAuthority.VIEW_ORGANIZATION.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.OrganizationAuthority.VIEW_ORGANIZATION.getAuthority().replaceAll("_", " ")), true, organization);
        Privilege viewApplicationsPrivilege = createPrivilegeIfNotExist(SystemAuthority.UserAuthority.OrganizationAuthority.VIEW_ORGANIZATIONS.getAuthority(), StringUtils.capitalize(SystemAuthority.UserAuthority.OrganizationAuthority.VIEW_ORGANIZATIONS.getAuthority().replaceAll("_", " ")), true, organization);

        Set<Privilege> adminPrivileges = Set.of(
                createUserPrivilege, updateUserPrivilege, changeUserPasswordPrivilege, resetUserPasswordPrivilege, viewUserPrivilege, viewUsersPrivilege,
                createRolePrivilege, updateRolePrivilege, viewRolePrivilege, viewRolesPrivilege,
                createPrivilegePrivilege, updatePrivilegePrivilege, viewPrivilegePrivilege, viewPrivilegesPrivilege,
                createApplicationPrivilege, updateApplicationPrivilege, viewApplicationPrivilege, viewApplicationsPrivilege);

        Set<Privilege> userPrivileges = Set.of(
                createUserPrivilege, updateUserPrivilege, changeUserPasswordPrivilege, resetUserPasswordPrivilege, viewUserPrivilege, viewUsersPrivilege);

        Role superAdminRole = createRoleIfNotExist(SystemAuthority.VdmsSystemAuthority.GenericAuthorities.SUPER_ADMIN.getAuthority(), StringUtils.capitalize(SystemAuthority.VdmsSystemAuthority.GenericAuthorities.SUPER_ADMIN.getAuthority().replaceAll("_", " ")), adminPrivileges, false, organization);
        Role organizationAdminRole = createRoleIfNotExist(SystemAuthority.VdmsSystemAuthority.GenericAuthorities.ORGANIZATION_ADMIN.getAuthority(), StringUtils.capitalize(SystemAuthority.VdmsSystemAuthority.GenericAuthorities.ORGANIZATION_ADMIN.getAuthority().replaceAll("_", " ")), userPrivileges, true, customerOrganization);
        Role userRole = createRoleIfNotExist(SystemAuthority.VdmsSystemAuthority.GenericAuthorities.USER.getAuthority(), StringUtils.capitalize(SystemAuthority.VdmsSystemAuthority.GenericAuthorities.USER.getAuthority().replaceAll("_", " ")), Set.of(), true, customerOrganization);

        createUserIfNotExist("superadmin", "welcome", "Hydrogen", "Admin", "superadmin@gmail.com", "07030239942", Set.of(superAdminRole), organization, true, country);
        createUserIfNotExist("manager", "welcome", "Organization", "Manager", "manager@gmail.com", "07030239942", Set.of(organizationAdminRole), organization, true, country);
        createUserIfNotExist("user", "welcome", "Guest", "User", "user@gmail.com", "07030239942", Set.of(userRole), organization, false, country);

        createClientIfNotExist(applicationName, "welcome");

        alreadySetup = true;
    }

    private Privilege createPrivilegeIfNotExist(String name, String desc, boolean global, Organization organization) {
        return privilegeRepository.findByName(name).orElseGet(() -> privilegeRepository.save(Privilege.builder()
                .name(name)
                .description(desc)
                .global(global)
                .createdBy("system")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build()));
    }

    private Role createRoleIfNotExist(String name, String desc, Set<Privilege> privileges, boolean global, Organization organization) {
        return roleRepository.findByName(name).orElseGet(() -> roleRepository.save(Role.builder()
                .name(name)
                .description(desc)
                .privileges(privileges)
                .global(global)
                .organization(organization)
                .createdBy("system")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build()));
    }

    private Organization creatOrganisationIfNotExist(String name, String rcNo) {
        return organizationRepository.findByName(name).orElseGet(() -> organizationRepository.save(Organization.builder()
                .name(name)
                .rcNumber(rcNo)
                .createdBy("system")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build()));
    }

    private User createUserIfNotExist(String username, String password, String firstName, String lastName, String email,
                                      String mobileNumber, Set<Role> roles, Organization organization, boolean isAdmin, Country country) {
        return userRepository.findByUsername(username).orElseGet(() -> userRepository.save(User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .mobileNumber(mobileNumber)
                .roles(roles)
                .organization(organization)
                .secret(SecretKeyGenerator.generateSecretKey())
                .isAdmin(isAdmin)
                .passwordReset(true)
                .accountType(AccountType.NORMAL_ACCOUNT)
                .country(country)
                .createdBy("system")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build()));
    }

    private Client createClientIfNotExist(String clientId, String secret) {
        return clientRepository.findByClientId(clientId).orElseGet(() -> clientRepository.save(Client.builder()
                .clientId(clientId.trim())
                .clientName(clientId.trim())
                .clientSecret(passwordEncoder.encode(secret.trim()))
                .clientSecretExpiresAt(Instant.now().plusSeconds(Duration.ofDays(365).toSeconds()))
                .clientIdIssuedAt(Instant.now())
                .authorizationGrantTypes("authorization_code,refresh_token,client_credentials,custom_password,urn:ietf:params:oauth:grant-type:jwt-bearer")
                .clientAuthenticationMethods("client_secret_basic,client_secret_post")
                .scopes("openid,profile,read,write,api.read,api.write,client.read,client.create")
                .redirectUris("https://oidcdebugger.com/debug,https://oauthdebugger.com/debug,https://getpostman.com/oauth2/callback")
                .postLogoutRedirectUris("http://127.0.0.1:8099/login")
                .tokenSettings("""
                        {"@class":"java.util.Collections$UnmodifiableMap",
                        "settings.token.reuse-refresh-tokens":true,
                        "settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],
                        "settings.token.access-token-time-to-live":["java.time.Duration",86400.000000000],
                        "settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat",
                        "value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],
                        "settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],
                        "settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}
                        """)
                .clientSettings("""
                        {"@class":"java.util.Collections$UnmodifiableMap",
                        "settings.client.require-proof-key":false,
                        "settings.client.require-authorization-consent":true}
                         """)
                .build()));
    }

    private List<Country> createCountryIfNotExist() {
        long country = countryRepository.count();
        if (country == 0) {
            TypeReference<List<Country>> typeReference = new TypeReference<>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            };
            Resource resource = new ClassPathResource("json/countries.json");
            try {
                List<Country> countries = AppUtils.fromJson(IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8), typeReference);
                return countryRepository.saveAll(countries);
            } catch (IOException e) {
                log.error("Error reading and writing country: {}", e.getMessage());
            }
        }
        return Collections.emptyList();
    }
}
