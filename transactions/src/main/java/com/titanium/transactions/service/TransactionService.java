package com.titanium.transactions.service;

import com.titanium.transactions.model.Transaction;
import com.titanium.transactions.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<Map<String, Object>> getAllTransactions() {
        try {
            List<Transaction> transactions = transactionRepository.findAll();
            Map<String, Object> response = new HashMap<>();
            response.put("transactions", transactions);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", ex);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> getTransaction(int transactionId) {
        try {
            Transaction transaction = transactionRepository.findById(transactionId);
            if(transaction == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            else {
                Map<String, Object> response = new HashMap<>();
                response.put("transaction", transaction);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch(Exception ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", ex);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> createTransaction(Transaction transactionData) {
        try {
            transactionRepository.save(transactionData);
            Map<String, Object> response = new HashMap<>();
            response.put("Transaction", "Created");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        }
        catch(Exception ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", ex);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
