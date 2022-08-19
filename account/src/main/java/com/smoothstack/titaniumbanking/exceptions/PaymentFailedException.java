package com.smoothstack.titaniumbanking.exceptions;

public class PaymentFailedException extends BadRequestException {
    public PaymentFailedException() { super("Payment failed."); }
}