package com.smoothstack.titaniumbanking.services;


import com.smoothstack.titaniumbanking.models.Account;
import com.smoothstack.titaniumbanking.repositories.AccountRepository;

import lombok.RequiredArgsConstructor;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepository accountRepo;

    //create
    public Account addAccount(Account account){
        accountRepo.save(account);
        return account;
    }


    //read
    public Account getAccountById(int accountId){
        return accountRepo.findByAccountId(accountId);
    }

    public List<Account> getAllAccounts(){
        return accountRepo.findAll();
    }
 
    //update
    public Account updateAccountById(Account account, int accountId){
        Account updatedAccount = accountRepo.findByAccountId(accountId);
        updatedAccount.setAccountName(account.getAccountName());
        updatedAccount.setBalance(account.getBalance());
        updatedAccount.setInterest(account.getInterest());
        updatedAccount.setLastStatementDate(account.getLastStatementDate());
        updatedAccount.setPaymentDate(account.getPaymentDate());
        accountRepo.save(updatedAccount);
        return updatedAccount;
    }

    //delete
    public void deleteAccountById(int accountId){
        Account account = accountRepo.findByAccountId(accountId);
        accountRepo.delete(account);
    }

    public void deleteAllAccounts() {
		accountRepo.deleteAll();
	}

    //for testing
    public boolean accountExists() {
        return accountRepo.count() != 0;
    }
}
