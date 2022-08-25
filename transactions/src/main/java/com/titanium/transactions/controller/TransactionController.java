package com.titanium.transactions.controller;

import com.titanium.transactions.model.Transaction;
import com.titanium.transactions.service.TransactionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"https://localhost:3000", "https://localhost:4200"}, allowCredentials = "true")
@Api( tags = "Transactions" )
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveOneTransaction(@PathVariable int transactionId) {
        return transactionService.getTransaction(transactionId);
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> postTransactionToAccount(@RequestBody Transaction transactionData) {
        return transactionService.createTransaction(transactionData);
    }

    @RequestMapping(value = "/transaction/fromAccount/{accountFromId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveTransactionByAccountFromId(@PathVariable int accountFromId) {
        return transactionService.retrieveTransactionByAccountFromId(accountFromId);
    }

    @RequestMapping(value = "/transaction/toAccount/{accountFromId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveTransactionByAccountToId(@PathVariable int accountToId) {
        return transactionService.retrieveTransactionByAccountFromId(accountToId);
    }

    @RequestMapping(value = "/transaction/transactionType/{transactionType}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveTransactionByType(@PathVariable int transactionType) {
        return transactionService.retrieveTransactionByType(transactionType);
    }

    @RequestMapping(value = "/transaction/date/{transactionDate}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveTransactionsByDate(@PathVariable Timestamp transactionDate) {
        return transactionService.retrieveTransactionByDate(transactionDate);
    }
}