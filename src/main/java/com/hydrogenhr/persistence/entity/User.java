package com.hydrogenhr.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hydrogenhr.model.enums.AccountType;
import com.hydrogenhr.model.enums.RegistrationStage;
import com.hydrogenhr.model.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
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

    @Column(name = "username", length = 100, unique = true, updatable = true)
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

    @Column(name = "mobile_number", length = 16, nullable = true)
    @Size(min = 7, max = 16, message = "Mobile number cannot be less than 7 characters and more than 16 characters long")
    private String mobileNumber;

    @JsonIgnore
    @Column(name = "secret", length = 150)
    private String secret;

    @Column(name = "password_last_reset_date", length = 25)
    private Timestamp passwordLastResetDate;

    @Column(name = "password_reset", length = 1)
    private boolean passwordReset;

    @Column
    private LocalDate dateOfBirth;

    @Column(name = "age")
    private int age;



    @Column(name = "is_admin", length = 1)
    private boolean isAdmin;

    @Column(name = "using_2fa", length = 1)
    private boolean isUsing2FA;

    @Column(name = "account_type", length = 15)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "registration_stage")
    @Enumerated(EnumType.STRING)
    private RegistrationStage registrationStage;

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
