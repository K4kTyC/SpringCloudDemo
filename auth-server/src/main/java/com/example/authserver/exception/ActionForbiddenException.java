package com.example.authserver.exception;

public class ActionForbiddenException extends RuntimeException {

    public ActionForbiddenException() {
      super();
    }

    public ActionForbiddenException(String message) {
        super(message);
    }
}
