package com.hydrogenhr.resource;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hydrogenhr.model.dto.SQAnswerDTO;
import com.hydrogenhr.persistence.entity.SQAnswer;
import com.hydrogenhr.persistence.entity.SecurityQuestion;
import com.hydrogenhr.service.SQAnswerService;
import com.hydrogenhr.service.SecurityQuestionService;

// import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dev/security-question")
@RequiredArgsConstructor
public class SecurityQuestionController {
    private final SecurityQuestionService securityQuestionService;
    private final SQAnswerService sqAnswerService;

    @GetMapping
    public ResponseEntity<List<SecurityQuestion>> getAllSecurityQuestions(){
        List<SecurityQuestion> securityQuestions = securityQuestionService.getAllSecurityQuestion();
        return new ResponseEntity<>(securityQuestions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SecurityQuestion> createSecurityQuestion(@RequestBody SecurityQuestion question){
        
        SecurityQuestion newSecurityQuestion = securityQuestionService.createQuestion(question);
        return new ResponseEntity<>(newSecurityQuestion, HttpStatus.CREATED);
    }

    @PostMapping("/answer")
    public ResponseEntity<SQAnswer> createAnswer(@RequestBody SQAnswerDTO sqAnswerDTO){
        SQAnswer newSQAnswer = sqAnswerService.createAnswer(sqAnswerDTO);
        return new ResponseEntity<>(newSQAnswer, HttpStatus.CREATED);
    }

    

    @GetMapping("/{id}")
    public ResponseEntity<SecurityQuestion> getQuestion(@PathVariable UUID id){
        Optional <SecurityQuestion> securityQuestion =  securityQuestionService.getQuestionById(id);

        return securityQuestion.map(question -> new ResponseEntity<>(question, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/verify-answer")
    public ResponseEntity<Boolean> verifyAnswer(@RequestBody SQAnswerDTO sqAnswerDTO){
        return new ResponseEntity<>(sqAnswerService.verifyQuestion(sqAnswerDTO), HttpStatus.OK) ;
    }
    
}
