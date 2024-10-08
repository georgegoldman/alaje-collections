package com.hydrogenhr.model.response;

import com.hydrogenhr.core.constants.AppConstants;
import com.hydrogenhr.core.utils.AppUtils;
import com.hydrogenhr.model.enums.AccountType;
import com.hydrogenhr.persistence.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:27 PM
 */
@Getter
@Setter
@ToString
public class CustomPrincipal extends org.springframework.security.core.userdetails.User {

    private final String id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String username;
    private final String email;
    private final String telephone;
    private final Boolean passwordReset;
    private final String fullName;
    private final boolean isAdmin;
    private final boolean isUsing2FA;
    private final String status;
    private final Set<String> privileges;
    private final String country;
    private final String organization;
    private final AccountType accountType;
    private final String passwordLastResetDate;

    public CustomPrincipal(Collection<? extends GrantedAuthority> authorities, User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
        this.id = user.getId().toString();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.lastName = user.getLastName();
        this.telephone = user.getTelephone();
        this.email = user.getEmail();
        this.isAdmin = user.isAdmin();
        this.isUsing2FA = user.isUsing2FA();
        this.status = String.valueOf(user.getStatus());
        this.passwordReset = user.isPasswordReset();
        this.privileges = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        this.fullName = user.getFirstName() + " " + user.getLastName();
        this.country = user.getCountry() == null ? "" : user.getCountry().getNumCode();
        this.organization = user.getOrganization().getId().toString();
        this.accountType = user.getAccountType();
        this.passwordLastResetDate = AppUtils.parseTime(user.getPasswordLastResetDate());
    }
}
