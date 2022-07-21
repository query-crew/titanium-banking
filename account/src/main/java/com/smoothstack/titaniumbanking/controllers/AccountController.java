package com.smoothstack.titaniumbanking.controllers;

import com.smoothstack.titaniumbanking.dto.AccountDto;
import com.smoothstack.titaniumbanking.models.Account;
import com.smoothstack.titaniumbanking.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class AccountController {
    
    @Autowired
    AccountService accountService;

    //create
    @RequestMapping(value="/accounts", method=RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> addNewAccount(@RequestBody AccountDto account) {
       return accountService.addAccount(account);
    }

    //read
    @RequestMapping(value="/accounts", method=RequestMethod.GET)
    public ResponseEntity<Map <String, Object>> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    //update
    @RequestMapping(value="/accounts/{accountId}", method=RequestMethod.PUT)
    public ResponseEntity<Map <String, Object>> updateAccount(@RequestBody AccountDto account, @PathVariable int accountId){
        return accountService.updateAccountById(account, accountId);
    }

    //delete
    @RequestMapping(value="/accounts/{accountId}", method=RequestMethod.DELETE)
    public void deleteAccount(@RequestBody Account account, @PathVariable int accountId){
        accountService.deleteAccountById(accountId);
    }

    @RequestMapping(value="/accounts", method=RequestMethod.DELETE)
    public void deleteAllAccounts(){
        accountService.deleteAllAccounts();
    }
    
    
}
