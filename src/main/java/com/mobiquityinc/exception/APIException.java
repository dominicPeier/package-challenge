package com.mobiquityinc.exception;

public class APIException extends RuntimeException {
    public APIException(String message) {
        super(message);
    }

    public APIException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
