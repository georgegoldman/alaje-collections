package com.hydrogenhr.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationType {
    EMAIL_VERIFICATION('0'),
    PASSWORD_RESET('1');

    private final Character validationType;
}
