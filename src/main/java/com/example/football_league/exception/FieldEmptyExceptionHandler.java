package com.example.football_league.exception;

public class FieldEmptyExceptionHandler extends RuntimeException {
    public FieldEmptyExceptionHandler(String message) {
        super(message);
    }
}
