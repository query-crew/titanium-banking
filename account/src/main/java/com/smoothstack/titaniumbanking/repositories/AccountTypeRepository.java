package com.smoothstack.titaniumbanking.repositories;
import com.smoothstack.titaniumbanking.models.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    AccountType findByAccountTypeId(int id);

    AccountType findByAccountType(String accountType);
    boolean existsByAccountType(String accountType);
}