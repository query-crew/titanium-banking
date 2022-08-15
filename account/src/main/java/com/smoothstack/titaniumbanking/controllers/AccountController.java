package com.smoothstack.titaniumbanking.controllers;

import com.smoothstack.titaniumbanking.dto.AccountDto;
import com.smoothstack.titaniumbanking.exceptions.AccountExistsException;
import com.smoothstack.titaniumbanking.exceptions.AccountHasNoOwnerException;
import com.smoothstack.titaniumbanking.exceptions.AccountNotFoundException;
import com.smoothstack.titaniumbanking.exceptions.ValidationHandler;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = {"https://localhost:3000", "https://localhost:4200"}, allowCredentials = "true")
public class AccountController {
    
    @Autowired
    AccountService accountService;

    //create
    @RequestMapping(value="/accounts", method=RequestMethod.POST)
    public ResponseEntity<String> addNewAccount(@Valid @RequestBody AccountDto account) {
        try{
            accountService.addAccount(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (AccountExistsException e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    //read
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value="/accounts", method=RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllAccounts(@RequestParam int pageNo, @RequestParam int pageSize){
        try {
            return new ResponseEntity<>(accountService.getAllAccounts(pageNo, pageSize), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/accounts/{accountId}", method=RequestMethod.GET)
    public ResponseEntity<Map<String, Account>> getAccount(@PathVariable int accountId){
        HashMap map = new HashMap();
        try {
            map.put("success", accountService.getAccountById(accountId));
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        catch (AccountNotFoundException e) {
            map.put(e.getMessage(), null);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
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
