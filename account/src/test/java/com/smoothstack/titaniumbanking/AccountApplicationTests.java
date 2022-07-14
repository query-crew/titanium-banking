package com.smoothstack.titaniumbanking;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smoothstack.titaniumbanking.models.Account;
import com.smoothstack.titaniumbanking.services.AccountService;

@SpringBootTest
class AccountApplicationTests {

    @Autowired
    private AccountService service;

    @BeforeEach
    void setUp(){
        if(service.accountExists()){
            service.deleteAllAccounts();
        }
    }

    @Test
    void contextLoads(){}

    
    @Test
    void viewAllAccounts(){
        Account account = new Account("ONETestAccount", "12345", 9800, 5, LocalDate.now(), LocalDate.now());
        service.addAccount(account);
        Account accountTwo = new Account("TWOTestAccount", "678910", 1200, 3, LocalDate.now(), LocalDate.now());
        service.addAccount(accountTwo);
        List<Account> accounts = service.getAllAccounts();

        Assertions.assertEquals(2, accounts.size());
    }

    //add account test
    @Test
    void addAccountTest(){
        Account testAccount =  new Account("TestAccountName", "12345", 9800, 5, LocalDate.now(), LocalDate.now());
        Account addedAccount =  service.addAccount(testAccount);
        Account expectedAccount = new Account("TestAccountName", "12345", 9800, 5, addedAccount.getLastStatementDate(), addedAccount.getPaymentDate());
        Assertions.assertEquals(expectedAccount, addedAccount);
    }
    
    //update account test
    @Test
    void updateAccountTest(){
        Account testAccount = new Account("TestAccountName", "12345", 9800, 5, LocalDate.now(), LocalDate.now());
        service.addAccount(testAccount);
        Account newAccount = new Account("ANOTHERTESTACCOUNT", "12345", 9800, 5, LocalDate.now(), LocalDate.now());
        newAccount.setAccountId(testAccount.getAccountId());
        service.updateAccountById(newAccount, testAccount.getAccountId());
        Assertions.assertEquals(newAccount, service.getAccountById(testAccount.getAccountId()));
    }

    //delete account test
    @Test
    void deleteAccountTest(){
        Account testAccount = new Account("TestAccountName", "12345", 9800, 5, LocalDate.now(), LocalDate.now());
        Account addedAccount =  service.addAccount(testAccount);
        service.deleteAccountById(addedAccount.getAccountId());
        Assertions.assertEquals(0, service.getAllAccounts().size());
    }


    
}
