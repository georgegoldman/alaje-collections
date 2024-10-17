package com.hydrogenhr.persistence.entity;


import java.math.BigDecimal;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "TransactionDetail")
@Table(name = "transaction_detail")
public class TransactionDetail extends BaseEntity {

    @Column(name = "transaction_reference")
    private String transactionReference;

    @Column(name = "currency")
    private String currency;

    @Column(name = "amount")
    private  BigDecimal amount;

    @Column(name = "charged_amount")
    private BigDecimal chargedAmount;

    @Column(name = "charge_response_code")
    private String chargedResponseCode;

    @Column(name = "charged_response_message")
    private String chargedResponseMessage;

    @Column(name = "transaction_status")
    private String transactionStatus;

    @JoinColumn(name = "transaction")
    @OneToOne(fetch = FetchType.EAGER)
    private Transaction transaction;

}
