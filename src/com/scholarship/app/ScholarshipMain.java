package com.scholarship.app;
import java.sql.Date;
import com.scholarship.service.ScholarshipService;

public class ScholarshipMain {
    public static void main(String[] args) {
        ScholarshipService service = new ScholarshipService();

        System.out.println("--- Scholarship Application Evaluation Console ---");
        try {
            System.out.println(
                service.submitApplication("STU1001", "MERIT2025",
                        new Date(System.currentTimeMillis()))
                ? "APPLICATION SUBMITTED" : "SUBMISSION FAILED");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(
                service.evaluateApplication(710009, true, 30000,
                        "Merit based", "MERIT2025", 50)
                ? "APPLICATION APPROVED" : "APPROVAL FAILED");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(
                service.revokeAward(710001, "Violation")
                ? "AWARD REVOKED" : "REVOCATION FAILED");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
