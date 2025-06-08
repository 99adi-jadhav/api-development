package com.example.project.Hospital_management.exception;

public class InvalidPatientDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public InvalidPatientDataException(String message) {
        super(message);
    }

    public InvalidPatientDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
