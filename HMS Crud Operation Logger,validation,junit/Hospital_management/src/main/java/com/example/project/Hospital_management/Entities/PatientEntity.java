package com.example.project.Hospital_management.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "patient")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long patientId; // Unique ID for the patient

    @NotEmpty(message = "Patient name cannot be empty")
    @Size(min = 2, max = 50, message = "Patient name should have between 2 and 50 characters")
    @Column(name = "patient_name")
    private String patientName; // Patient's name

    @NotEmpty(message = "Patient gender cannot be empty")
    @Column(name = "patient_gender")
    private String patientGender; // Patient's gender (e.g., Male, Female)

    @NotEmpty(message = "Patient address cannot be empty")
    @Column(name = "patient_address")
    private String patientAddress; // Patient's address

    // Default Constructor
    public PatientEntity() {}

    // Constructor with all attributes
    public PatientEntity(String patientName, String patientGender, String patientAddress) {
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientAddress = patientAddress;
    }

    // Getters and Setters
    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    @Override
    public String toString() {
        return "PatientEntity{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", patientGender='" + patientGender + '\'' +
                ", patientAddress='" + patientAddress + '\'' +
                '}';
    }
}
