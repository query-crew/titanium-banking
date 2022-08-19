package com.smoothstack.titaniumbanking.exceptions;

public class BalanceBelowLimitException extends BadRequestException {
    public BalanceBelowLimitException() { super("Balance is too low."); }
}
