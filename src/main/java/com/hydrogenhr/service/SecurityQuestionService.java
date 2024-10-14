package com.hydrogenhr.service;

import com.hydrogenhr.persistence.entity.SecurityQuestion;

import java.util.Optional;


import java.util.UUID;
import java.util.List;

public interface SecurityQuestionService {

    SecurityQuestion createQuestion(SecurityQuestion securityQuestion);

    List<SecurityQuestion> getAllSecurityQuestion();

    Optional<SecurityQuestion> getQuestionById(UUID id);

    Optional<SecurityQuestion> getQuestionByQuestion(String question);
    
}
