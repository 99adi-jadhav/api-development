package HospitalManagementSystem.HMSWithMyBatis.Controller;

import HospitalManagementSystem.HMSWithMyBatis.Entities.PatientEntities;
import HospitalManagementSystem.HMSWithMyBatis.Service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

public class PatientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    public void testCreatePatient() throws Exception {
        PatientEntities newPatient = new PatientEntities();
        newPatient.setPatient_name("Aditya");
        newPatient.setPatient_gender("Male");
        newPatient.setPatient_address("Pune,Maharastra");

        doNothing().when(patientService).addPatient(any(PatientEntities.class));

        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"patient_name\":\"Aditya\",\"patient_gender\":\"Male\",\"patient_address\":\"Pune,Maharastra\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.patient_name").value("Aditya"))
                .andExpect(jsonPath("$.patient_gender").value("Male"))
                .andExpect(jsonPath("$.patient_address").value("Pune,Maharastra"));
    }

    @Test
    public void testGetAllPatients() throws Exception {
        PatientEntities patient1 = new PatientEntities();
        patient1.setPatient_name("Aditya");
        patient1.setPatient_gender("Male");
        patient1.setPatient_address("Pune,Maharastra");

        PatientEntities patient2 = new PatientEntities();
        patient2.setPatient_name("Manoj");
        patient2.setPatient_gender("Male");
        patient2.setPatient_address("AhilyaNagar");

        when(patientService.getAllPatients()).thenReturn(Arrays.asList(patient1, patient2));

        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].patient_name").value("Aditya"))
                .andExpect(jsonPath("$[1].patient_name").value("Manoj"));
    }

    @Test
    public void testGetPatientById() throws Exception {
        PatientEntities patient = new PatientEntities();
        patient.setPatient_name("Aditya");
        patient.setPatient_gender("Male");
        patient.setPatient_address("Pune");

        when(patientService.getPatientById(1L)).thenReturn(Optional.of(patient));

        mockMvc.perform(get("/patients/{patient_id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patient_name").value("Aditya"))
                .andExpect(jsonPath("$.patient_gender").value("Male"))
                .andExpect(jsonPath("$.patient_address").value("Pune"));
    }

   

    @Test
    public void testUpdatePatient() throws Exception {
        // Create a new patient object with a valid address
        PatientEntities patient = new PatientEntities();
        patient.setPatient_id(1L);
        patient.setPatient_name("Aditya");
        patient.setPatient_gender("Male");
        patient.setPatient_address("Pune, Maharashtra, India"); // Address is now valid (>= 5 characters)

        // Mock the getPatientById to return the patient
        Mockito.when(patientService.getPatientById(1L)).thenReturn(Optional.of(patient));

        // Mock the updatePatient method to do nothing
        doNothing().when(patientService).updatePatient(ArgumentMatchers.any(PatientEntities.class));

        // Perform the PUT request and verify the response
        mockMvc.perform(put("/patients/{patient_id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"patient_id\":1,\"patient_name\":\"Aditya\",\"patient_gender\":\"Male\",\"patient_address\":\"Pune, Maharashtra, India\"}"))
                .andExpect(status().isOk())  // Verify HTTP status is 200 OK
                .andExpect(jsonPath("$.patient_name").value("Aditya"));  // Verify the patient_name field in the response
    }

    

    @Test
    public void testDeletePatient() throws Exception {
        PatientEntities patient = new PatientEntities();
        patient.setPatient_id(1L);
        patient.setPatient_name("Aditya");

        when(patientService.getPatientById(1L)).thenReturn(Optional.of(patient));

        mockMvc.perform(delete("/patients/{patient_id}", 1L))
                .andExpect(status().isNoContent());

        verify(patientService, times(1)).deletePatient(1L);
    }

}
