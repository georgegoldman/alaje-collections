package com.hydrogenhr.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.hydrogenhr.model.dto.TransactionDTo;
import com.hydrogenhr.model.enums.ChargeType;
import com.hydrogenhr.model.enums.TransactionStatus;
import com.hydrogenhr.persistence.entity.FixedCharges;
import com.hydrogenhr.persistence.entity.Transaction;
import com.hydrogenhr.persistence.entity.User;
import com.hydrogenhr.persistence.repository.TransactionRepository;
import com.hydrogenhr.service.FixedChargesService;
import com.hydrogenhr.service.TransactionService;
import com.hydrogenhr.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final FixedChargesService fixedChargesService;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    

    @Override
    public Transaction createTransaction(TransactionDTo transactionDTo) {
        double serviceCharge = 0;
        double totalAmount = 0;
        ChargeType chargeType = ChargeType.BOTH;

        if (transactionDTo.getFlatRate() !=  0 && transactionDTo.getInPercentage() != 0){
            serviceCharge =  transactionDTo.getFlatRate() + (transactionDTo.getPayableAmount()*(transactionDTo.getInPercentage()/100));

        }
        else if (transactionDTo.getFlatRate() != 0 && transactionDTo.getInPercentage() == 0){
            serviceCharge = transactionDTo.getPayableAmount() + transactionDTo.getFlatRate();
            chargeType = ChargeType.FLAT;
        }else if (transactionDTo.getInPercentage() != 0 && transactionDTo.getFlatRate() == 0){
            serviceCharge = transactionDTo.getPayableAmount()*(transactionDTo.getInPercentage()/100);
            chargeType = ChargeType.PERCENTAGE;
        }

        totalAmount = serviceCharge + transactionDTo.getPayableAmount();

        Optional<User> getUser = userService.getUserById(transactionDTo.getUserId());

        Transaction newTransaction = Transaction.builder()
        .alajeFixedUserCharge(BigDecimal.valueOf(serviceCharge))
        .payableAmount(BigDecimal.valueOf(transactionDTo.getPayableAmount()))
        .transactionStatus(TransactionStatus.PENDING)
        .totalAmount(BigDecimal.valueOf(totalAmount))
        .user(getUser.get())
        .build();


        Transaction transaction = transactionRepository.save(newTransaction);
        
        FixedCharges fixedCharges = FixedCharges.builder()
        .chargeType(chargeType)
        .inPercentage(transactionDTo.getInPercentage())
        .flatRate(transactionDTo.getFlatRate())
        .transaction(transaction)
        .user(getUser.get())
        .build();
        fixedChargesService.creatAlajeFixedUserCharges(fixedCharges);


        return transaction;
        
    }   

}
