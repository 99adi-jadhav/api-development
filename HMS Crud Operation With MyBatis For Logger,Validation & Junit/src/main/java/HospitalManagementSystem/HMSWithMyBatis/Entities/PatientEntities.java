package HospitalManagementSystem.HMSWithMyBatis.Entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PatientEntities {

    private long patient_id;

    @NotBlank(message = "Patient name cannot be blank")
    @Size(min = 2, max = 100, message = "Patient name should be between 2 and 100 characters")
    private String patient_name;

    @NotBlank(message = "Patient gender cannot be blank")
    private String patient_gender;

    @NotBlank(message = "Patient address cannot be blank")
    @Size(min = 5, max = 255, message = "Patient address should be between 5 and 255 characters")
    private String patient_address;

    // Getters and Setters

    public long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(long patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_gender() {
        return patient_gender;
    }

    public void setPatient_gender(String patient_gender) {
        this.patient_gender = patient_gender;
    }

    public String getPatient_address() {
        return patient_address;
    }

    public void setPatient_address(String patient_address) {
        this.patient_address = patient_address;
    }
}
