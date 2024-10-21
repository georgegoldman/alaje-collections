package com.hydrogenhr.service.impl;

import java.util.*;

import org.springframework.stereotype.Service;

import com.hydrogenhr.model.dto.EmailValidationDTO;
import com.hydrogenhr.persistence.entity.EmailValidation;
import com.hydrogenhr.persistence.entity.User;
import com.hydrogenhr.persistence.repository.EmailValidationRepository;
import com.hydrogenhr.persistence.repository.UserRepository;
import com.hydrogenhr.service.EmailValidationService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailValidationServiceImpl implements EmailValidationService {

    private final EmailValidationRepository emailValidationRepository;
    private final UserRepository userRepository;

    @Override
    public EmailValidation getEmailValidation(long id) {
        
        return emailValidationRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Email Validation not found with id: " + id));
    }

    @Override
    public List<EmailValidation> getAllEmailValidation() {
        return emailValidationRepository.findAll();
    }

    @Override
    public EmailValidation updateEmailValidation(Long id, EmailValidationDTO emailValidationDTO) {
        return emailValidationRepository.findById(id).map(emailValidation -> {
            // update the token, validate status, and validation type from DTO
            emailValidation.setToken(emailValidationDTO.getToken());
            emailValidation.setValidationStatus(emailValidationDTO.getValidationStatus());
            emailValidation.setValidationType(emailValidationDTO.getValidationType());

            // Update the asociated user if the user is present in the DTO
            if (emailValidationDTO.getUserId() != null ){
                User user = userRepository.findById(emailValidationDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: "+emailValidationDTO.getUserId()));
                emailValidation.setUser(user);
            }else {
                throw new IllegalArgumentException("Please the user id can not be null");
            }

            // Save and return the update entity
            return emailValidationRepository.save(emailValidation);
        }).orElseThrow(() -> new EntityNotFoundException("EmailValidation not found with id " + id));
    }

    @Override
    public EmailValidation createEmailValidation(EmailValidationDTO emailValidationDTO){
        User user  = userRepository.findById(emailValidationDTO.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found for id: "+ emailValidationDTO.getUserId()));
        
        EmailValidation emailValidation = EmailValidation.builder()
        .validationStatus(emailValidationDTO.getValidationStatus())
        .validationType(emailValidationDTO.getValidationType())
        .token("test token")
        .user(user)
        .build();
        return emailValidationRepository.save(emailValidation);
    }

}
