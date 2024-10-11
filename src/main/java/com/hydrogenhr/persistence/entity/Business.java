package com.hydrogenhr.persistence.entity;

import com.hydrogenhr.model.enums.BusinessType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "Business")
@Table(name = "business")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Business extends BaseEntity {

    @Column(name = "business_type")
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    @Column(name = "business_description")
    private String businessDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;
}
