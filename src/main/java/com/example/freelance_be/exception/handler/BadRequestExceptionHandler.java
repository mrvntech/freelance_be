package com.example.freelance_be.exception.handler;

import com.example.freelance_be.exception.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class BadRequestExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handle(MethodArgumentNotValidException exception){
        return ResponseEntity.status(401).body(exception.getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> handle(BadRequestException exception){
        return ResponseEntity.status(401).body(exception.getMessage());
    }
}
