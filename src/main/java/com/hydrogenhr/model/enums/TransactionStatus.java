package com.hydrogenhr.model.enums;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionStatus {
    PENDING('0'),
    COMPLETED('1'),
    FAILED('2'),
    CANCELLED('3'),
    REFUNDED('4');

    private Character transactionStatus;
}
