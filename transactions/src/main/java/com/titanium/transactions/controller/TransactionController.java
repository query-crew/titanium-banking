package com.titanium.transactions.controller;

import com.titanium.transactions.model.Transaction;
import com.titanium.transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //Get all transactions for a specified accountId
    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveAllTransactions() {
        return transactionService.getAllTransactions();
    }

    //Get a transaction specified by an accountId
    @RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveOneTransaction(@PathVariable int transactionId) {
        return transactionService.getTransaction(transactionId);
    }

    //Add a transaction to a specified account
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> postTransactionToAccount(@RequestBody Transaction transactionData) {
        return transactionService.createTransaction(transactionData);
    }
}

