package com.hydrogenhr.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydrogenhr.persistence.entity.Transaction;
import com.hydrogenhr.service.TransactionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
        Optional <Transaction> transaction = transactionService.getInvoiceById(id);

        return transaction.map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    public Transaction createTransaction(@RequestBody Transaction transaction){
        return transactionService.createTransaction(transaction);
    }
}
