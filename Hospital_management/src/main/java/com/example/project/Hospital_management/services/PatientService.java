package com.example.project.Hospital_management.services;

import com.example.project.Hospital_management.Entities.PatientEntity;
import com.example.project.Hospital_management.repositories.PatientRepository;
import com.example.project.Hospital_management.exception.PatientNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public PatientEntity savePatient(PatientEntity patientEntity) {
        return patientRepository.save(patientEntity);
    }

    public List<PatientEntity> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<PatientEntity> getPatientById(Long patientId) {
        return patientRepository.findById(patientId);
    }

 // New method to get patient by Name
//    public Optional<PatientEntity> getPatientByName(String name) {
//        return patientRepository.findByName(name);
//    }
    
    public PatientEntity updatePatient(PatientEntity patientEntity) {
        Optional<PatientEntity> existingPatientOptional = patientRepository.findById(patientEntity.getPatient_id());

        if (existingPatientOptional.isPresent()) {
            PatientEntity existingPatient = existingPatientOptional.get();
            existingPatient.setPatient_address(patientEntity.getPatient_address());
            existingPatient.setPatient_gender(patientEntity.getPatient_gender());
            existingPatient.setPatient_name(patientEntity.getPatient_name());

            // Save the updated patient to persist changes
            return patientRepository.save(existingPatient);
        } else {
            throw new PatientNotFoundException("Patient with ID: " + patientEntity.getPatient_id() + " not found");
        }
    }

    public void deletePatientById(Long patientId) {
        patientRepository.deleteById(patientId);
    }
}
