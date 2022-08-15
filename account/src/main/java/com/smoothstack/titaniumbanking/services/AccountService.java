package com.smoothstack.titaniumbanking.services;


import com.smoothstack.titaniumbanking.dto.AccountDto;
import com.smoothstack.titaniumbanking.exceptions.AccountExistsException;
import com.smoothstack.titaniumbanking.exceptions.AccountNotFoundException;
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
    public Account addAccount(AccountDto newAccount) {

        if(accountRepo.existsByAccountNumber(newAccount.getAccountNumber())){
            throw new AccountExistsException();
        }

        Account account = new Account();
        account.setAccountName(newAccount.getAccountName());
        account.setAccountType(newAccount.getAccountType());
        account.setAccountNumber(generateAccountNumber(newAccount.getAccountName(), newAccount.getAccountType()));
        account.setBalance(0);

        if(newAccount.getAccountType() == "checking"){
            account.setInterest(0);
        }else if(newAccount.getAccountType() == "savings"){
            account.setInterest(25);
        }else if(newAccount.getAccountType() == "investing"){
            account.setInterest(0);
        }else account.setInterest(15);
        
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

    //generate account number
    public String generateAccountNumber(String name, String type){
        int num = generatePrimeNumber();
        return String.format("%017d", Math.abs(Objects.hash(num, name, type))) ;
    }

    //generate prime number used as a salt value
    public Integer generatePrimeNumber(){
        int num = 0;
        Random rand = new Random(); 
        num = rand.nextInt(1000) + 1;

        while (!isPrime(num)) {          
            num = rand.nextInt(1000) + 1;
        }
        return num;
    }

    //checks if a number is prime
    private static boolean isPrime(int num){
        if (num <= 3 || num % 2 == 0) 
            return num == 2 || num == 3; //this returns false if number is <=1 & true if number = 2 or 3
        int divisor = 3;
        while ((divisor <= Math.sqrt(num)) && (num % divisor != 0)) 
            divisor += 2;                   //iterates through all possible divisors
        return num % divisor != 0;     //returns true/false
    }

    //for testing
    public boolean accountExists() {
        return accountRepo.count() != 0;
    }
}
