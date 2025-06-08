package HospitalManagementSystem.HMSWithMyBatis.Entities;

public class PatientEntities {
	 private long patient_id;
	    private String patient_name;
	    private String patient_gender;
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
