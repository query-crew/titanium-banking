package com.smoothstack.titaniumbanking.exceptions;

public class MemberNotAuthorizedException extends BadRequestException {
    public MemberNotAuthorizedException() { super("Members are not authorized to perform this action."); }
}
