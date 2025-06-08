package com.example.project.Hospital_management.exception;

public class ErrorDetailsPatient {

    private int status;
    private String message;
    private String details;

    public ErrorDetailsPatient(int status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

    // Getters and Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
