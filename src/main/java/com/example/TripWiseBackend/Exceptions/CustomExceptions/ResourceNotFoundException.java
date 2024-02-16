package com.example.TripWiseBackend.Exceptions.CustomExceptions;

public class ResourceNotFoundException extends Exception {

    private String resourceName;
    private String message;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String resourceName, String message) {
        this.resourceName = resourceName;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.resourceName + " " + message;
    }
}
