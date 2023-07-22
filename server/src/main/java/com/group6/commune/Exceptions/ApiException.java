package com.group6.commune.Exceptions;
public abstract class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
