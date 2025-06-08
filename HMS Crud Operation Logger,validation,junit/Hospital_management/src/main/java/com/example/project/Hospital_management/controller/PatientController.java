package com.example.project.Hospital_management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.project.Hospital_management.Entities.PatientEntity;
import com.example.project.Hospital_management.services.PatientService;

@RestController
@RequestMapping("/patients")
@Validated
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    // GET all patients
    @GetMapping
    public List<PatientEntity> getAllPatients() {
        logger.info("Received request to fetch all patients");
        return patientService.getAllPatients();
    }

    // GET patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<PatientEntity> getPatientById(@PathVariable Long id) {
        logger.info("Received request to fetch patient with ID: {}", id);
        PatientEntity patient = patientService.getPatientById(id); // Fetch the patient
        return ResponseEntity.ok(patient); // Return with status 200 (OK)
    }

    @PostMapping
    public ResponseEntity<PatientEntity> createPatient(@Validated @RequestBody PatientEntity patientEntity) {
        logger.info("Received request to create a new patient: {}", patientEntity);
        
        // Call the service layer to save the patient entity
        PatientEntity savedPatient = patientService.savePatient(patientEntity);
        
        // If the patient is saved successfully, return a 201 Created response
        if (savedPatient != null) {
            // This line sets the response status to 201 (Created) and returns the saved patient
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
        } else {
            // If there's an error saving the patient, return a 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    
    
 // PUT update patient
    @PutMapping("/{id}")
    public ResponseEntity<PatientEntity> updatePatient(@Validated @RequestBody PatientEntity patientEntity,@PathVariable Long id) {
        logger.info("Received request to update a new patient: {}", id);
        PatientEntity updatePatient = patientService.updatePatient(patientEntity);
        return ResponseEntity.ok(updatePatient); // Return with status 201 (Created)
    }

    // DELETE patient by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable Long id) {
        logger.info("Received request to delete patient with ID: {}", id);
        patientService.deletePatientById(id); // Delete the patient
        return ResponseEntity.noContent().build(); // Return 204 (No Content) after deletion
    }
}
