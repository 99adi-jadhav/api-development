package com.example.project.Hospital_management.services;

import com.example.project.Hospital_management.Entities.PatientEntity;
import com.example.project.Hospital_management.repositories.PatientRepository;
import com.example.project.Hospital_management.exception.InvalidPatientDataException;
import com.example.project.Hospital_management.exception.PatientNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    // Get all patients
    public List<PatientEntity> getAllPatients() {
        logger.info("Fetching all patients from the database.");
        return patientRepository.findAll();
    }

    // Get a patient by ID
    public PatientEntity getPatientById(long patientId) {
        logger.info("Fetching patient with ID: {}", patientId);
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + patientId));
    }

    // Save or update a patient
    public PatientEntity savePatient(PatientEntity patientEntity) {
        logger.info("Saving patient: {}", patientEntity);

        // Check for invalid data, for example, missing fields
        if (patientEntity.getPatientName() == null || patientEntity.getPatientGender() == null || patientEntity.getPatientAddress() == null) {
            throw new InvalidPatientDataException("Patient data is invalid. Name, Gender, or Address cannot be null.");
        }

        return patientRepository.save(patientEntity);
    }

    // Delete a patient by ID
    public void deletePatientById(long patientId) {
        logger.info("Attempting to delete patient with ID: {}", patientId);
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException("Patient not found with ID: " + patientId);
        }
        patientRepository.deleteById(patientId);
        logger.debug("Successfully deleted patient with ID: {}", patientId);
    }

    // Update a patient
    public PatientEntity updatePatient(PatientEntity patientEntity) {
        logger.info("Updating patient with ID: {}", patientEntity.getPatientId());

        PatientEntity existingPatient = patientRepository.findById(patientEntity.getPatientId())
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + patientEntity.getPatientId()));

        // Update fields
        existingPatient.setPatientName(patientEntity.getPatientName());
        existingPatient.setPatientGender(patientEntity.getPatientGender());
        existingPatient.setPatientAddress(patientEntity.getPatientAddress());

        return patientRepository.save(existingPatient);
    }
}
