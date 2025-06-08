package HospitalManagementSystem.HMSWithMyBatis.Service;

import HospitalManagementSystem.HMSWithMyBatis.Entities.PatientEntities;
import HospitalManagementSystem.HMSWithMyBatis.Mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class PatientService {

    @Autowired
    private PatientMapper patientMapper;

    // Save a new patient
    public void addPatient(PatientEntities patientEntity) {
        patientMapper.insertPatient(patientEntity);
    }

    // Get patient by ID
    public Optional<PatientEntities> getPatientById(long patient_id) {
    	PatientEntities patientEntity = patientMapper.getPatientById(patient_id);
        return Optional.ofNullable(patientEntity);
    }

    // Get all patients
    public List<PatientEntities> getAllPatients() {
        return patientMapper.getAllPatients();
    }

    // Update an existing patient
    public void updatePatient(PatientEntities patientEntity) {
        patientMapper.updatePatient(patientEntity);
    }

    // Delete a patient
    public void deletePatient(long patient_id) {
        patientMapper.deletePatient(patient_id);
    }
}