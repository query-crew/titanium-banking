package com.titanium.transactions.service;

import com.titanium.transactions.model.Transaction;
import com.titanium.transactions.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<Map<String, Object>> getAllTransactions(int page, int size) {
        try {
            List<Transaction> transactions;
            Pageable paging = PageRequest.of(page, size);
            Page<Transaction> pageTransaction;
            pageTransaction = transactionRepository.findAll(paging);
            transactions = new ArrayList<>(pageTransaction.getContent());
            Map<String, Object> response = new HashMap<>();
            response.put("transactions", transactions);
            response.put("currentPage", pageTransaction.getNumber());
            response.put("totalItems", pageTransaction.getTotalElements());
            response.put("totalPages", pageTransaction.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", ex);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> retrieveTransactionByAccountFromId(int accountFromId, String description, String sortProp, int page, int size) {
        try {
            List<Transaction> transactions;
            Pageable paging = PageRequest.of(page, size);
            Page<Transaction> pageTransaction = null;
            if ((description == null || description.length() == 0) && (sortProp == null || sortProp.length() == 0)) {
                //no search input and no sort params
                pageTransaction = transactionRepository.findByAccountFromId(accountFromId, paging);
            }
            else if (sortProp == null || sortProp.length() == 0) {
                //no sort params
                pageTransaction = transactionRepository.findByAccountFromIdAndDescriptionContainingIgnoreCase(accountFromId, description, paging);

            } else if (description == null || description.length() == 0){
                switch(sortProp) {
                    case "datea" :
                        pageTransaction = transactionRepository.findByAccountFromIdOrderByTransactionDateAsc(accountFromId, paging);
                        break;
                    case "dated" :
                        pageTransaction = transactionRepository.findByAccountFromIdOrderByTransactionDateDesc(accountFromId, paging);
                        break;
                    case "amounta" :
                        pageTransaction = transactionRepository.findByAccountFromIdOrderByAmountAsc(accountFromId, paging);
                        break;
                    case "amountd" :
                        pageTransaction = transactionRepository.findByAccountFromIdOrderByAmountDesc(accountFromId, paging);
                        break;
                    default:
                        System.out.println("this shouldn't happen, add error handling here");
                }
            } else {
                //search input filled, sort param selected
                switch(sortProp) {
                    case "datea":
                        pageTransaction = transactionRepository.findByAccountFromIdAndDescriptionContainingIgnoreCaseOrderByTransactionDateAsc(accountFromId, description, paging);
                        break;
                    case "dated":
                        pageTransaction = transactionRepository.findByAccountFromIdAndDescriptionContainingIgnoreCaseOrderByTransactionDateDesc(accountFromId, description, paging);
                        break;
                    case "amounta":
                        pageTransaction = transactionRepository.findByAccountFromIdAndDescriptionContainingIgnoreCaseOrderByAmountAsc(accountFromId, description, paging);
                        break;
                    case "amountd":
                        pageTransaction = transactionRepository.findByAccountFromIdAndDescriptionContainingIgnoreCaseOrderByAmountDesc(accountFromId, description, paging);
                        break;
                    default:
                        System.out.println("this shouldn't happen, add error handling here");
                }
            }
            Map<String, Object> response = new HashMap<>();
            if (pageTransaction == null) {
                transactions = new ArrayList<>();
                response.put("transactions", transactions);
                response.put("currentPage", 0);
                response.put("totalItems", 0);
                response.put("totalPages", 0);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            transactions = new ArrayList<>(pageTransaction.getContent());
            response.put("transactions", transactions);
            response.put("currentPage", pageTransaction.getNumber());
            response.put("totalItems", pageTransaction.getTotalElements());
            response.put("totalPages", pageTransaction.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", ex);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> retrieveTransactionByAccountToId(int accountToId, String description, String sortProp, int page, int size) {
        try {
            List<Transaction> transactions;
            Pageable paging = PageRequest.of(page, size);
            Page<Transaction> pageTransaction = null;
            if ((description == null || description.length() == 0) && (sortProp == null || sortProp.length() == 0)) {
                //no search input and no sort params
                pageTransaction = transactionRepository.findByAccountToId(accountToId, paging);
            }
            else if (sortProp == null || sortProp.length() == 0) {
                //no sort params
                pageTransaction = transactionRepository.findByAccountToIdAndDescriptionContainingIgnoreCase(accountToId, description, paging);

            } else if (description == null || description.length() == 0){
                switch(sortProp) {
                    case "datea" :
                        pageTransaction = transactionRepository.findByAccountToIdOrderByTransactionDateAsc(accountToId, paging);
                        break;
                    case "dated" :
                        pageTransaction = transactionRepository.findByAccountToIdOrderByTransactionDateDesc(accountToId, paging);
                        break;
                    case "amounta" :
                        pageTransaction = transactionRepository.findByAccountToIdOrderByAmountAsc(accountToId, paging);
                        break;
                    case "amountd" :
                        pageTransaction = transactionRepository.findByAccountToIdOrderByAmountDesc(accountToId, paging);
                        break;
                    default:
                        System.out.println("this shouldn't happen, add error handling here");
                }
            } else {
                //search input filled, sort param selected
                switch(sortProp) {
                    case "datea":
                        pageTransaction = transactionRepository.findByAccountToIdAndDescriptionContainingIgnoreCaseOrderByTransactionDateAsc(accountToId, description, paging);
                        break;
                    case "dated":
                        pageTransaction = transactionRepository.findByAccountToIdAndDescriptionContainingIgnoreCaseOrderByTransactionDateDesc(accountToId, description, paging);
                        break;
                    case "amounta":
                        pageTransaction = transactionRepository.findByAccountToIdAndDescriptionContainingIgnoreCaseOrderByAmountAsc(accountToId, description, paging);
                        break;
                    case "amountd":
                        pageTransaction = transactionRepository.findByAccountToIdAndDescriptionContainingIgnoreCaseOrderByAmountDesc(accountToId, description, paging);
                        break;
                    default:
                        System.out.println("this shouldn't happen, add error handling here");
                }
            }
            Map<String, Object> response = new HashMap<>();
            if (pageTransaction == null) {
                transactions = new ArrayList<>();
                response.put("transactions", transactions);
                response.put("currentPage", 0);
                response.put("totalItems", 0);
                response.put("totalPages", 0);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            transactions = new ArrayList<>(pageTransaction.getContent());
            response.put("transactions", transactions);
            response.put("currentPage", pageTransaction.getNumber());
            response.put("totalItems", pageTransaction.getTotalElements());
            response.put("totalPages", pageTransaction.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", ex);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> retrieveTransactionByDate(Timestamp transactionDate, int page, int size) {
        try {
            List<Transaction> transactions;
            Pageable paging = PageRequest.of(page, size);
            Page<Transaction> pageTransaction;
            pageTransaction = transactionRepository.findByTransactionDate(transactionDate, paging);
            transactions = new ArrayList<>(pageTransaction.getContent());
            if(transactions.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            else {
                Map<String, Object> response = new HashMap<>();
                response.put("transactions", transactions);
                response.put("currentPage", pageTransaction.getNumber());
                response.put("totalItems", pageTransaction.getTotalElements());
                response.put("totalPages", pageTransaction.getTotalPages());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch(Exception ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", ex);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Map<String, Object>> retrieveTransactionByType(int transactionType, int page, int size) {
        try {
            List<Transaction> transactions;
            Pageable paging = PageRequest.of(page, size);
            Page<Transaction> pageTransaction;
            pageTransaction = transactionRepository.findByTransactionType(transactionType, paging);
            transactions = new ArrayList<>(pageTransaction.getContent());
            if(transactions.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            else {
                Map<String, Object> response = new HashMap<>();
                response.put("transactions", transactions);
                response.put("currentPage", pageTransaction.getNumber());
                response.put("totalItems", pageTransaction.getTotalElements());
                response.put("totalPages", pageTransaction.getTotalPages());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch(Exception ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", ex);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Map<String, Object>> getTransaction(int transactionId) {
        try {
            Transaction transaction = transactionRepository.findById(transactionId);
            if(transaction == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            else {
                Map<String, Object> response = new HashMap<>();
                response.put("transaction", transaction);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch(Exception ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", ex);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> createTransaction(Transaction transactionData) {
        try {
            transactionRepository.save(transactionData);
            Map<String, Object> response = new HashMap<>();
            response.put("Transaction", "Created");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        }
        catch(Exception ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", ex);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}