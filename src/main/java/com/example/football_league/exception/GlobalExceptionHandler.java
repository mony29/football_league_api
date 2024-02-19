package com.example.football_league.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundExceptionHandler.class)
    ProblemDetail handleNotFoundException(NotFoundExceptionHandler e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Not Found Exception");
        problemDetail.setProperty("timestamp ", Instant.now());
        problemDetail.setType(URI.create("localhost:8080/errors/not-found"));
        return problemDetail;
    }
}
