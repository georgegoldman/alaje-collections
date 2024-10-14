package com.hydrogenhr.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import com.hydrogenhr.persistence.entity.SecurityQuestion;

public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion, UUID> {
    Optional<SecurityQuestion> findByQuestion(String question);

}
