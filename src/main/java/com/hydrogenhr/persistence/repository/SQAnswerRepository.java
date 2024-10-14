package com.hydrogenhr.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydrogenhr.persistence.entity.SQAnswer;

public interface SQAnswerRepository extends JpaRepository<SQAnswer, Long>{

    // Method to find answer by answer value
    SQAnswer findByAnswer(String answer);
}
