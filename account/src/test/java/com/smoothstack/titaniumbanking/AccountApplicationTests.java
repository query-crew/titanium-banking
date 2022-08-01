 package com.smoothstack.titaniumbanking;

 import java.time.LocalDate;
 import java.util.ArrayList;
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
         AccountDto accountDto = new AccountDto();
         accountDto.setAccountName("ONETestAccount");
         accountDto.setAccountType("checking");
         accountDto.setAccountNumber("12345");
         accountDto.setBalance(9800);
         accountDto.setInterest(5);
         accountDto.setLastStatementDate(LocalDate.now());
         accountDto.setPaymentDate(LocalDate.now());
         service.addAccount(accountDto);

         AccountDto account2Dto = new AccountDto();
         account2Dto.setAccountName("TWOTestAccount");
         account2Dto.setAccountType("loan");
         account2Dto.setAccountNumber("123456");
         account2Dto.setBalance(9900);
         account2Dto.setInterest(6);
         account2Dto.setLastStatementDate(LocalDate.now());
         account2Dto.setPaymentDate(LocalDate.now());
         service.addAccount(account2Dto);

         AccountDto account3Dto = new AccountDto();
         account3Dto.setAccountName("THREETestAccount");
         account3Dto.setAccountType("investing");
         account3Dto.setAccountNumber("1234567");
         account3Dto.setBalance(1000);
         account3Dto.setInterest(9);
         account3Dto.setLastStatementDate(LocalDate.now());
         account3Dto.setPaymentDate(LocalDate.now());
         service.addAccount(account3Dto);

         Account account = new Account("ONETestAccount", "checking", "12345", 9800, 5, LocalDate.now(), LocalDate.now());
         Account account2 = new Account("TWOTestAccount", "loan", "123456", 9900, 6, LocalDate.now(), LocalDate.now());
         Account account3 = new Account("THREETestAccount", "investing", "1234567", 1000, 9, LocalDate.now(), LocalDate.now());

         List<Account> expectedAccounts = new ArrayList<>();
         expectedAccounts.add(account);
         expectedAccounts.add(account2);
         expectedAccounts.add(account3);

         List<Account> actualAccounts = service.getAllAccounts();
         Assertions.assertEquals(expectedAccounts, actualAccounts);
     }

//     //add account test
//     @Test
//     void addAccountTest(){
//         AccountDto testAccount =  new AccountDto();
//         testAccount.setAccountName("ONETestAccount");
//         testAccount.setAccountNumber("12345");
//         testAccount.setBalance(9800);
//         testAccount.setInterest(5);
//         testAccount.setLastStatementDate(LocalDate.now());
//         testAccount.setPaymentDate(LocalDate.now());
//         ResponseEntity<Map <String, Object>> addedAccount =  service.addAccount(testAccount);
//         Assertions.assertEquals(HttpStatus.CREATED, addedAccount.getStatusCode());
//     }
//
//     //update account test
//     @Test
//     void updateAccountTest(){
//         Account testAccount = new Account();
//         testAccount.setAccountName("ONETestAccount");
//         testAccount.setAccountNumber("12345");
//         testAccount.setBalance(9800);
//         testAccount.setInterest(5);
//         testAccount.setLastStatementDate(LocalDate.now());
//         testAccount.setPaymentDate(LocalDate.now());
//         AccountDto testAccountDto = service.convertToDto(testAccount);
//         service.addAccount(testAccountDto);
//         System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//         System.out.println(testAccount.toString());
//         ResponseEntity<Map <String, Object>> updatedAccount = service.updateAccountById(testAccountDto, testAccount.getAccountId());
//         Assertions.assertEquals(HttpStatus.ACCEPTED, updatedAccount.getStatusCode());
//     }
//
//     //delete account test
//     @Test
//     void deleteAccountTest(){
//         AccountDto testAccount = new AccountDto();
//         testAccount.setAccountName("ONETestAccount");
//         testAccount.setAccountNumber("12345");
//         testAccount.setBalance(9800);
//         testAccount.setInterest(5);
//         testAccount.setLastStatementDate(LocalDate.now());
//         testAccount.setPaymentDate(LocalDate.now());
//         service.addAccount(testAccount);
//         ResponseEntity<Map <String, Object>> deletedAccount = service.deleteAccountById(testAccount.getAccountId());
//         Assertions.assertEquals(HttpStatus.NO_CONTENT, deletedAccount.getStatusCode());
//     }


 }
