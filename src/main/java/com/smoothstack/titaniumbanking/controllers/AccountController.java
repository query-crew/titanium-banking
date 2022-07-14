package com.smoothstack.titaniumbanking.controllers;

import com.smoothstack.titaniumbanking.models.Account;
import com.smoothstack.titaniumbanking.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void addNewAccount(@RequestBody Account account) {
        accountService.addAccount(account);;
    }

    //read
    @RequestMapping(value="/accounts", method=RequestMethod.GET)
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    //update
    @RequestMapping(value="/accounts/{accountId}", method=RequestMethod.PUT)
    public void updateAccount(@RequestBody Account account, @PathVariable int accountId){
        accountService.updateAccountById(account, accountId);
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
