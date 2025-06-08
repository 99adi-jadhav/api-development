package HospitalManagementSystem.HMSWithMyBatis.Mapper;

import HospitalManagementSystem.HMSWithMyBatis.Entities.PatientEntities;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PatientMapper {

	// Insert a new patient
    @Insert("INSERT INTO patients (patient_name, patient_gender, patient_address) VALUES (#{patient_name}, #{patient_gender}, #{patient_address})")
    void insertPatient(PatientEntities patient);

    // Get a patient by ID
    @Select("SELECT * FROM patients WHERE patient_id = #{patient_id}")
    PatientEntities getPatientById(long patient_id);

    // Get all patients
    @Select("SELECT * FROM patients")
    List<PatientEntities> getAllPatients();

    // Update an existing patient
    @Update("UPDATE patients SET patient_name = #{patient_name}, patient_gender = #{patient_gender}, patient_address = #{patient_address} WHERE patient_id = #{patient_id}")
    void updatePatient(PatientEntities patient);

    // Delete a patient by ID
    @Delete("DELETE FROM patients WHERE patient_id = #{patient_id}")
    void deletePatient(long patient_id);
}
