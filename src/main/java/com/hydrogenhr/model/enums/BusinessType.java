package com.hydrogenhr.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessType {
    SME('0'),
    SOLE_PROPRIETORSHIP('1'),
    COMPANY('2'),
    LARGE_CORPORATE('3'),
    MULTINATIONAL('4'),
    STARTUP('5');

    private Character businessType;
}
