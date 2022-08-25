package com.smoothstack.titaniumbanking.exceptions;

public class InitialDepositBelowLimitException extends BadRequestException {
    public InitialDepositBelowLimitException() { super("Initial deposit is not enough."); }
}
