package com.smoothstack.Branch.controller;

import com.smoothstack.Branch.dto.BranchDto;
import com.smoothstack.Branch.exception.BranchNotFoundException;
import com.smoothstack.Branch.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://localhost:3000", "https://localhost:4200"})
public class BranchController {
    @Autowired
    BranchService branchService;

    //Create
    @RequestMapping(path = "/branch", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addNewBranch(@RequestBody BranchDto branch) {
        return new ResponseEntity<>(branchService.addBranch(branch), HttpStatus.CREATED);
    }
//    Read
    @GetMapping(path = "/branch")
    public ResponseEntity<Map<String, Object>> findAllBranches(@RequestParam(required = false) String branchName, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        return new ResponseEntity<>(branchService.findAllBranches(branchName, page, size), HttpStatus.OK);
    }

    @RequestMapping(path = "/branch/{branchId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findBranch(@PathVariable int branchId) {
        Map<String, Object> res = new HashMap<>();
        try {
            res.put("branch", branchService.findBranchDto(branchId));
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (BranchNotFoundException e) {
            res.put("error", e.getMessage());
            return new ResponseEntity<>(res, e.getStatus());
        }
    }
    //Update
    @RequestMapping(path = "/branch/{branchId}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Object>> updateBranch(@RequestBody BranchDto branch, @PathVariable int branchId) {
        try {
            return new ResponseEntity<>(branchService.updateBranch(branchId, branch), HttpStatus.OK);
        } catch (BranchNotFoundException e) {
            Map<String, Object> res = new HashMap<>();
            res.put("error", e.getMessage());
            return new ResponseEntity<>(res, e.getStatus());
        }
    }
    //Delete
    @RequestMapping(path = "/branch", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> removeAllBranches() {
        branchService.removeAllBranches();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    @RequestMapping(path = "/branch/{branchId}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> removeBranch(@PathVariable int branchId) {
        try {
            branchService.removeBranch(branchId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (BranchNotFoundException e) {
            Map<String, Object> res = new HashMap<>();
            res.put("error", e.getMessage());
            return new ResponseEntity<>(res, e.getStatus());
        }

    }
}
