package com.hydrogenhr.service;

import java.util.*;

import com.hydrogenhr.persistence.entity.Invoice;
import com.hydrogenhr.persistence.entity.Transaction;

public interface TransactionService {

    List<Transaction> getAllTransactions();

    public Optional<Transaction> getInvoiceById(Long id);

    Transaction createTransaction(Transaction transaction);

}
