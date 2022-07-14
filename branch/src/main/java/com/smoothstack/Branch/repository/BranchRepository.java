package com.smoothstack.Branch.repository;

import com.smoothstack.Branch.model.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
    Page<Branch> findAll(Pageable pageable);
    Page<Branch> findByBranchName(String branchName, Pageable pageable);
    Branch findByBranchId(int id);
}
