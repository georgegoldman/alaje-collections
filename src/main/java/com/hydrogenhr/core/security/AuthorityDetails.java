package com.hydrogenhr.core.security;

import com.hydrogenhr.model.enums.AppStatus;
import com.hydrogenhr.persistence.entity.Privilege;
import com.hydrogenhr.persistence.entity.Role;
import com.hydrogenhr.persistence.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static com.hydrogenhr.core.constants.AppConstants.Authentication.PERMISSION;
import static com.hydrogenhr.core.constants.AppConstants.Authentication.ROLE;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:32 PM
 */
@Service
@RequiredArgsConstructor
public class AuthorityDetails {

    private static Set<String> getRoles(User user) {
        Set<String> roles = new HashSet<>();
        Set<Role> collection = new HashSet<>(user.getRoles());
        for (Role role : collection) {
            if (role.getStatus().getStatus().equals(AppStatus.ACTIVE.getStatus())) {
                roles.add(role.getAuthority().toUpperCase(Locale.ENGLISH));
            } else {
                roles.remove(role.getAuthority());
            }
        }
        return roles;
    }

    private static Set<String> getPrivileges(User user) {
        Set<String> privileges = new HashSet<>();
        Set<Privilege> collection = new HashSet<>();
        for (Role role : user.getRoles()) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege privilege : collection) {
            if (privilege.getStatus().getStatus().equals(AppStatus.ACTIVE.getStatus())) {
                privileges.add(privilege.getAuthority().toUpperCase(Locale.ENGLISH));
            } else {
                privileges.remove(privilege.getAuthority());
            }
        }
        return privileges;
    }

    private static Set<GrantedAuthority> grantedAuthorities(Set<String> privileges, Set<String> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(PERMISSION + privilege.trim().toUpperCase(Locale.ENGLISH)));
        }

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(ROLE + role.trim().toUpperCase(Locale.ENGLISH)));
        }
        return authorities;
    }

    public Set<? extends GrantedAuthority> getAuthorities(User user) {
        return grantedAuthorities(getPrivileges(user), getRoles(user));
    }

}
