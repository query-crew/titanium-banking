package com.smoothstack.titaniumbanking.services;


import com.smoothstack.titaniumbanking.dto.AccountDto;
import com.smoothstack.titaniumbanking.exceptions.AccountNotFoundException;
import com.smoothstack.titaniumbanking.models.Account;
import com.smoothstack.titaniumbanking.repositories.AccountRepository;

import lombok.RequiredArgsConstructor;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private AccountRepository accountRepo;

    //create
    public Account addAccount(AccountDto newAccount) {
        Account account = new Account();
        account.setAccountName(newAccount.getAccountName());
        account.setAccountType(newAccount.getAccountType());
        account.setAccountNumber(newAccount.getAccountNumber());
        account.setBalance(newAccount.getBalance());
        account.setInterest(newAccount.getInterest());
        account.setLastStatementDate(newAccount.getLastStatementDate());
        account.setPaymentDate(newAccount.getPaymentDate());
        accountRepo.save(account);
        return account;
    }


    //read
    public Account getAccountById(int accountId) throws AccountNotFoundException{
        Account account = accountRepo.findByAccountId(accountId);
        if(account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepo.findAll();
        return accounts;
    }
 
    //update
    public Account updateAccountById(AccountDto account, int accountId) throws AccountNotFoundException {
        Account accountToUpdate = getAccountById(accountId);
        accountToUpdate.setAccountName(account.getAccountName());
        accountToUpdate.setBalance(account.getBalance());
        accountToUpdate.setInterest(account.getInterest());
        accountToUpdate.setLastStatementDate(account.getLastStatementDate());
        accountToUpdate.setPaymentDate(account.getPaymentDate());
        accountRepo.save(accountToUpdate);
        return accountToUpdate;
    }

    

    //delete
    public Account deleteAccountById(int accountId) throws AccountNotFoundException {
        Account account = getAccountById(accountId);
        accountRepo.delete(account);
        return account;
    }

    public void deleteAllAccounts() {
		accountRepo.deleteAll();
	}

    //for testing
    public boolean accountExists() {
        return accountRepo.count() != 0;
    }
}
