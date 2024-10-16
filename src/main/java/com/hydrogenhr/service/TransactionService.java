package com.hydrogenhr.service;

import java.util.*;

import com.hydrogenhr.model.dto.PaymentDTO;
import com.hydrogenhr.model.dto.TransactionDTo;
import com.hydrogenhr.persistence.entity.Transaction;

public interface TransactionService {

    List<Transaction> getAllTransactions();

    public Optional<Transaction> getTransactionById(Long id);

    Transaction createTransaction(TransactionDTo transaction);

}
