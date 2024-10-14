package com.hydrogenhr.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydrogenhr.persistence.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
