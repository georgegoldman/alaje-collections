package com.hydrogenhr.persistence.entity;

import com.hydrogenhr.model.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity(name = "Transaction")
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Transaction extends BaseEntity{

    @Column(name = "payable_amount", nullable = false)
    private BigDecimal payableAmount;

    @Column(name = "alaje_fixed_user_charge", nullable = false)
    private BigDecimal alajeFixedUserCharge;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false, name = "transaction_status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_fk")
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk")
    private User user;

    @JoinColumn(name = "transaction_detail_fk")
    @OneToOne(fetch = FetchType.EAGER)
    private TransactionDetail transactionDetail;
}
