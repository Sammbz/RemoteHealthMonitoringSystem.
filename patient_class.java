/**
 * Patient class represents a patient with chronic conditions
 * being monitored by the RHMS
 */
public class Patient {
    private String patientId;
    private String name;
    private int age;
    private String chronicCondition; // e.g., "Hypertension", "Diabetes"
    private String contactNumber;
    
    // Constructor
    public Patient(String patientId, String name, int age, 
                   String chronicCondition, String contactNumber) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.chronicCondition = chronicCondition;
        this.contactNumber = contactNumber;
    }
    
    // Getters
    public String getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getChronicCondition() { return chronicCondition; }
    public String getContactNumber() { return contactNumber; }
    
    // Setters
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    @Override
    public String toString() {
        return String.format("Patient[ID=%s, Name=%s, Age=%d, Condition=%s]",
                           patientId, name, age, chronicCondition);
    }
}