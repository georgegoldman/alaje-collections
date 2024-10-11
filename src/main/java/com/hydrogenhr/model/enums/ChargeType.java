package com.hydrogenhr.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChargeType {
    PERCENTAGE('0'),
    FLAT('1'),
    BOTH('2');

    private Character chargeType;
}
