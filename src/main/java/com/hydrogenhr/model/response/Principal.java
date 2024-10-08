package com.hydrogenhr.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 10:56 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Principal implements Serializable {
    private String id;
    private String iss;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String email;
    private String telephone;
    private Boolean passwordReset;
    private String fullName;
    private boolean isAdmin;
    private boolean isUsing2FA;
    private String status;
    private String application;
    private Set<String> authorities;
    private Set<String> scope;
}
