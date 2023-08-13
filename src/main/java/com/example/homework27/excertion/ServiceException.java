package com.example.homework27.excertion;

public class ServiceException extends RuntimeException{
    private String message;

    public ServiceException() {
    }

    public ServiceException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
