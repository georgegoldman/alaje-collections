package com.hydrogenhr.model.exceptions;

import com.hydrogenhr.persistence.entity.User;


public class UserRegistrationValidator {
    public static void validate(User user) {
        if (user.getEmail() == null && user.getMobileNumber() == null) {
            throw new IllegalArgumentException("Either email or telephone must be provided");
        }else if (user.getEmail() != null && user.getMobileNumber() != null){
            throw new IllegalArgumentException("Just provide only email or telephone must be provided");
        }
    }
}
