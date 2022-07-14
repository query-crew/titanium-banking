package com.titanium.transactions.controller;

import com.titanium.transactions.model.Transaction;
import com.titanium.transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //Get all transactions for a specified accountId
    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public List<Transaction> retrieveAllTransactions() {
        return transactionService.getAllTransactions();
    }

    //Get a transaction specified by an accountId
    @RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.GET)
    public Transaction retrieveOneTransaction(@PathVariable int transactionId) {
        return transactionService.getTransaction(transactionId);
    }

    //Add a transaction to a specified account
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public void postTransactionToAccount(@RequestBody Transaction transactionData) {
        transactionService.createTransaction(transactionData);
    }
}

