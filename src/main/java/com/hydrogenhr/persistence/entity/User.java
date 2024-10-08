package com.hydrogenhr.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hydrogenhr.model.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:06 PM
 */
@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends BaseEntity {

    @Column(name = "username", length = 100, unique = true, updatable = false)
    private String username;

    @Column(name = "password", columnDefinition = "text")
    @JsonIgnore
    private String password;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email", length = 120, unique = true)
    @Email(message = "Please provide a valid email address")
    private String email;

    @Column(name = "telephone", length = 16)
    @Size(min = 7, max = 16, message = "Telephone number cannot be less than 7 characters and more than 16 characters long")
    private String telephone;

    @JsonIgnore
    @Column(name = "secret", length = 150)
    private String secret;

    @Column(name = "password_last_reset_date", length = 25)
    private Timestamp passwordLastResetDate;

    @Column(name = "password_reset", length = 1)
    private boolean passwordReset;

    @Column(name = "is_admin", length = 1)
    private boolean isAdmin;

    @Column(name = "using_2fa", length = 1)
    private boolean isUsing2FA;

    @Column(name = "account_type", length = 15)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_fk")
    private Country country;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_fk")
    private Organization organization;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns =
    @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns =
    @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
}
