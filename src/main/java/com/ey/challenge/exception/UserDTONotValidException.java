package com.ey.challenge.exception;

public class UserDTONotValidException extends RuntimeException {
    public UserDTONotValidException(String message) {
        super(message);
    }
}
