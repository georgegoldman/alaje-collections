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
import com.hydrogenhr.persistence.entity.User;
import com.hydrogenhr.persistence.repository.UserRepository;
import com.hydrogenhr.service.TransactionService;

import com.hydrogenhr.model.enums.TransactionStatus;


import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/dev/transaction")
@RequiredArgsConstructor
public class TransactionController  {

    private final TransactionService transactionService;
    private final UserController userController;

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

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTo transactionDTo){
        Optional<User> user = userController.getUserById(transactionDTo.getUserId());

        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(null);
        }
        TransactionStatus transactionStatus;
        try{
            transactionStatus = TransactionStatus.valueOf(transactionDTo.getTransactionStatus().toUpperCase());
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(null);
        }
        Transaction newTransaction = Transaction.builder()
        .transactionStatus(transactionStatus)
        .alajeFixedUserCharge(BigDecimal.valueOf(transactionDTo.getAlajeFixedUserCharge()))
        .payableAmount(transactionDTo.getPayableAmount())
        .totalAmount(transactionDTo.getTotalAmount())
        .user(user.get())
        .build();
        
        Transaction transaction = transactionService.createTransaction(newTransaction);

        return ResponseEntity.status(HttpStatus.CREATED)
        .body(transaction);
    }
}
