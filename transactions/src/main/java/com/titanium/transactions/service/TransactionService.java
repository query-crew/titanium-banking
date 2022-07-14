package com.titanium.transactions.service;

import com.titanium.transactions.model.Transaction;
import com.titanium.transactions.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransaction(int transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public void createTransaction(Transaction transactionData) {
        transactionRepository.save(transactionData);
    }
}
