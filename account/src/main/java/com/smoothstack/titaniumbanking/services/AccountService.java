package com.smoothstack.titaniumbanking.services;


import com.smoothstack.titaniumbanking.dto.AccountDto;
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
    public ResponseEntity<Map<String, Object>> addAccount(AccountDto newAccount) {
        Account account = new Account();
        account.setAccountName(newAccount.getAccountName());
        account.setAccountNumber(newAccount.getAccountNumber());
        account.setBalance(newAccount.getBalance());
        account.setInterest(newAccount.getInterest());
        account.setLastStatementDate(newAccount.getLastStatementDate());
        account.setPaymentDate(newAccount.getPaymentDate());
        try {
            accountRepo.save(account);
            Map<String, Object> response = new HashMap<>();
            response.put( "account", HttpStatus.CREATED);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        }
        catch(Exception ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", ex);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //read
    public ResponseEntity<Map<String, Object>> getAccountById(int accountId) {
        try {
            Account account = accountRepo.findByAccountId(accountId);
            if(account == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            else {
                Map<String, Object> response = new HashMap<>();
                response.put("account", account);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch(Exception ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", ex);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> getAllAccounts(){
        try {
            List<Account> accounts = accountRepo.findAll();
            Map<String, Object> response = new HashMap<>();
            response.put("accounts", accounts);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", ex);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Account getAccount(int accountId) {
        return accountRepo.findByAccountId(accountId);
    }
 
    //update
    public ResponseEntity<Map<String, Object>> updateAccountById(AccountDto account, int accountId) {
        try {

            Account accountToUpdate = getAccount(accountId);  
            accountToUpdate.setAccountName(account.getAccountName());
            accountToUpdate.setBalance(account.getBalance());
            accountToUpdate.setInterest(account.getInterest());
            accountToUpdate.setLastStatementDate(account.getLastStatementDate());
            accountToUpdate.setPaymentDate(account.getPaymentDate());
            accountRepo.save(accountToUpdate);

            HashMap<String, Object> response = new HashMap<>();
            response.put("account", convertToDto(getAccount(accountId)));

            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", e);
            System.out.println(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

    //delete
    public ResponseEntity<Map<String, Object>> deleteAccountById(int accountId){
        Account account = accountRepo.findByAccountId(accountId);
        accountRepo.delete(account);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    public void deleteAllAccounts() {
		accountRepo.deleteAll();
	}


    //convert to DTO
    public AccountDto convertToDto(Account account) {
        AccountDto accountDto = new AccountDto();
        // accountDto.setAccountId(account.getAccountId());
        accountDto.setAccountName(account.getAccountName());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setBalance(account.getBalance());
        accountDto.setInterest(account.getInterest());
        accountDto.setLastStatementDate(account.getLastStatementDate());
        accountDto.setPaymentDate(account.getPaymentDate());
        return accountDto;
    }

    //for testing
    public boolean accountExists() {
        return accountRepo.count() != 0;
    }
}
