package com.hydrogenhr.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name="Status")
@Table(name = "status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Status extends BaseEntity {

    @Column(name = "registered")
    private boolean registered;

    @Column(name = "mobile_number_verified")
    private boolean mobileNumberVerified;

    @Column(name = "email_verified")
    private boolean emailVerified;

    @Column(name = "onboarding_completed")
    private boolean onboardingCompleted;

    @JoinColumn(name = "user_fk")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

}
