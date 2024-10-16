package com.hydrogenhr.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {
    private String chargeType;
    private int inPercentage;
    private int flatRate;
    private Long transactionId;
    private int payableAmount;
    private int totalAmount;
    private int alajeFixedUserCharge;
    private String transactionStatus;
    private Long userId;

}
