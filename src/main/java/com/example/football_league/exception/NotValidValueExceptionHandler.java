package com.example.football_league.exception;

public class NotValidValueExceptionHandler extends RuntimeException {
    public NotValidValueExceptionHandler(String message) {
        super(message);
    }
}
