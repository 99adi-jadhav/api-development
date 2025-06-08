package com.example.project.Hospital_management.Entities;

import jakarta.persistence.Column; // This Classes are import from jakarta persistence library 
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //annotation mark
@Table(name ="Patient") // this anotation specifies the data table 
public class PatientEntity {

    // Patient attributes
	@Id
	@GeneratedValue(strategy = GenerationType .IDENTITY )  
	private long patient_id; // Unique id for the patient
	 @Column(name = "patient_name")
    private String patient_name; // Patient name
	 @Column(name = "patient_gender")
    private String patient_gender; // Patient gender
	 @Column(name = "patient_address")
    private String patient_address; // Patient address

    // Constructors

    public PatientEntity() {} // Empty constructor

    // Constructor with all attributes
    public PatientEntity(long patient_id, String patient_name, String patient_gender, String patient_address) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.patient_gender = patient_gender;
        this.patient_address = patient_address;
    }

    // Getters and Setters for Patient attributes
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

    @Override
    public String toString() {
        return "PatientEntity [" +
                "patient_id=" + patient_id +
                ", patient_name='" + patient_name + '\'' +
                ", patient_gender='" + patient_gender + '\'' +
                ", patient_address='" + patient_address + '\'' +
                ']';
    }
}