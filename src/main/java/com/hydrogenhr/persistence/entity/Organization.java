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

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:10 PM
 */
@Entity(name = "Organisation")
@Table(name = "organisations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Organization extends BaseEntity {

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "rc_number", length = 20)
    private String rcNumber;

    @Column(name = "email", length = 120)
    private String email;

    @Column(name = "telephone", length = 16)
    private String telephone;
}
