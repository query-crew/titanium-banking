package com.smoothstack.titaniumbanking.services;


import com.smoothstack.titaniumbanking.dto.AccountDto;
import com.smoothstack.titaniumbanking.exceptions.AccountExistsException;
import com.smoothstack.titaniumbanking.exceptions.AccountHasNoOwnerException;
import com.smoothstack.titaniumbanking.exceptions.AccountNotFoundException;
import com.smoothstack.titaniumbanking.models.Account;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.smoothstack.titaniumbanking.repositories.AccountRepository;

import lombok.RequiredArgsConstructor;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        if (newAccount.getLastStatementDate() != null) {
            account.setLastStatementDate(LocalDate.parse(newAccount.getLastStatementDate()));
        }
        if (newAccount.getPaymentDate() != null) {
            account.setPaymentDate(LocalDate.parse(newAccount.getPaymentDate()));
        }
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

    public List<Account> getAllAccounts(int pageNo, int pageSize) {
        Pageable pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Account> pagedAccounts = accountRepo.findAll(pageRequest);
        return pagedAccounts.toList();
    }
 
    //update
    public Account updateAccountById(AccountDto account, int accountId) throws AccountNotFoundException {
        Account accountToUpdate = getAccountById(accountId);
        accountToUpdate.setAccountName(account.getAccountName());
        accountToUpdate.setBalance(account.getBalance());
        accountToUpdate.setInterest(account.getInterest());
        if (accountToUpdate.getLastStatementDate() != null) {
            accountToUpdate.setLastStatementDate(LocalDate.parse(account.getLastStatementDate()));
        }
        if (accountToUpdate.getPaymentDate() != null) {
            accountToUpdate.setPaymentDate(LocalDate.parse(account.getPaymentDate()));
        }
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

    /**
     * This method takes an accountId and a memberId as parameters. It finds the account based on the accountId and
     * adds the memberId to the account. This allows us to form a one to many relationship between the bank member
     * and the account.
     * @author chloe johnson
     * @param memberId
     * @return an Account
     */
    public Account addAccountWithMember(AccountDto accountDto, int memberId) throws AccountExistsException {
        Account account = addAccount(accountDto);
        account.setMemberId(memberId);
        accountRepo.save(account);
        return account;
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
