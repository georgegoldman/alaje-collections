package com.hydrogenhr.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RevenueSource {
    TAX('0'),
    FEES('1'),
    GRANTS('2'),
    DONATIONS('3'),
    SUBSCRIPTIONS('4'),
    SALES('5'),
    FINES('6'),
    COMMISSIONS('7'),
    INVESTMENTS('8'),
    ROYALTIES('9');

    private Character revenueSource;

}
