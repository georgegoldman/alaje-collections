package com.hydrogenhr.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydrogenhr.persistence.entity.EmailValidation;

public interface EmailValidationRepository extends JpaRepository<EmailValidation, Long> {

}
