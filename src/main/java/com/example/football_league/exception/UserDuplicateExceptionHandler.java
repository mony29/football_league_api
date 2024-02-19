package com.example.football_league.exception;

public class UserDuplicateExceptionHandler extends RuntimeException {
    public UserDuplicateExceptionHandler(String message) {
        super(message);
    }
}