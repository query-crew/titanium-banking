package com.titanium.transactions.repository;

import com.titanium.transactions.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findById(int id);
    Page<Transaction> findAll(Pageable pageable);
    //Find by accountFromId, no description, no sort
    Page<Transaction> findByAccountFromId(int accountFromId, Pageable pageable);
    //Find by accountToId, no description, no sort
    Page<Transaction> findByAccountToId(int accountToId, Pageable pageable);
    //Find by accountFromId or accountToId, no description, no sort
    Page<Transaction> findByAccountFromIdOrAccountToId(int accountFromId, int accountToId, Pageable pageable);
    //Find by accountFromId and Description contains description. No sort
    Page<Transaction> findByAccountFromIdAndDescriptionContainingIgnoreCase(int accountFromId, String description, Pageable pageable);
    //Find by accountToId and Description contains description. No sort
    Page<Transaction> findByAccountToIdAndDescriptionContainingIgnoreCase(int accountToId, String description, Pageable pageable);
    //Query version of find by accountId and Desc no sort.
    @Query ("SELECT t from Transaction t WHERE (t.accountFromId = :accountId AND UPPER(t.description) LIKE %:description%) OR (t.accountToId = :accountId AND UPPER(t.description) LIKE %:description%)")
    Page<Transaction> findByAccountIdWithDescription(@Param("accountId") int accountId, @Param("description") String description, Pageable pageable);
    //Find by accountFromId or accountToId, and Description contains description, no sort.
    Page<Transaction> findByAccountFromIdOrAccountToIdAndDescriptionContainingIgnoreCase(int accountFromId, int accountToId, String description, Pageable pageable);
    //Find by accountFromId, no description, Sort date asc,
    Page<Transaction> findByAccountFromIdOrderByTransactionDateAsc(int accountFromId, Pageable pageable);
    //Find by accountToId, no description, Sort date asc,
    Page<Transaction> findByAccountToIdOrderByTransactionDateAsc(int accountToId, Pageable pageable);
    //Query: Find by accountFrom or accountToId, no description, sort date asc
    @Query("SELECT t from Transaction t WHERE (t.accountFromId = :accountId) OR (t.accountToId = :accountId) ORDER BY t.transactionDate ASC")
    Page<Transaction> findByAccountIdSortDateAsc(@Param("accountId") int accountId, Pageable pageable);
    //Find by accountFromId or accountToId, no description, Sort date asc
    Page<Transaction> findByAccountFromIdOrAccountToIdOrderByTransactionDateAsc(int accountFromId, int accountToId, Pageable pageable);
    //Find by accountFromId, no description, Sort date desc,
    Page<Transaction> findByAccountFromIdOrderByTransactionDateDesc(int accountFromId, Pageable pageable);
    //Find by accountToId, no description, Sort date desc,
    Page<Transaction> findByAccountToIdOrderByTransactionDateDesc(int accountToId, Pageable pageable);
    //Query: Find by accountFrom or accountToId, no description, sort date asc
    @Query("SELECT t from Transaction t WHERE (t.accountFromId = :accountId) OR (t.accountToId = :accountId) ORDER BY t.transactionDate DESC")
    Page<Transaction> findByAccountIdSortDateDesc(@Param("accountId") int accountId, Pageable pageable);
    //Find by accountFromId or accountToId, no description, Sort date desc,
    Page<Transaction> findByAccountFromIdOrAccountToIdOrderByTransactionDateDesc(int accountFromId, int accountToId, Pageable pageable);
    //Find by accountFromId, no description, Sort amount asc,
    Page<Transaction> findByAccountFromIdOrderByAmountAsc(int accountFromId, Pageable pageable);
    //Find by accountToId, no description, Sort amount asc,
    Page<Transaction> findByAccountToIdOrderByAmountAsc(int accountToId, Pageable pageable);
    //Query: Find by accountFrom or accountToId, no description, sort amount asc
    @Query("SELECT t from Transaction t WHERE (t.accountFromId = :accountId) OR (t.accountToId = :accountId) ORDER BY t.amount ASC")
    Page<Transaction> findByAccountIdSortAmountAsc(@Param("accountId") int accountId, Pageable pageable);
    //Find by accountFromId or accountToId, no description, Sort amount asc,
    Page<Transaction> findByAccountFromIdOrAccountToIdOrderByAmountAsc(int accountFromId, int accountToId, Pageable pageable);
    //Find by accountFromId, no description, Sort amount desc,
    Page<Transaction> findByAccountFromIdOrderByAmountDesc(int accountFromId, Pageable pageable);
    //Find by accountToId, no description, Sort amount desc,
    Page<Transaction> findByAccountToIdOrderByAmountDesc(int accountToId, Pageable pageable);
    //Query: Find by accountFrom or accountToId, no description, sort amount desc
    @Query("SELECT t from Transaction t WHERE (t.accountFromId = :accountId) OR (t.accountToId = :accountId) ORDER BY t.amount DESC")
    Page<Transaction> findByAccountIdSortAmountDesc(@Param("accountId") int accountId, Pageable pageable);
    //Find by accountFromId or accountToId, no description, Sort amount desc,
    Page<Transaction> findByAccountFromIdOrAccountToIdOrderByAmountDesc(int accountFromId, int accountToId, Pageable pageable);
    //Find by accountFromId, and description contains description, Sort date asc,
    Page<Transaction> findByAccountFromIdAndDescriptionContainingIgnoreCaseOrderByTransactionDateAsc(int accountFromId, String description, Pageable pageable);
    //Find by accountToId, and description contains description, Sort date asc,
    Page<Transaction> findByAccountToIdAndDescriptionContainingIgnoreCaseOrderByTransactionDateAsc(int accountToId, String description, Pageable pageable);
    //Query: find by accountId and description contains description, Sort date asc.
    @Query ("SELECT t from Transaction t WHERE (t.accountFromId = :accountId AND UPPER(t.description) LIKE %:description%) OR (t.accountToId = :accountId AND UPPER(t.description) LIKE %:description%) ORDER BY t.transactionDate ASC")
    Page<Transaction> findByAccountIdWithDescriptionSortDateAsc(@Param("accountId") int accountId, @Param("description") String description, Pageable pageable);
    //Find by accountFromId or accountToId, and description contains description, Sort date asc,
    Page<Transaction> findByAccountFromIdOrAccountToIdAndDescriptionContainingIgnoreCaseOrderByTransactionDateAsc(int accountFromId, int accountToId, String description, Pageable pageable);
    //Find by accountFromId, and description contains description, Sort date desc,
    Page<Transaction> findByAccountFromIdAndDescriptionContainingIgnoreCaseOrderByTransactionDateDesc(int accountFromId, String description, Pageable pageable);
    //Find by accountToId, and description contains description, Sort date asc,
    Page<Transaction> findByAccountToIdAndDescriptionContainingIgnoreCaseOrderByTransactionDateDesc(int accountToId, String description, Pageable pageable);
    //Query: find by accountId and description contains description, Sort date asc.
    @Query ("SELECT t from Transaction t WHERE (t.accountFromId = :accountId AND UPPER(t.description) LIKE %:description%) OR (t.accountToId = :accountId AND UPPER(t.description) LIKE %:description%) ORDER BY t.transactionDate DESC")
    Page<Transaction> findByAccountIdWithDescriptionSortDateDesc(@Param("accountId") int accountId, @Param("description") String description, Pageable pageable);
    //Find by accountFromId or accountToId, and description contains description, Sort date asc,
    Page<Transaction> findByAccountFromIdOrAccountToIdAndDescriptionContainingIgnoreCaseOrderByTransactionDateDesc(int accountFromId, int accountToId, String description, Pageable pageable);
    //Find by accountFromId, and description contains description, Sort amount asc,
    Page<Transaction> findByAccountFromIdAndDescriptionContainingIgnoreCaseOrderByAmountAsc(int accountFromId, String description, Pageable pageable);
    //Find by accountToId, and description contains description, Sort amount asc,
    Page<Transaction> findByAccountToIdAndDescriptionContainingIgnoreCaseOrderByAmountAsc(int accountToId, String description, Pageable pageable);
    //Query: find by accountId and description contains description, Sort amount asc.
    @Query ("SELECT t from Transaction t WHERE (t.accountFromId = :accountId AND UPPER(t.description) LIKE %:description%) OR (t.accountToId = :accountId AND UPPER(t.description) LIKE %:description%) ORDER BY t.amount ASC")
    Page<Transaction> findByAccountIdWithDescriptionSortAmountAsc(@Param("accountId") int accountId, @Param("description") String description, Pageable pageable);
    //Find by accountFromId or accountToId, and description contains description, Sort amount asc,
    Page<Transaction> findByAccountFromIdOrAccountToIdAndDescriptionContainingIgnoreCaseOrderByAmountAsc(int accountFromId, int accountToId, String description, Pageable pageable);
    //Find by accountFromId, and description contains description, Sort amount desc,
    Page<Transaction> findByAccountFromIdAndDescriptionContainingIgnoreCaseOrderByAmountDesc(int accountFromId, String description, Pageable pageable);
    //Find by accountToId, and description contains description, Sort amount desc,
    Page<Transaction> findByAccountToIdAndDescriptionContainingIgnoreCaseOrderByAmountDesc(int accountToId, String description, Pageable pageable);
    //Query: find by accountId and description contains description, Sort amount desc.
    @Query ("SELECT t from Transaction t WHERE (t.accountFromId = :accountId AND UPPER(t.description) LIKE %:description%) OR (t.accountToId = :accountId AND UPPER(t.description) LIKE %:description%) ORDER BY t.amount DESC")
    Page<Transaction> findByAccountIdWithDescriptionSortAmountDesc(@Param("accountId") int accountId, @Param("description") String description, Pageable pageable);
    //Find by accountFromId or accountToId, and description contains description, Sort amount desc,
    Page<Transaction> findByAccountFromIdOrAccountToIdAndDescriptionContainingIgnoreCaseOrderByAmountDesc(int accountFromid, int accountToId, String description, Pageable pageable);
    Page<Transaction> findByTransactionType(int transactionType, Pageable pageable);
    Page<Transaction> findByTransactionDate(Timestamp transactionDate, Pageable pageable);
}