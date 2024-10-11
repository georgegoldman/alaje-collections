package com.hydrogenhr.persistence.entity;

import com.hydrogenhr.model.enums.ChargeType;
import jakarta.persistence.*;

@Entity(name = "AlajeFixedUserCharges")
@Table(name = "alaje_fixed_user_charges")
public class AlajeFixedUserCharges extends BaseEntity{

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
