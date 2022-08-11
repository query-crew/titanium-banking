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
@Api( tags = "Transactions" )
@CrossOrigin(origins = "https://localhost:3000")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> postTransactionToAccount(@RequestBody Transaction transactionData) {
        return transactionService.createTransaction(transactionData);
    }

    //Read

    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveAllTransactions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return transactionService.getAllTransactions(page, size);
    }
    @RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveOneTransaction(@PathVariable int transactionId) {
        return transactionService.getTransaction(transactionId);
    }
    @RequestMapping(value = "/transaction/fromAccount/{accountFromId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveTransactionByAccountFromId(@RequestParam(required = false) String description, @RequestParam(required = false) String sortProp, @PathVariable int accountFromId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return transactionService.retrieveTransactionByAccountFromId(accountFromId, description, sortProp, page, size);
    }
    @RequestMapping(value = "/transaction/toAccount/{accountToId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveTransactionByAccountToId(@RequestParam(required = false) String description, @RequestParam(required = false) String sortProp, @PathVariable int accountToId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return transactionService.retrieveTransactionByAccountToId(accountToId, description, sortProp, page, size);
    }
    @RequestMapping(value = "/transaction/transactionType/{transactionType}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveTransactionByType(@PathVariable int transactionType, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return transactionService.retrieveTransactionByType(transactionType, page, size);
    }
    @RequestMapping(value = "/transaction/date/{transactionDate}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveTransactionsByDate(@PathVariable Timestamp transactionDate, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return transactionService.retrieveTransactionByDate(transactionDate, page, size);
    }
}