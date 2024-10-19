package com.example.authserver.exception;

public record ValidityViolation(String field, String message) {
}
