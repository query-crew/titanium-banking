package com.smoothstack.Branch.controller;

import com.smoothstack.Branch.dto.BranchDto;
import com.smoothstack.Branch.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BranchController {
    @Autowired
    BranchService branchService;

    //Create
    @RequestMapping(path = "/branch", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addNewBranch(@RequestBody BranchDto branch) {
        return branchService.addBranch(branch);
    }
//    Read
    @GetMapping(path = "/branch")
    public ResponseEntity<Map<String, Object>> findAllBranches(@RequestParam(required = false) String branchName, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        return branchService.findAllBranches(branchName, page, size);
    }

    @RequestMapping(path = "/branch/{branchId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findBranch(@PathVariable int branchId) {
        return branchService.findBranchDto(branchId);
    }
    //Update
    @RequestMapping(path = "/branch/{branchId}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> updateBranch(@RequestBody BranchDto branch, @PathVariable int branchId) {
        return branchService.updateBranch(branchId, branch);
    }
    //Delete
    @RequestMapping(path = "/branch", method = RequestMethod.DELETE)
    public void removeAllBranches() {
        branchService.removeAllBranches();
    }
    @RequestMapping(path = "/branch/{branchId}", method = RequestMethod.DELETE)
    public void removeBranch(@PathVariable int branchId) {
        branchService.removeBranch(branchId);
    }
}
