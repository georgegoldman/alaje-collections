package com.hydrogenhr.persistence.entity;

import com.hydrogenhr.model.enums.ChargeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "FixedCharges")
@Table(name = "fixed_charges")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FixedCharges extends BaseEntity{

    @Column(name = "charge_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ChargeType chargeType;

    @Column(name = "in_percentage")
    private int inPercentage;

    @Column(name = "flat_rate")
    private int flatRate;

    @Column(name = "both")
    private boolean both;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_fk")
    private Transaction transaction;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk")
    private User user;
}
