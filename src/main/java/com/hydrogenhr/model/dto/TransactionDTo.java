package com.hydrogenhr.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTo {
    private int inPercentage;
    private double flatRate;
    private double payableAmount;
    private Long userId;
}
