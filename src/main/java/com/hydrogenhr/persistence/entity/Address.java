package com.hydrogenhr.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "Address")
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Address extends BaseEntity{

    @Column(name = "building_name", length = 45)
    @Size(max = 45, message = "Building name cannot exceed 45 characters")
    private String buildingName;

    @Column(name = "street_number", length = 20)
    @Size(max = 20, message = "Street number cannot exceed 20 characters")
    private String streetNumber;

    @Column(name = "zip_code", length = 45)
    @Size(max = 45, message = "Zipcode cannot exceed 25 characters")
    private String zipCode;

    @Column(name = "street_name", nullable = false)
    private String streetName;

    @Column(name = "landmark", length = 100)
    @Size(max = 100, message = "Landmark cannot exceed 100")
    private String landmark;

    @Column(name = "description", length = 100)
    @Size(max = 100)
    private String description;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "lga", nullable = false)
    private String lga;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @JoinColumn(name = "user_fk")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

}
