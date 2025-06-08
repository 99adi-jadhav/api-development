package HospitalManagementSystem.HMSWithMyBatis.Exception;

public class StudentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	// Constructor that accepts a custom message
    public StudentNotFoundException(String message) {
        super(message);
    }
}
