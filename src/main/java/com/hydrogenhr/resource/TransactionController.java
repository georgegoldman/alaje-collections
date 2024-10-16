package com.hydrogenhr.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydrogenhr.model.dto.TransactionDTo;
import com.hydrogenhr.persistence.entity.Transaction;
import com.hydrogenhr.service.TransactionService;

import lombok.RequiredArgsConstructor;

import java.util.*;

@RestController
@RequestMapping("/dev/transaction")
@RequiredArgsConstructor
public class TransactionController  {

    private final TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAlltransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){
        Optional <Transaction> transaction = transactionService.getTransactionById(id);

        return transaction.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTo transactionDTo){
        Transaction transaction = transactionService.createTransaction(transactionDTo);

        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
}
