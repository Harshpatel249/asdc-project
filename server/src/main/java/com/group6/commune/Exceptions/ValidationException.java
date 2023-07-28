package com.group6.commune.Exceptions;

import java.util.Map;

public class ValidationException extends ApiException {
    private Map<String, String> errors;

    public ValidationException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}

