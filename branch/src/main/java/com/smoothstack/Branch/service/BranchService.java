package com.smoothstack.Branch.service;

import com.smoothstack.Branch.dto.BranchDto;
import com.smoothstack.Branch.exception.BranchNotFoundException;
import com.smoothstack.Branch.model.Address;
import com.smoothstack.Branch.model.Branch;
import com.smoothstack.Branch.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BranchService {
    @Autowired
    BranchRepository branchRepository;
    //Create
    public Map<String, Object> addBranch(BranchDto newBranch) {
        Branch branch = new Branch(newBranch.getBranchName());
        Address newBranchAddress = new Address(newBranch.getAddressLine1(), newBranch.getAddressLine2(), newBranch.getCity(), newBranch.getState(), newBranch.getZipCode());
        branch.setBranchDetails(newBranch.getBranchDetails());
        newBranchAddress.setBranch(branch);
        branch.setAddress(newBranchAddress);
        branchRepository.save(branch);
        Map<String, Object> response = new HashMap<>();
        response.put("branch", toDto(branch));
        return response;
    }

    //Read
    public Map<String, Object> findAllBranches(String branchName, int page, int size) {
        List<BranchDto> branches;
        Pageable paging = PageRequest.of(page, size);
        Page<Branch> pageBranch;
        if (branchName == null || branchName.length() == 0) {
            pageBranch = branchRepository.findAll(paging);
        }
        else {
            pageBranch = branchRepository.findByBranchNameContainingIgnoreCase(branchName, paging);
        }
            branches = pageBranch.getContent().stream().map(this::toDto).collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("branches", branches);
        response.put("currentPage", pageBranch.getNumber());
        response.put("totalItems", pageBranch.getTotalElements());
        response.put("totalPages", pageBranch.getTotalPages());
        return response;
    }
    public Branch findBranch(int branchId) {
        Branch branch = branchRepository.findByBranchId(branchId);
        if (branch == null) {
            throw new BranchNotFoundException();
        }
        return branch;
    }
    public Map<String, Object> findBranchDto(int branchId) throws BranchNotFoundException {
        HashMap<String, Object> response = new HashMap<>();
        response.put("branch", toDto(findBranch(branchId)));
        return response;
    }
    //Update
    public Map<String, Object> updateBranch(int branchId, BranchDto newBranch) {
        Branch targetBranch = findBranch(branchId);
        if (targetBranch == null) {
            throw new BranchNotFoundException();
        }
        targetBranch.setBranchName(newBranch.getBranchName());
        targetBranch.setBranchDetails(newBranch.getBranchDetails());
        Address targetAddress = targetBranch.getAddress();
        targetAddress.setAddressLine1(newBranch.getAddressLine1());
        targetAddress.setAddressLine2(newBranch.getAddressLine2());
        targetAddress.setCity(newBranch.getCity());
        targetAddress.setState(newBranch.getState());
        targetAddress.setZipCode(newBranch.getZipCode());
        branchRepository.save(targetBranch);

        HashMap<String, Object> response = new HashMap<>();
        response.put("branch", toDto(findBranch(branchId)));

        return response;
    }

    //Delete
    public void removeBranch(int branchId) {
        Branch targetBranch = findBranch(branchId);
        if (targetBranch == null) {
            throw new BranchNotFoundException();
        }
        branchRepository.delete(targetBranch);
    }
    public void removeAllBranches() {
        branchRepository.deleteAll();
    }

    //Conversion
    public BranchDto toDto (Branch branch) {
        //model mapper misses some values for some reason (city, state, zip)
//        BranchDto branchDto = modelMapper.map(branch, BranchDto.class);
        BranchDto branchDto = new BranchDto();
        branchDto.setBranchId(branch.getBranchId());
        branchDto.setBranchName(branch.getBranchName());
        branchDto.setBranchDetails(branch.getBranchDetails());
        branchDto.setAddressLine1(branch.getAddress().getAddressLine1());
        branchDto.setAddressLine2(branch.getAddress().getAddressLine2());
        branchDto.setCity(branch.getAddress().getCity());
        branchDto.setState(branch.getAddress().getState());
        branchDto.setZipCode(branch.getAddress().getZipCode());
        return branchDto;
    }

    //Testing
    public Branch addBranchTest(BranchDto newBranch) {
        Branch branch = new Branch(newBranch.getBranchName());
        Address newBranchAddress = new Address(newBranch.getAddressLine1(), newBranch.getAddressLine2(), newBranch.getCity(), newBranch.getState(), newBranch.getZipCode());
        newBranchAddress.setBranch(branch);
        branch.setAddress(newBranchAddress);
        branchRepository.save(branch);
        return branch;
    }

    public boolean hasBranches() {
        return branchRepository.count() != 0;
    }
}
