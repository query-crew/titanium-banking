package com.titanium.transactions.controller;

import com.titanium.transactions.exception.InvalidSortPropException;
import com.titanium.transactions.exception.TransactionNotFoundException;
import com.titanium.transactions.model.Transaction;
import com.titanium.transactions.service.TransactionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"https://localhost:3000", "https://localhost:4200"}, allowCredentials = "true")
@Api( tags = "Transactions" )
@CrossOrigin(origins = {"https://localhost:3000", "https://localhost:4200"})
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //Create
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> postTransactionToAccount(@RequestBody Transaction transactionData) {
        return new ResponseEntity<>(transactionService.createTransaction(transactionData), HttpStatus.CREATED);
    }
    //Read
    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveAllTransactions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Map<String, Object> res = transactionService.getAllTransactions(page, size);
        return new ResponseEntity<>(transactionService.getAllTransactions(page, size), HttpStatus.OK);
    }
    @RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveOneTransaction(@PathVariable int transactionId) {
        try {
            return new ResponseEntity<>(transactionService.getTransaction(transactionId), HttpStatus.OK);
        } catch (TransactionNotFoundException e) {
            Map<String, Object> res = new HashMap<>();
            res.put("error", e.getMessage());
            return new ResponseEntity<>(res, e.getStatus());
        }
    }
    @RequestMapping(value = "/transaction/fromAccount/{accountFromId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveTransactionByAccountFromId(@PathVariable int accountFromId, @RequestParam(required = false) String description, @RequestParam(required = false) String sortProp, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        try {
            return new ResponseEntity<>(transactionService.retrieveTransactionByAccountFromId(accountFromId, description, sortProp, page, size), HttpStatus.OK);
        } catch (InvalidSortPropException e) {
            Map<String, Object> res = new HashMap<>();
            res.put("error", e.getMessage());
            return new ResponseEntity<>(res, e.getStatus());
        }
    }
    @RequestMapping(value = "/transaction/toAccount/{accountToId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveTransactionByAccountToId(@PathVariable int accountToId, @RequestParam(required = false) String description, @RequestParam(required = false) String sortProp, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        try{
            return new ResponseEntity<>(transactionService.retrieveTransactionByAccountToId(accountToId, description, sortProp, page, size), HttpStatus.OK);
        } catch (InvalidSortPropException e){
            Map<String, Object> res = new HashMap<>();
            res.put("error", e.getMessage());
            return new ResponseEntity<>(res, e.getStatus());
        }
    }
    @RequestMapping(value = "/transaction/account/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveTransactionByAccountId(@PathVariable int accountId, @RequestParam(required = false) String description, @RequestParam(required = false) String sortProp, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        try {
            return new ResponseEntity<>(transactionService.retrieveTransactionByAccountId(accountId, description, sortProp, page, size), HttpStatus.OK);
        } catch (InvalidSortPropException e) {
            Map<String, Object> res = new HashMap<>();
            res.put("error", e.getMessage());
            return new ResponseEntity<>(res, e.getStatus());
        }
    }
    @RequestMapping(value = "/transaction/transactionType/{transactionType}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveTransactionByType(@PathVariable int transactionType, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(transactionService.retrieveTransactionByType(transactionType, page, size), HttpStatus.OK);
    }
    @RequestMapping(value = "/transaction/date/{transactionDate}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> retrieveTransactionsByDate(@PathVariable Timestamp transactionDate, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(transactionService.retrieveTransactionByDate(transactionDate, page, size), HttpStatus.OK);
    }
}