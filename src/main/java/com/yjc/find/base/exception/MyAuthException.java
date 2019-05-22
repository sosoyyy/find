package com.yjc.find.base.exception;

public class AuthException extends RuntimeException {
    public AuthException(String errorMsg) {
        super(errorMsg);
    }
}
