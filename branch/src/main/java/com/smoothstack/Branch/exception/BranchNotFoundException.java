package com.smoothstack.Branch.exception;

public class BranchNotFoundException extends NotFoundException{
    public BranchNotFoundException() {
        super("Branch not found.");
    }
}
