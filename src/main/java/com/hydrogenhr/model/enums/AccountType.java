package com.hydrogenhr.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:09 PM
 */
@Getter
@AllArgsConstructor
public enum AccountType {
    NORMAL_ACCOUNT('0'),
    SERVICE_ACCOUNT('1');

    private Character accountType;
}
