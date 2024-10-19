package com.example.authserver.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlingControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidityViolation>> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ValidityViolation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> onEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ActionForbiddenException.class)
    public ResponseEntity<String> onActionForbiddenException(ActionForbiddenException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
