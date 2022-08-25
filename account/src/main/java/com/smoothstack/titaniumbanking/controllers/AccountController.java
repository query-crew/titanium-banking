package com.smoothstack.titaniumbanking.controllers;

import com.smoothstack.titaniumbanking.dto.*;
import com.smoothstack.titaniumbanking.dto.AccountBalanceDto;
import com.smoothstack.titaniumbanking.exceptions.*;
import com.smoothstack.titaniumbanking.models.Account;
import com.smoothstack.titaniumbanking.services.AccountService;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = {"https://localhost:3000", "https://localhost:4200"}, allowCredentials = "true")
public class AccountController {
    
    @Autowired
    AccountService accountService;

    //create
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping(value="/account")
    public ResponseEntity<String> addAccount(@Valid @RequestBody AccountDto account) {
        try{
            accountService.addAccount(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (AccountExistsException e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('member')")
    @PostMapping(value="/account", params = "addAccountWithInitialDeposit")
    public ResponseEntity<String> addAccountWithInitialDeposit(@Valid @RequestBody InitialDepositAccountDto account) {
        try{
            accountService.addAccountWithInitialDeposit(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (AccountExistsException | StripeException | MemberNotAuthorizedException e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value="/account")
    public ResponseEntity<Map<String, Object>> getAllAccounts(@RequestParam int pageNo, @RequestParam int pageSize){
        HashMap<String, Object> map = new HashMap();
        try {
            map.put("accounts", accountService.getAllAccounts(pageNo, pageSize));
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        catch (Exception e) {
            map.put("error", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('member')")
    @GetMapping(value="/account/{accountId}")
    public ResponseEntity<Map<String, Object>> getAccount(@PathVariable int accountId){
        HashMap map = new HashMap();
        try {
            map.put("success", accountService.getAccountById(accountId));
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        catch (AccountNotFoundException e) {
            map.put("error", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping(value="/account/{accountId}")
    public ResponseEntity<String> updateAccount(@PathVariable int accountId, @Valid @RequestBody AccountDto account) {
        try {
            accountService.updateAccountById(accountId, account);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (AccountNotFoundException | AccountTypeNotFoundException | InitialDepositBelowLimitException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value="/account/{accountId}", params = "setEnabled")
    public ResponseEntity<String> setAccountEnabled(@PathVariable int accountId, @Valid @RequestBody AccountEnabledDto accountEnabledDto) {
        try {
            accountService.setAccountEnabled(accountId, accountEnabledDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (AccountNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value="/account/{accountId}", params = "setBalance")
    public ResponseEntity<String> setAccountBalance(@PathVariable int accountId, @Valid @RequestBody AccountBalanceDto accountBalanceDto) {
        try {
            accountService.setAccountBalance(accountId, accountBalanceDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (AccountNotFoundException | BalanceBelowLimitException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping(value="/account/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable int accountId){
        try {
            accountService.deleteAccountById(accountId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (AccountNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping(value="/account")
    public ResponseEntity<String> deleteAllAccounts(){
        try {
            accountService.deleteAllAccounts();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (AccountNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('member')")
    @GetMapping(value="/member/{memberId}/account")
    public ResponseEntity<Map<String, Object>> getAccountsByMemberId(@PathVariable int memberId) {
        HashMap<String, Object> map = new HashMap<>();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('management')")
    @PostMapping(value="/accountType")
    public ResponseEntity<String> addAccountType(@Valid @RequestBody AccountTypeDto accountType) {
        try{
            accountService.addAccountType(accountType);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (AccountExistsException e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    // Read
    @PreAuthorize("hasAuthority('admin') or hasAuthority('member') or hasAuthority('management')")
    @GetMapping(value="/accountType")
    public ResponseEntity<Map<String, Object>> getAllAccountTypes() {
        HashMap<String, Object> map = new HashMap<>();
        try{
            map.put("accountTypes", accountService.getAllAccountTypes());
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (AccountExistsException e){
            map.put("error", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('member') or hasAuthority('full_access')")
    @GetMapping(value="/accountType/{accountTypeId}")
    public ResponseEntity<Map<String, Object>> getAccountTypeById(@PathVariable int accountTypeId) {
        HashMap<String, Object> map = new HashMap<>();
        try{
            map.put("accountType", accountService.getAccountTypeById(accountTypeId));
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (AccountExistsException e){
            map.put("error", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    // Update
    @PreAuthorize("hasAuthority('management')")
    @PutMapping(value="/accountType/{accountTypeId}")
    public ResponseEntity<String> updateAccountTypeById(@PathVariable int accountTypeId, @Valid @RequestBody AccountTypeDto accountType) {
        try{
            accountService.updateAccountTypeById(accountTypeId, accountType);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (AccountExistsException e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete
    @PreAuthorize("hasAuthority('management')")
    @DeleteMapping(value="/accountType/{accountTypeId}")
    public ResponseEntity<String> deleteAccountTypeById(@PathVariable int accountTypeId) {
        try{
            accountService.deleteAccountTypeById(accountTypeId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (AccountExistsException e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
