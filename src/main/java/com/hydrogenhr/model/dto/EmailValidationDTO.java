package com.hydrogenhr.model.dto;

import com.hydrogenhr.model.enums.ValidationStatus;
import com.hydrogenhr.model.enums.ValidationType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailValidationDTO {
    private String token;

    private ValidationStatus validationStatus;
    
    private ValidationType validationType;

    private Long userId;

}
