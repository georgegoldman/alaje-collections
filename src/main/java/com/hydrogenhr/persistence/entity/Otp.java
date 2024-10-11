package com.hydrogenhr.persistence.entity;

import com.hydrogenhr.model.enums.ValidationStatus;
import com.hydrogenhr.model.enums.ValidationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Entity(name = "Otp")
@Table(name = "otp")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Otp extends BaseEntity{

    @Column(nullable = false, name = "one_time_password")
    private String oneTimePassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "validation_status", nullable = false)
    private ValidationStatus validationStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "validation_type", nullable = false)
    private ValidationType validationType;

    @Column(name = "activation_initiated", nullable = false)
    private Timestamp activationInitiated;

    @Column(name = "confirmed_at")
    private Timestamp confirmedAt;

    @JoinColumn(name = "user_fk")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
