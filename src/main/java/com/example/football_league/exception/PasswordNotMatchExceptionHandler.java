package com.example.football_league.exception;

public class PasswordNotMatchExceptionHandler extends RuntimeException {
    public PasswordNotMatchExceptionHandler(String message) {
        super(message);
    }
}
