package com.smoothstack.titaniumbanking.controllers;

import com.smoothstack.titaniumbanking.dto.AccountDto;
import com.smoothstack.titaniumbanking.exceptions.AccountNotFoundException;
import com.smoothstack.titaniumbanking.models.Account;
import com.smoothstack.titaniumbanking.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
    
    @Autowired
    AccountService accountService;

    //create
    @RequestMapping(value="/accounts", method=RequestMethod.POST)
    public ResponseEntity<String> addNewAccount(@RequestBody AccountDto account) {
        accountService.addAccount(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //read
    @RequestMapping(value="/accounts", method=RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllAccounts(){
        try {
            return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/accounts/{accountId}", method=RequestMethod.GET)
    public ResponseEntity<Account> getAccount(@PathVariable int accountId){
        try {
            return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
        }
        catch (AccountNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //update
    @RequestMapping(value="/accounts/{accountId}", method=RequestMethod.PUT)
    public ResponseEntity<String> updateAccount(@RequestBody AccountDto account, @PathVariable int accountId){
        try {
            accountService.updateAccountById(account, accountId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (AccountNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //delete
    @RequestMapping(value="/accounts/{accountId}", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteAccount(@PathVariable int accountId){
        try {
            accountService.deleteAccountById(accountId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (AccountNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/accounts", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteAllAccounts(){
        try {
            accountService.deleteAllAccounts();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (AccountNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
