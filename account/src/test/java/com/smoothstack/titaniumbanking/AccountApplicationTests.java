package com.smoothstack.titaniumbanking;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.smoothstack.titaniumbanking.models.Account;
import com.smoothstack.titaniumbanking.services.AccountService;



import com.smoothstack.titaniumbanking.dto.AccountDto;

@SpringBootTest
class AccountApplicationTests {

    @Autowired
    private AccountService service;

    // @BeforeEach
    // void setUp(){
    //     if(service.accountExists()){
    //         service.deleteAllAccounts();
    //     }
    // }

    @Test
    void contextLoads(){}

    
    @Test
    void viewAllAccounts(){
        AccountDto account = new AccountDto();
        
        account.setAccountName("ONETestAccount");
        account.setAccountNumber("12345");
        account.setBalance(9800);
        account.setInterest(5);
        account.setLastStatementDate(LocalDate.now());
        account.setPaymentDate(LocalDate.now());
        service.addAccount(account);
        
       ResponseEntity<Map<String, Object>> accounts = service.getAllAccounts();

        Assertions.assertEquals(HttpStatus.OK, accounts.getStatusCode());
    }

    //add account test
    @Test
    void addAccountTest(){
        AccountDto testAccount =  new AccountDto();
        testAccount.setAccountName("ONETestAccount");
        testAccount.setAccountNumber("12345");
        testAccount.setBalance(9800);
        testAccount.setInterest(5);
        testAccount.setLastStatementDate(LocalDate.now());
        testAccount.setPaymentDate(LocalDate.now());
        ResponseEntity<Map <String, Object>> addedAccount =  service.addAccount(testAccount);
        Assertions.assertEquals(HttpStatus.CREATED, addedAccount.getStatusCode());
    }
    
    //update account test
    @Test
    void updateAccountTest(){
        Account testAccount = new Account();
        testAccount.setAccountName("ONETestAccount");
        testAccount.setAccountNumber("12345");
        testAccount.setBalance(9800);
        testAccount.setInterest(5);
        testAccount.setLastStatementDate(LocalDate.now());
        testAccount.setPaymentDate(LocalDate.now());
        AccountDto testAccountDto = service.convertToDto(testAccount);
        service.addAccount(testAccountDto);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(testAccount.toString());
        ResponseEntity<Map <String, Object>> updatedAccount = service.updateAccountById(testAccountDto, testAccount.getAccountId());
        Assertions.assertEquals(HttpStatus.ACCEPTED, updatedAccount.getStatusCode());
    }

    //delete account test
    @Test
    void deleteAccountTest(){
        AccountDto testAccount = new AccountDto();
        testAccount.setAccountName("ONETestAccount");
        testAccount.setAccountNumber("12345");
        testAccount.setBalance(9800);
        testAccount.setInterest(5);
        testAccount.setLastStatementDate(LocalDate.now());
        testAccount.setPaymentDate(LocalDate.now());
        service.addAccount(testAccount);
        ResponseEntity<Map <String, Object>> deletedAccount = service.deleteAccountById(testAccount.getAccountId());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, deletedAccount.getStatusCode());
    }


    
}
