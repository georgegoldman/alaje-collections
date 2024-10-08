package com.hydrogenhr.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:07 PM
 */
@Entity(name = "Privilege")
@Table(name = "privileges")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Privilege extends BaseEntity implements GrantedAuthority {

    @Column(name = "name", length = 100, unique = true)
    private String name;

    @Column(name = "description", length = 120)
    private String description;

    @Column(name = "is_global", length = 1)
    private boolean global;

    @Override
    public String getAuthority() {
        return name;
    }
}
