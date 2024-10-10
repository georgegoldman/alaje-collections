package com.hydrogenhr.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegistrationStage {
    REGISTERED('0'),
    CREATED('1'),
    MOBILE_NUMBER_VERIFIED('2'),
    EMAIL_ADDRESS_VERIFIED('3'),
    USER_PROFILE_UPDATED('4'),
    COMPLETED('5');

    private Character registrationStage;
}
