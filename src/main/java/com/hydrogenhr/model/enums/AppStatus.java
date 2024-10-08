package com.hydrogenhr.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 9/29/24
 * Time: 11:02 PM
 */
@Getter
@AllArgsConstructor
public enum AppStatus {
    ACTIVE('0'),
    INACTIVE('1'),
    DELETED('2'),
    PENDING('3'),
    APPROVED('4'),
    REJECTED('5'),
    CANCELED('6'),
    LOCKED('7');

    private Character status;
}
