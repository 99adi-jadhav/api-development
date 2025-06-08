package HospitalManagementSystem.HMSWithMyBatis.Controller;

import HospitalManagementSystem.HMSWithMyBatis.Entities.PatientEntities;
import HospitalManagementSystem.HMSWithMyBatis.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    // Create a new patient
    @PostMapping
    public ResponseEntity<PatientEntities> createPatient(@RequestBody @Validated PatientEntities patientEntity) {
        logger.info("Request to create new patient: {}", patientEntity.getPatient_name());
        patientService.addPatient(patientEntity);
        return new ResponseEntity<>(patientEntity, HttpStatus.CREATED);
    }

    // Get all patients
    @GetMapping
    public ResponseEntity<List<PatientEntities>> getAllPatients() {
        logger.info("Request to get all patients");
        List<PatientEntities> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Get a patient by ID
    @GetMapping("/{patient_id}")
    public ResponseEntity<PatientEntities> getPatientById(@PathVariable long patient_id) {
        logger.info("Request to get patient by ID: {}", patient_id);
        Optional<PatientEntities> patient = patientService.getPatientById(patient_id);
        return patient.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update an existing patient
    @PutMapping("/{patient_id}")
    public ResponseEntity<PatientEntities> updatePatient(@PathVariable long patient_id, @RequestBody @Validated PatientEntities patientEntity) {
        logger.info("Request to update patient with ID: {}", patient_id);
        if (patient_id != patientEntity.getPatient_id()) {
            logger.error("Patient ID mismatch: {} and {}", patient_id, patientEntity.getPatient_id());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<PatientEntities> existingPatient = patientService.getPatientById(patient_id);
        if (existingPatient.isPresent()) {
            patientService.updatePatient(patientEntity);
            return new ResponseEntity<>(patientEntity, HttpStatus.OK);
        } else {
            logger.warn("Patient with ID {} not found for update", patient_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a patient
    @DeleteMapping("/{patient_id}")
    public ResponseEntity<Void> deletePatient(@PathVariable long patient_id) {
        logger.info("Request to delete patient with ID: {}", patient_id);
        Optional<PatientEntities> existingPatient = patientService.getPatientById(patient_id);
        if (existingPatient.isPresent()) {
            patientService.deletePatient(patient_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.warn("Patient with ID {} not found for deletion", patient_id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
