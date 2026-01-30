package com.sorokin.account_processing.exceptions;

public class AccountAlreadyExistException extends RuntimeException {
    public AccountAlreadyExistException(String message) {
        super(message);
    }
}
