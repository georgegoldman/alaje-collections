package com.hydrogenhr.service;

import java.util.*;

import com.hydrogenhr.model.dto.EmailValidationDTO;
import com.hydrogenhr.persistence.entity.EmailValidation;

public interface EmailValidationService {

    EmailValidation getEmailValidation(long id);

    List<EmailValidation> getAllEmailValidation();

    EmailValidation updateEmailValidation(Long id, EmailValidationDTO emailValidationDTO);

    EmailValidation createEmailValidation(EmailValidationDTO emailValidationDTO);

}
