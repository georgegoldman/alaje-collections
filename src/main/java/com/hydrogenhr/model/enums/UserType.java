package com.hydrogenhr.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum UserType {
    INDIVIDUAL('0'),
    BUSINESS('1'),
    GOVERNMENT('2');

    private Character userType;
}
