package com.hydrogenhr.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity(name = "Invoice")
@Table(name ="invoice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Invoice extends BaseEntity {

    @Column(name = "amount_due")
    private double amountDue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "revenue_setup")
    private RevenueSetup revenueSetup;
}
