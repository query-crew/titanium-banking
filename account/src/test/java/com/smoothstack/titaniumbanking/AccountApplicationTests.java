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
         accountDto.setLastStatementDate(LocalDate.now().toString());
         accountDto.setPaymentDate(LocalDate.now().toString());
         Account addedAcct1 = service.addAccount(accountDto);

         AccountDto account2Dto = new AccountDto();
         account2Dto.setAccountName("TWOTestAccount");
         account2Dto.setAccountType("loan");
         account2Dto.setLastStatementDate(LocalDate.now().toString());
         account2Dto.setPaymentDate(LocalDate.now().toString());
         Account addedAcct2 = service.addAccount(account2Dto);

         AccountDto account3Dto = new AccountDto();
         account3Dto.setAccountName("THREETestAccount");
         account3Dto.setAccountType("investing");
         account3Dto.setLastStatementDate(LocalDate.now().toString());
         account3Dto.setPaymentDate(LocalDate.now().toString());
         Account addedAcct3 = service.addAccount(account3Dto);

         Account account = new Account("ONETestAccount", "checking", addedAcct1.getAccountNumber(), addedAcct1.getBalance(), addedAcct1.getInterest(), LocalDate.now(), LocalDate.now());
         Account account2 = new Account("TWOTestAccount", "loan", addedAcct2.getAccountNumber(), addedAcct2.getBalance(), addedAcct2.getInterest(), LocalDate.now(), LocalDate.now());
         Account account3 = new Account("THREETestAccount", "investing", addedAcct3.getAccountNumber(), addedAcct3.getBalance(), addedAcct3.getInterest(), LocalDate.now(), LocalDate.now());

         List<Account> expectedAccounts = new ArrayList<>();
         expectedAccounts.add(account);
         expectedAccounts.add(account2);
         expectedAccounts.add(account3);

         List<Account> actualAccounts = service.getAllAccounts(0, 10);
         Assertions.assertEquals(expectedAccounts, actualAccounts);
     }

    //add account test
    @Test
    void addAccountTest(){
        AccountDto testAccount =  new AccountDto();
        testAccount.setAccountName("ONETestAccount");
        testAccount.setLastStatementDate(LocalDate.now().toString());
        testAccount.setPaymentDate(LocalDate.now().toString());
        service.addAccount(testAccount);

        AccountDto account2Dto = new AccountDto();
         account2Dto.setAccountName("TWOTestAccount");
         account2Dto.setAccountType("loan");
         account2Dto.setLastStatementDate(LocalDate.now().toString());
         account2Dto.setPaymentDate(LocalDate.now().toString());
         service.addAccount(account2Dto);
        Assertions.assertEquals(2, service.getAllAccounts(0, 10).size());
    }

     //update account test
     @Test
     void updateAccountTest(){
         AccountDto testAccountDto = new AccountDto();
         testAccountDto.setAccountName("ONEtestAccountDto");
         testAccountDto.setAccountType("loan");
         testAccountDto.setLastStatementDate(LocalDate.now().toString());
         testAccountDto.setPaymentDate(LocalDate.now().toString());
         Account actualAccount = service.addAccount(testAccountDto);
         int id = actualAccount.getAccountId();

         AccountDto updatedAccountDto = new AccountDto();
         updatedAccountDto.setAccountName("UpdatedtestAccountDto");
         updatedAccountDto.setInterest(actualAccount.getInterest());
         updatedAccountDto.setLastStatementDate(LocalDate.now().toString());
         updatedAccountDto.setPaymentDate(LocalDate.now().toString());

         Account expectAccount = new Account();
         expectAccount.setAccountId(id);
         expectAccount.setAccountNumber(actualAccount.getAccountNumber());
         expectAccount.setBalance(actualAccount.getBalance());
         expectAccount.setInterest(actualAccount.getInterest());
         expectAccount.setAccountName("UpdatedtestAccountDto");
         expectAccount.setAccountType("loan");
         expectAccount.setLastStatementDate(LocalDate.now());
         expectAccount.setPaymentDate(LocalDate.now());

         Account actualUpdatedAccount = service.updateAccountById(updatedAccountDto, id);
         Assertions.assertEquals(expectAccount, actualUpdatedAccount);
     }

    @Test
    void deleteAccountTest(){
        AccountDto testAccount = new AccountDto();
        testAccount.setAccountName("ONETestAccount");
        testAccount.setAccountType("checking");
        testAccount.setLastStatementDate(LocalDate.now().toString());
        testAccount.setPaymentDate(LocalDate.now().toString());
        Account addedAccount = service.addAccount(testAccount);
        int id = addedAccount.getAccountId();

        Account expectedAccount = new Account();
         expectedAccount.setAccountName("ONETestAccount");
         expectedAccount.setAccountType("checking");
         expectedAccount.setAccountNumber(addedAccount.getAccountNumber());
         expectedAccount.setBalance(addedAccount.getBalance());
         expectedAccount.setInterest(addedAccount.getInterest());
         expectedAccount.setLastStatementDate(LocalDate.now());
         expectedAccount.setPaymentDate(LocalDate.now());
         Account actualAccount = service.deleteAccountById(id);

         Assertions.assertEquals(expectedAccount, actualAccount);
    }

    @Test
    void deleteAccountEmptyTest(){
        AccountDto testAccount = new AccountDto();
        testAccount.setAccountName("ONETestAccount");
        testAccount.setAccountType("checking");
        testAccount.setLastStatementDate(LocalDate.now().toString());
        testAccount.setPaymentDate(LocalDate.now().toString());
        Account actualAccount = service.addAccount(testAccount);
        int id = actualAccount.getAccountId();
        service.deleteAccountById(id);

        Assertions.assertEquals(0, service.getAllAccounts(0, 10).size());
    }

    @Test
    void accountNumGenerationTest(){
        AccountDto testAccount = new AccountDto();
        testAccount.setAccountName("ONETestAccount");
        testAccount.setAccountType("savings");
        testAccount.setAccountNumber(service.generateAccountNumber(testAccount.getAccountName(), testAccount.getAccountType()).toString());
        testAccount.setBalance(9800);
        testAccount.setInterest(5);
        testAccount.setLastStatementDate(LocalDate.now().toString());
        testAccount.setPaymentDate(LocalDate.now().toString());
        Account actualAccount = service.addAccount(testAccount);
        String accountNum = actualAccount.getAccountNumber();
        System.out.println("**************");
        System.out.println(accountNum);

        AccountDto test2Account = new AccountDto();
        test2Account.setAccountName("ONETestAccount");
        test2Account.setAccountType("savings");
        test2Account.setAccountNumber(service.generateAccountNumber(testAccount.getAccountName(), testAccount.getAccountType()).toString());
        test2Account.setBalance(9800);
        test2Account.setInterest(5);
        test2Account.setLastStatementDate(LocalDate.now().toString());
        test2Account.setPaymentDate(LocalDate.now().toString());
        Account actual2Account = service.addAccount(test2Account);
        String account2Num = actual2Account.getAccountNumber();
        System.out.println("**************");
        System.out.println(account2Num);
    }

    @Test
     void addAccountWithMember() {
        AccountDto testAccount =  new AccountDto();
        testAccount.setAccountName("ONETestAccount");
        testAccount.setAccountType("checking");
        testAccount.setLastStatementDate(LocalDate.now().toString());
        testAccount.setPaymentDate(LocalDate.now().toString());
        Account actualAccount = service.addAccountWithMember(testAccount, 5);
        Account expectedAccount = new Account("ONETestAccount", "checking", actualAccount.getAccountNumber(), actualAccount.getBalance(), actualAccount.getInterest(), LocalDate.now(), LocalDate.now());
        expectedAccount.setMemberId(5);
        Assertions.assertEquals(expectedAccount, actualAccount);
    }
 }
