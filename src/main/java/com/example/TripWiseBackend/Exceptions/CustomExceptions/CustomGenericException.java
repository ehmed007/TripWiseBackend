package com.example.TripWiseBackend.Exceptions.CustomExceptions;

public class CustomGenericException extends Exception {
    private String resourceName;
    private String message;

    public CustomGenericException() {
        super();
    }

    public CustomGenericException(String resourceName, String message) {
        this.resourceName = resourceName;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.resourceName + " " + message;
    }
}
