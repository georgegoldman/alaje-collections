package com.hydrogenhr.service.impl;

import org.hibernate.query.IllegalQueryOperationException;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hydrogenhr.model.dto.SQAnswerDTO;
import com.hydrogenhr.persistence.entity.SQAnswer;
import com.hydrogenhr.persistence.entity.SecurityQuestion;
import com.hydrogenhr.persistence.entity.User;
import com.hydrogenhr.persistence.repository.SQAnswerRepository;
import com.hydrogenhr.persistence.repository.SecurityQuestionRepository;
import com.hydrogenhr.persistence.repository.UserRepository;
import com.hydrogenhr.resource.UserController;
import com.hydrogenhr.service.SQAnswerService;
import com.hydrogenhr.service.SecurityQuestionService;
import com.hydrogenhr.service.UserService;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SQAnswerServiceImpl implements SQAnswerService{

    private final UserRepository userRepository;
    private final SQAnswerRepository sqAnswerRepository;
    private final SecurityQuestionService securityQuestionService;
    private final UserController userController;



    @Override
    public SQAnswer createAnswer(SQAnswerDTO sqAnswerDTO) {
    
        Optional<SecurityQuestion> securityQuestion = securityQuestionService.getQuestionByQuestion(sqAnswerDTO.getQuestion());

        Optional<User> user = userRepository.findById(sqAnswerDTO.getUser_id());
        SQAnswer sqAnswer = new SQAnswer();
        sqAnswer.setAnswer(sqAnswerDTO.getAnswer());
        sqAnswer.setCreatedBy(user.get().getUsername());
        sqAnswer.setSecurityQuestion(securityQuestion.get());
        sqAnswer.setUser(user.get());
        return sqAnswerRepository.save(sqAnswer);
    }



    @Override
    public Boolean verifyQuestion(SQAnswerDTO sqAnswerDTO) {
        
        Optional<User> user = userController.getUserById(sqAnswerDTO.getUser_id());
        if (user == null){
            throw new IllegalArgumentException("User does not exist");
        }

        Optional<SecurityQuestion> securityQuestion = securityQuestionService.getQuestionByQuestion(sqAnswerDTO.getQuestion());

        SQAnswer sqAnswer = sqAnswerRepository.findByAnswer(sqAnswerDTO.getAnswer());

        

        if (user.get().getId() == sqAnswer.getUser().getId()){
            if (securityQuestion.get().getQuestion().equals(sqAnswerDTO.getQuestion())){
                if (sqAnswerDTO.getAnswer().equals(sqAnswer.getAnswer())){
                    return true;
                }
            }
        }
        return false;
        
    }

}
