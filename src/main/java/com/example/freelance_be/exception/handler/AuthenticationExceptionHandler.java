package com.example.freelance_be.exception.handler;

import com.example.freelance_be.exception.exception.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthenticationExceptionHandler {
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<String> handle(AuthenticationException exception){
        return ResponseEntity.status(401).body(exception.getMessage());
    }
}
