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
 * Time: 11:05 PM
 */
@Entity(name = "Country")
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class Country extends BaseEntity {
    @Column(name = "iso", length = 5)
    private String iso;

    @Column(name = "name", length = 120)
    private String name;

    @Column(name = "nick_name", length = 120)
    private String niceName;

    @Column(name = "iso3", length = 5)
    private String iso3;

    @Column(name = "num_code", length = 6)
    private String numCode;

    @Column(name = "phone_code", length = 6)
    private String phoneCode;

    @Column(name = "currency", length = 6)
    private String currency;
}
