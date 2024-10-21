package com.hydrogenhr.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydrogenhr.model.dto.EmailValidationDTO;
import com.hydrogenhr.persistence.entity.EmailValidation;
import com.hydrogenhr.service.EmailValidationService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dev/email-validation")
@RequiredArgsConstructor
public class EmailValidationController {

    private final EmailValidationService emailValidationService;

    @GetMapping
    public ResponseEntity<List<EmailValidation>> getAllVerifiedEmail(){
        List<EmailValidation> emailValidations = emailValidationService.getAllEmailValidation();
        return new ResponseEntity<>(emailValidations, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<EmailValidation> getEmailValidationById(@PathVariable long id){
        EmailValidation emailValidation = emailValidationService.getEmailValidation(id);
        return ResponseEntity.ok(emailValidation);
    }

    @PostMapping
    public ResponseEntity<EmailValidation> createEmailValidation(@RequestBody EmailValidationDTO emailValidationDTO) {
        EmailValidation emailValidation = emailValidationService.createEmailValidation(emailValidationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(emailValidation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailValidation> updateEmailValidation(@PathVariable Long id, @RequestBody EmailValidationDTO emailValidationDTO) {
        EmailValidation emailValidation = emailValidationService.updateEmailValidation(id, emailValidationDTO);
        return ResponseEntity.status(HttpStatus.OK).body(emailValidation);
    }


}
