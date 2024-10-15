package com.hydrogenhr.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTo {
    private BigDecimal payableAmount;
    private BigDecimal totalAmount;
    private int alajeFixedUserCharge;
    private String transactionStatus;
    private Long userId;
}
