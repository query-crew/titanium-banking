package com.smoothstack.titaniumbanking.services;


import com.smoothstack.titaniumbanking.dto.*;
import com.smoothstack.titaniumbanking.exceptions.*;
import com.smoothstack.titaniumbanking.models.Account;

import java.time.LocalDate;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import com.smoothstack.titaniumbanking.models.AccountType;
import com.smoothstack.titaniumbanking.repositories.AccountRepository;

import com.smoothstack.titaniumbanking.repositories.AccountTypeRepository;
import lombok.RequiredArgsConstructor;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepo;
    private final AccountTypeRepository accountTypeRepo;

    public Account addAccount(AccountDto accountDto) throws AccountTypeNotFoundException {
        AccountType accountType = accountTypeRepo.findByAccountTypeId(accountDto.getAccountTypeId());
        if (accountType == null) {
            throw new AccountTypeNotFoundException();
        }
        if (accountDto.getBalance() < accountType.getBalanceRequirement()) {
            throw new InitialDepositBelowLimitException();
        }
        Account account = new Account(generateAccountNumber(accountType.getAccountType(), accountDto.getMemberId()), accountDto.getBalance(), LocalDate.parse(accountDto.getLastStatementDate()), accountDto.getEnabled(), accountDto.getMemberId());
        account.setAccountType(accountType);
        accountRepo.save(account);
        return account;
    }

    public Account addAccountWithInitialDeposit(InitialDepositAccountDto initialDepositAccountDto) throws AccountTypeNotFoundException, StripeException, MemberNotAuthorizedException {
        initiateInitialDeposit(initialDepositAccountDto.getPaymentMethodId(), initialDepositAccountDto.getBalance());
        AccountType accountType = accountTypeRepo.findByAccountTypeId(initialDepositAccountDto.getAccountTypeId());
        if (accountType == null) {
            throw new AccountTypeNotFoundException();
        }
        if (accountType.getLoanId() != 0) {
            throw new MemberNotAuthorizedException();
        }
        if (initialDepositAccountDto.getBalance() < accountType.getBalanceRequirement()) {
            throw new InitialDepositBelowLimitException();
        }
        Account account = new Account(generateAccountNumber(accountType.getAccountType(), initialDepositAccountDto.getMemberId()), initialDepositAccountDto.getBalance(), LocalDate.parse(initialDepositAccountDto.getLastStatementDate()), 0, initialDepositAccountDto.getMemberId());
        account.setAccountType(accountType);
        accountRepo.save(account);
        return account;
    }

    // read
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

    public Account updateAccountById(int accountId, AccountDto accountDto) throws AccountNotFoundException, AccountTypeNotFoundException, InitialDepositBelowLimitException {
        AccountType accountType = accountTypeRepo.findByAccountTypeId(accountDto.getAccountTypeId());
        Account account = getAccountById(accountId);
        if (accountType == null) {
            throw new AccountTypeNotFoundException();
        }
        if (accountDto.getBalance() < accountType.getBalanceRequirement()) {
            throw new InitialDepositBelowLimitException();
        }
        account.setBalance(accountDto.getBalance());
        account.setLastStatementDate(LocalDate.parse(accountDto.getLastStatementDate()));
        account.setAccountType(accountType);
        account.setEnabled(accountDto.getEnabled());
        account.setMemberId(accountDto.getMemberId());
        return account;
    }

    public Account setAccountEnabled(int accountId, AccountEnabledDto accountEnabledDto) throws AccountNotFoundException {
        Account account = getAccountById(accountId);
        account.setEnabled(accountEnabledDto.getEnabled());
        accountRepo.save(account);
        return account;
    }

    public Account setAccountBalance(int accountId, AccountBalanceDto accountBalanceDto) throws AccountNotFoundException, AccountTypeNotFoundException, BalanceBelowLimitException {
        Account account = getAccountById(accountId);
        account.setBalance(accountBalanceDto.getBalanceAmount());
        accountRepo.save(account);
        return account;
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
    private String generateAccountNumber(String accountType, int memberId){
        int num = generatePrimeNumber();
        return String.format("%017d", Math.abs(Objects.hash(num, accountType, memberId))) ;
    }

    //generate prime number used as a salt value
    private Integer generatePrimeNumber(){
        int num;
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

    public boolean accountTypeExists() {
        return accountTypeRepo.count() != 0;
    }

    public AccountType addAccountType(AccountTypeDto accountTypeDto) throws AccountTypeExistsException {
        if(accountTypeRepo.existsByAccountType(accountTypeDto.getAccountType())) {
            throw new AccountTypeExistsException();
        }
        AccountType accountType = new AccountType(accountTypeDto.getAccountType(), accountTypeDto.getAccountTypeAbbr(), accountTypeDto.getInterest(), accountTypeDto.getBalanceRequirement());
        accountTypeRepo.save(accountType);
        return accountType;
    }

    public List<AccountType> getAllAccountTypes() {
        return accountTypeRepo.findAll();
    }

    public AccountType getAccountTypeById(int accountTypeId) throws AccountTypeNotFoundException {
        AccountType accountType = accountTypeRepo.findByAccountTypeId(accountTypeId);
        if (accountType == null) {
            throw new AccountTypeNotFoundException();
        }
        return accountType;
    }

    public AccountType updateAccountTypeById(int accountTypeId, AccountTypeDto accountTypeDto) throws AccountTypeNotFoundException {
        AccountType accountTypeToUpdate = getAccountTypeById(accountTypeId);
        accountTypeToUpdate.setAccountType(accountTypeDto.getAccountType());
        accountTypeToUpdate.setAccountTypeAbbr(accountTypeDto.getAccountTypeAbbr());
        accountTypeToUpdate.setInterest(accountTypeDto.getInterest());
        accountTypeToUpdate.setBalanceRequirement(accountTypeDto.getBalanceRequirement());
        accountTypeRepo.save(accountTypeToUpdate);
        return accountTypeToUpdate;
    }

    public AccountType deleteAccountTypeById(int accountTypeId) throws AccountTypeNotFoundException {
        AccountType accountTypeToDelete = getAccountTypeById(accountTypeId);
        accountTypeRepo.delete(accountTypeToDelete);
        return accountTypeToDelete;
    }

    public void deleteAllAccountTypes() {
        accountTypeRepo.deleteAll();
    }

    public void initiateInitialDeposit(String paymentMethodId, Long balance) throws StripeException, PaymentFailedException {
        PaymentIntent intent;
        if (paymentMethodId != null) {
                PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder()
                        .setAmount(balance)
                        .setCurrency("usd")
                        .setConfirm(true)
                        .setPaymentMethod(paymentMethodId)
                        .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.MANUAL)
                        .build();
                intent = PaymentIntent.create(createParams);
                if (!intent.getStatus().equals("succeeded")) {
                    throw new PaymentFailedException();
                }
        }
    }
}
