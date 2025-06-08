package com.example.project.Hospital_management.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle PatientNotFoundException
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Object> handlePatientNotFoundException(
            PatientNotFoundException ex, WebRequest request) {
        logger.error("Error: {}", ex.getMessage()); // Log the error message
        ErrorDetailsPatient errorDetails = new ErrorDetailsPatient(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); // Return 404 Not Found
    }

    // Handle InvalidPatientDataException (or any custom exception)
    @ExceptionHandler(InvalidPatientDataException.class)
    public ResponseEntity<Object> handleInvalidPatientDataException(
            InvalidPatientDataException ex, WebRequest request) {
        logger.error("Error: {}", ex.getMessage()); // Log the error message
        ErrorDetailsPatient errorDetails = new ErrorDetailsPatient(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST); // Return 400 Bad Request
    }

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        logger.error("Unexpected Error: {}", ex.getMessage()); // Log the error message
        ErrorDetailsPatient errorDetails = new ErrorDetailsPatient(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 Internal Server Error
    }
}
