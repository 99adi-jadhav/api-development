package com.example.project.Hospital_management.exception;

public class PatientNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public PatientNotFoundException(String message) {
        super(message);
    }

    public PatientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
