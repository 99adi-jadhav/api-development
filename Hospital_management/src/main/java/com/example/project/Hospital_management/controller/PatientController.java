package com.example.project.Hospital_management.controller;

import com.example.project.Hospital_management.Entities.PatientEntity;
import com.example.project.Hospital_management.services.PatientService;
import com.fasterxml.jackson.core.sym.Name;
import com.example.project.Hospital_management.exception.PatientNotFoundException;

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

    @PostMapping
    public ResponseEntity<PatientEntity> createPatient(@RequestBody PatientEntity patientEntity) {
        PatientEntity createdPatient = patientService.savePatient(patientEntity);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PatientEntity>> getAllPatients() {
        List<PatientEntity> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientEntity> getPatientById(@PathVariable Long id) {
        Optional<PatientEntity> patient = patientService.getPatientById(id);
        return patient.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
   
//    @GetMapping("/name/{name}")
//    public ResponseEntity<PatientEntity> getPatientByName(@PathVariable String name) {
//        Optional<PatientEntity> patient = patientService.getPatientByName(name);
//        return patient.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    
    @PutMapping("/{id}")
    public ResponseEntity<PatientEntity> updatePatient(@PathVariable Long id, @RequestBody PatientEntity patientEntity) {
        if (!id.equals(patientEntity.getPatient_id())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            PatientEntity updatedPatient = patientService.updatePatient(patientEntity);
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        Optional<PatientEntity> patient = patientService.getPatientById(id);
        if (patient.isPresent()) {
            patientService.deletePatientById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
