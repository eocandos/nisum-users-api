package com.nisum.users.api.exception;

public class CustomException extends RuntimeException {

    private final static long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message);
    }

}
