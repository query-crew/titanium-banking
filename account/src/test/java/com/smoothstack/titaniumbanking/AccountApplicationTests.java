 package com.smoothstack.titaniumbanking;

 import java.time.LocalDate;
 import java.util.ArrayList;
 import java.util.List;

 import com.smoothstack.titaniumbanking.dto.AccountTypeDto;
 import com.smoothstack.titaniumbanking.models.AccountType;
 import org.junit.jupiter.api.*;
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
     void setUp() {
         if (service.accountExists()) {
             service.deleteAllAccounts();
         }
         if (service.accountTypeExists()) {
             service.deleteAllAccountTypes();
         }
     }

     @Test
     void contextLoads() {
     }


     @Test
     void viewAllAccounts() {
         AccountTypeDto accountTypeDto = new AccountTypeDto();
         accountTypeDto.setAccountType("Titanium Test");
         accountTypeDto.setAccountTypeAbbr("Test");
         accountTypeDto.setInterest(2);
         accountTypeDto.setBalanceRequirement(2500L);
         AccountType type = service.addAccountType(accountTypeDto);
         int typeId = type.getAccountTypeId();

         AccountDto accountDto = new AccountDto();
         accountDto.setBalance(2500L);
         accountDto.setLastStatementDate(LocalDate.now().toString());
         accountDto.setEnabled(1);
         accountDto.setAccountTypeId(typeId);
         accountDto.setMemberId(1);
         Account addedAcct1 = service.addAccount(accountDto);

         AccountDto account2Dto = new AccountDto();
         account2Dto.setBalance(2600L);
         account2Dto.setLastStatementDate(LocalDate.now().toString());
         account2Dto.setEnabled(1);
         account2Dto.setAccountTypeId(typeId);
         account2Dto.setMemberId(2);
         Account addedAcct2 = service.addAccount(account2Dto);

         AccountDto account3Dto = new AccountDto();
         account3Dto.setBalance(2700L);
         account3Dto.setLastStatementDate(LocalDate.now().toString());
         account3Dto.setEnabled(1);
         account3Dto.setAccountTypeId(typeId);
         account3Dto.setMemberId(3);
         Account addedAcct3 = service.addAccount(account3Dto);

         Account account = new Account(addedAcct1.getAccountNumber(), 2500L, addedAcct1.getLastStatementDate(), 1, 1);
         account.setAccountType(type);
         Account account2 = new Account(addedAcct2.getAccountNumber(), 2600L, addedAcct2.getLastStatementDate(), 1, 2);
         account2.setAccountType(type);
         Account account3 = new Account(addedAcct3.getAccountNumber(), 2700L, addedAcct3.getLastStatementDate(), 1, 3);
         account3.setAccountType(type);

         List<Account> expectedAccounts = new ArrayList<>();
         expectedAccounts.add(account);
         expectedAccounts.add(account2);
         expectedAccounts.add(account3);

         List<Account> actualAccounts = service.getAllAccounts(0, 10);
         Assertions.assertEquals(expectedAccounts, actualAccounts);
     }

     //add account test
     @Test
     void addAccountTest() {
         AccountTypeDto accountTypeDto = new AccountTypeDto();
         accountTypeDto.setAccountType("Titanium Test");
         accountTypeDto.setAccountTypeAbbr("Test");
         accountTypeDto.setInterest(2);
         accountTypeDto.setBalanceRequirement(2500L);
         AccountType type = service.addAccountType(accountTypeDto);

         AccountDto accountDto = new AccountDto();
         accountDto.setBalance(2500L);
         accountDto.setLastStatementDate(LocalDate.now().toString());
         accountDto.setEnabled(1);
         accountDto.setAccountTypeId(type.getAccountTypeId());
         accountDto.setMemberId(1);
         Account account = service.addAccount(accountDto);
         Account expectedAccount = new Account(account.getAccountNumber(), 2500L, account.getLastStatementDate(), 1, 1);
         expectedAccount.setAccountType(type);
         Assertions.assertEquals(expectedAccount, account);
     }

     //update account test
     @Test
     void updateAccountTest() {
         AccountTypeDto accountTypeDto = new AccountTypeDto();
         accountTypeDto.setAccountType("Titanium Test");
         accountTypeDto.setAccountTypeAbbr("Test");
         accountTypeDto.setInterest(2);
         accountTypeDto.setBalanceRequirement(2500L);
         AccountType type = service.addAccountType(accountTypeDto);

         AccountDto accountDto = new AccountDto();
         accountDto.setBalance(2500L);
         accountDto.setLastStatementDate(LocalDate.now().toString());
         accountDto.setEnabled(1);
         accountDto.setAccountTypeId(type.getAccountTypeId());
         accountDto.setMemberId(1);
         Account initialAccount = service.addAccount(accountDto);

         AccountDto updatedAccountDto = new AccountDto();
         updatedAccountDto.setBalance(3000L);
         updatedAccountDto.setLastStatementDate(LocalDate.now().toString());
         updatedAccountDto.setEnabled(0);
         updatedAccountDto.setAccountTypeId(type.getAccountTypeId());
         updatedAccountDto.setMemberId(5);

         Account updatedAccount = service.updateAccountById(initialAccount.getAccountId(), updatedAccountDto);

         Account expectedAccount = new Account();
         expectedAccount.setAccountId(initialAccount.getAccountId());
         expectedAccount.setAccountNumber(initialAccount.getAccountNumber());
         expectedAccount.setBalance(3000L);
         expectedAccount.setEnabled(0);
         expectedAccount.setAccountType(initialAccount.getAccountType());
         expectedAccount.setMemberId(5);
         expectedAccount.setLastStatementDate(LocalDate.now());

         Assertions.assertEquals(expectedAccount, updatedAccount);
     }

     @Test
     void deleteAccountTest() {
         AccountTypeDto accountTypeDto = new AccountTypeDto();
         accountTypeDto.setAccountType("Titanium Test");
         accountTypeDto.setAccountTypeAbbr("Test");
         accountTypeDto.setInterest(2);
         accountTypeDto.setBalanceRequirement(2500L);
         AccountType type = service.addAccountType(accountTypeDto);

         AccountDto accountDto = new AccountDto();
         accountDto.setBalance(2500L);
         accountDto.setLastStatementDate(LocalDate.now().toString());
         accountDto.setEnabled(1);
         accountDto.setAccountTypeId(type.getAccountTypeId());
         accountDto.setMemberId(1);
         Account account = service.addAccount(accountDto);

         Account expectedAccount = new Account();
         expectedAccount.setAccountNumber(account.getAccountNumber());
         expectedAccount.setAccountType(account.getAccountType());
         expectedAccount.setBalance(2500L);
         expectedAccount.setEnabled(1);
         expectedAccount.setMemberId(1);
         expectedAccount.setLastStatementDate(LocalDate.now());
         Account actualAccount = service.deleteAccountById(account.getAccountId());

         Assertions.assertEquals(expectedAccount, actualAccount);
         Assertions.assertEquals(service.getAllAccounts(0, 10).size(), 0);
     }
 }
