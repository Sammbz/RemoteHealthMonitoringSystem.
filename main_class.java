/**
 * Main class - Entry point for the Remote Health Monitoring System
 * Demonstrates system functionality and object interactions
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  Remote Health Monitoring System - Demo Simulation    ║");
        System.out.println("║  Near East University Hospital                         ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
        
        // Initialize the monitoring system
        MonitoringSystem rhms = new MonitoringSystem("RHMS-NEU-2025");
        
        // Register patients
        System.out.println("--- PATIENT REGISTRATION ---");
        Patient patient1 = new Patient("P001", "Ali Yılmaz", 58, 
                                       "Hypertension", "+90-555-1234");
        Patient patient2 = new Patient("P002", "Ayşe Demir", 45, 
                                       "Diabetes", "+90-555-5678");
        Patient patient3 = new Patient("P003", "Mehmet Kaya", 62, 
                                       "Hypertension", "+90-555-9012");
        
        rhms.registerPatient(patient1);
        rhms.registerPatient(patient2);
        rhms.registerPatient(patient3);
        
        // Register doctors
        System.out.println("\n--- DOCTOR REGISTRATION ---");
        Doctor doctor1 = new Doctor("D001", "Dr. Elif Öztürk", 
                                   "Cardiology", "+90-555-1111");
        Doctor doctor2 = new Doctor("D002", "Dr. Can Arslan", 
                                   "Endocrinology", "+90-555-2222");
        
        rhms.registerDoctor(doctor1);
        rhms.registerDoctor(doctor2);
        
        // Setup wearable devices
        System.out.println("\n--- WEARABLE DEVICE SETUP ---");
        WearableDevice device1 = new WearableDevice("WD001", "P001", "Smartwatch");
        WearableDevice device2 = new WearableDevice("WD002", "P002", "Fitness Band");
        WearableDevice device3 = new WearableDevice("WD003", "P003", "Smartwatch");
        
        System.out.println("Device setup complete for all patients.\n");
        
        // Simulate monitoring cycle
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║          MONITORING CYCLE - Scenario 1                 ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        simulateMonitoringCycle(rhms, device1, patient1, doctor1);
        
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║          MONITORING CYCLE - Scenario 2                 ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        simulateMonitoringCycle(rhms, device2, patient2, doctor2);
        
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║          MONITORING CYCLE - Scenario 3                 ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        simulateMonitoringCycle(rhms, device3, patient3, doctor1);
        
        // Display final system status
        rhms.displaySystemStatus();
        
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║              Simulation Complete                       ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Simulates one complete monitoring cycle:
     * 1. Wearable records vital signs
     * 2. Data transmitted to system
     * 3. System analyzes and generates alert if needed
     * 4. Doctor reviews alert and provides recommendation
     */
    private static void simulateMonitoringCycle(MonitoringSystem system,
                                               WearableDevice device,
                                               Patient patient,
                                               Doctor doctor) {
        System.out.println("\n--- Monitoring Patient: " + patient.getName() + " ---");
        
        // Step 1: Record vital signs from wearable
        System.out.println("\n1. Recording vital signs from " + device.getDeviceType() + "...");
        VitalSigns vitalSigns = device.recordVitalSigns();
        
        if (vitalSigns != null) {
            System.out.println("   Recorded: " + vitalSigns);
            
            // Step 2: Transmit data to system
            System.out.println("\n2. Transmitting data to monitoring system...");
            device.transmitData(system, vitalSigns);
            
            // Step 3: Check if alert was generated
            System.out.println("\n3. System analyzing data...");
            
            // Step 4: If alert exists, doctor reviews
            if (vitalSigns.isAbnormal()) {
                // Get the most recent alert for this patient
                Alert recentAlert = findRecentAlert(system, patient.getPatientId());
                
                if (recentAlert != null) {
                    System.out.println("\n4. Doctor reviewing alert...");
                    String recommendation = doctor.reviewAlert(recentAlert, patient);
                    System.out.println("\n" + recommendation);
                    
                    // Schedule follow-up if needed
                    if (recentAlert.getSeverity().equals("HIGH") || 
                        recentAlert.getSeverity().equals("MEDIUM")) {
                        doctor.scheduleFollowUp(patient, "2025-12-01 10:00");
                    }
                }
            } else {
                System.out.println("4. No doctor review needed - vitals normal.");
            }
        }
        
        System.out.println("\n--- Cycle Complete ---");
    }
    
    /**
     * Helper method to find most recent unresolved alert for a patient
     */
    private static Alert findRecentAlert(MonitoringSystem system, String patientId) {
        for (int i = system.getAlerts().size() - 1; i >= 0; i--) {
            Alert alert = system.getAlerts().get(i);
            if (alert.getPatientId().equals(patientId) && !alert.isResolved()) {
                return alert;
            }
        }
        return null;
    }
}