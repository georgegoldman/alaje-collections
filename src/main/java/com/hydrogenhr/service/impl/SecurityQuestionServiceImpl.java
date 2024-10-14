package com.hydrogenhr.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import java.util.UUID;

import com.hydrogenhr.persistence.entity.SecurityQuestion;
import com.hydrogenhr.persistence.repository.SecurityQuestionRepository;
import com.hydrogenhr.service.SecurityQuestionService;
// import java.io.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityQuestionServiceImpl implements SecurityQuestionService {

    private final SecurityQuestionRepository securityQuestionRepository;

    @Override
    public SecurityQuestion createQuestion(SecurityQuestion question) {
        // check if question exist to avoid duplicate
        Optional<SecurityQuestion> existingQuestionbyQuestion = securityQuestionRepository.findByQuestion(question.getQuestion());

        if (existingQuestionbyQuestion.isPresent()){
            throw new IllegalArgumentException("Question already exist");
        }
        question.setQuestion(question.getQuestion().toLowerCase()); 
        return securityQuestionRepository.save(question);
    }

    @Override
    public List<SecurityQuestion> getAllSecurityQuestion() {
        return securityQuestionRepository.findAll();
    }

    @Override
    public Optional<SecurityQuestion> getQuestionById(UUID id) {
        return securityQuestionRepository.findById(id);
        
    }

    @Override
    public Optional<SecurityQuestion> getQuestionByQuestion(String question) {
       return securityQuestionRepository.findByQuestion(question.toLowerCase());
        
    }

}
