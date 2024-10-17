package com.hydrogenhr.persistence.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hydrogenhr.model.enums.ValidationStatus;
import com.hydrogenhr.model.enums.ValidationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Project title: foundation-service
 *
 * @author johnonyedikachi
 * Date: -
 * Time: -
 */

@Entity(name = "EmailValidation")
@Table(name = "email_validation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EmailValidation extends BaseEntity {
    
    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "validation_status")
    @Enumerated(EnumType.STRING)
    private ValidationStatus validationStatus;

    @Column(name = "validation_type")
    @Enumerated(EnumType.STRING)
    private ValidationType validationType;

    @Column(name = "activation_initiated")
    @JsonIgnore
    private LocalDateTime activationInitiated;

    @Column(name = "confirnmed_at")
    @JsonIgnore
    private LocalDateTime confirmedAt;

    @JoinColumn(name = "user_fk")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;


}
