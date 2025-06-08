package com.example.project.Hospital_management;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.project.Hospital_management.Entities.PatientEntity;
import com.example.project.Hospital_management.controller.PatientController;
import com.example.project.Hospital_management.services.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(PatientController.class) // Specify the controller being tested
public class PatientControllerTest {

    @MockBean // Correctly mock Spring bean
    private PatientService patientService; // Mock service layer

    @Autowired
    private MockMvc mockMvc; // MockMvc to perform HTTP requests

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper to handle JSON serialization

    @Test
    void shouldCreateNewPatient() throws Exception {
        // Arrange
        PatientEntity newPatient = new PatientEntity("Aditya", "male", "Pune");

        // Mock the service call
        when(patientService.savePatient(ArgumentMatchers.<PatientEntity>any())).thenReturn(newPatient);

        // Act & Assert
        this.mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPatient)))
                .andExpect(status().isCreated()) // Check HTTP 201
                .andExpect(jsonPath("$.patientName", is(newPatient.getPatientName())))
                .andExpect(jsonPath("$.patientGender", is(newPatient.getPatientGender())))
                .andExpect(jsonPath("$.patientAddress", is(newPatient.getPatientAddress())));

        // Verify the service layer method was called once
        verify(patientService, times(1)).savePatient(ArgumentMatchers.<PatientEntity>any());
    }
    
    @Test
    void shouldReturnAllPatients() throws Exception {
        PatientEntity patient1 = new PatientEntity("Aditya", "male", "Pune");
        patient1.setPatientId(1);
        
        PatientEntity patient2 = new PatientEntity("Manoj", "male", "Mumbai");
        patient2.setPatientId(2);
        
        List<PatientEntity> patients = Arrays.asList(patient1, patient2);
        
        when(patientService.getAllPatients()).thenReturn(patients);
        
        this.mockMvc.perform(get("/patients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // HTTP 200
                .andExpect(jsonPath("$.size()", is(patients.size()))) // Validate size of the list
                .andExpect(jsonPath("$[0].patientName", is(patient1.getPatientName())))
                .andExpect(jsonPath("$[0].patientGender", is(patient1.getPatientGender())))
                .andExpect(jsonPath("$[1].patientName", is(patient2.getPatientName())))
                .andExpect(jsonPath("$[1].patientGender", is(patient2.getPatientGender())));
        
        verify(patientService, times(1)).getAllPatients();
    }
    
    @Test
    void shouldReturnOnePatientById() throws Exception {
        // Arrange
        PatientEntity patient1 = new PatientEntity("Manoj", "male", "Mumbai");
        patient1.setPatientId(2);
        
        // Mock the service layer to return the patient directly
        when(patientService.getPatientById(anyLong())).thenReturn(patient1);

        // Act & Assert: Perform GET request and verify the response
        this.mockMvc.perform(get("/patients/{id}", 2))  // Use the correct endpoint for patient retrieval by ID
                .andExpect(status().isOk())  // Expect HTTP 200 OK
                .andExpect(jsonPath("$.patientName", is(patient1.getPatientName())))
                .andExpect(jsonPath("$.patientGender", is(patient1.getPatientGender())))
                .andExpect(jsonPath("$.patientAddress", is(patient1.getPatientAddress())));
    }
    
    @Test
    void shouldDeletePatient() throws Exception {
        // Arrange: Mock the deletion logic
        doNothing().when(patientService).deletePatientById(anyLong()); // Use doNothing() for void methods

        // Act & Assert: Perform the DELETE request
        this.mockMvc.perform(delete("/patients/{id}", 1))
                .andExpect(status().isNoContent()); // Expect HTTP 204 No Content

        // Verify that the service method was called once
        verify(patientService, times(1)).deletePatientById(anyLong());
    }

    @Test
    void shouldUpdatePatient() throws Exception {
        // Arrange: Create and initialize a patient object to simulate the update
        PatientEntity updatedPatient = new PatientEntity("Aditya", "male", "Pune");
        updatedPatient.setPatientId(1); // Set the ID for the updated patient

        // Mock the service layer method to return the updated patient
        when(patientService.updatePatient(ArgumentMatchers.any(PatientEntity.class))).thenReturn(updatedPatient);

        // Act & Assert: Perform PUT request and verify the response
        this.mockMvc.perform(put("/patients/{id}", 1) // Endpoint for updating a patient
                .contentType(MediaType.APPLICATION_JSON) // JSON content type
                .content(objectMapper.writeValueAsString(updatedPatient))) // Convert the object to JSON
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(jsonPath("$.patientName", is(updatedPatient.getPatientName()))) // Verify the name
                .andExpect(jsonPath("$.patientGender", is(updatedPatient.getPatientGender()))) // Verify the gender
                .andExpect(jsonPath("$.patientAddress", is(updatedPatient.getPatientAddress()))); // Verify the address

        // Verify that the service method was called once with correct arguments
        verify(patientService, times(1)).updatePatient(ArgumentMatchers.any(PatientEntity.class));
    }
}
