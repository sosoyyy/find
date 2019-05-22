package com.yjc.find.base.exception;

public class MyAuthException extends RuntimeException {
    public MyAuthException(String errorMsg) {
        super(errorMsg);
    }
}
