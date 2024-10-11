package com.hydrogenhr.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationStatus {

    VALID('0'),
    INVALID('1');

    private final Character validationStatus;
}
