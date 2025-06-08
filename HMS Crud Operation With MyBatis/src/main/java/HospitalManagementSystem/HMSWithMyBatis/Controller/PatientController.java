package HospitalManagementSystem.HMSWithMyBatis.Controller;

import HospitalManagementSystem.HMSWithMyBatis.Entities.PatientEntities;
import HospitalManagementSystem.HMSWithMyBatis.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Create a new patient
    @PostMapping
    public ResponseEntity<PatientEntities> createPatient(@RequestBody PatientEntities patientEntity) {
        patientService.addPatient(patientEntity);
        return new ResponseEntity<>(patientEntity, HttpStatus.CREATED);
    }

    // Get all patients
    @GetMapping
    public ResponseEntity<List<PatientEntities>> getAllPatients() {
        List<PatientEntities> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Get a patient by ID
    @GetMapping("/{patient_id}")
    public ResponseEntity<PatientEntities> getPatientById(@PathVariable long patient_id) {
        Optional<PatientEntities> patient = patientService.getPatientById(patient_id);
        return patient.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update an existing patient
    @PutMapping("/{patient_id}")
    public ResponseEntity<PatientEntities> updatePatient(@PathVariable long patient_id, @RequestBody PatientEntities patientEntity) {
        if (patient_id != patientEntity.getPatient_id()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Return HTTP 400 if IDs don't match
        }

        Optional<PatientEntities> existingPatient = patientService.getPatientById(patient_id);
        if (existingPatient.isPresent()) {
            patientService.updatePatient(patientEntity);
            return new ResponseEntity<>(patientEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return HTTP 404 if patient not found
        }
    }

    // Delete a patient
    @DeleteMapping("/{patient_id}")
    public ResponseEntity<Void> deletePatient(@PathVariable long patient_id) {
        Optional<PatientEntities> existingPatient = patientService.getPatientById(patient_id);
        if (existingPatient.isPresent()) {
            patientService.deletePatient(patient_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // HTTP 204 for successful delete
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // HTTP 404 if patient not found
        }
    }
}