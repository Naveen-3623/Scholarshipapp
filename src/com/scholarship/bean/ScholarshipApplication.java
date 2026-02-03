package com.scholarship.bean;

import java.util.Date;

public class ScholarshipApplication {

    private int applicationID;
    private String studentID;
    private String schemeCode;
    private Date applicationDate;
    private double gpaAtApplication;
    private String status;
    private double awardAmount;
    private String remarks;

    public ScholarshipApplication() {}

    public ScholarshipApplication(int applicationID, String studentID, String schemeCode,
                                  Date applicationDate, double gpaAtApplication,
                                  String status, double awardAmount, String remarks) {
        this.applicationID = applicationID;
        this.studentID = studentID;
        this.schemeCode = schemeCode;
        this.applicationDate = applicationDate;
        this.gpaAtApplication = gpaAtApplication;
        this.status = status;
        this.awardAmount = awardAmount;
        this.remarks = remarks;
    }

    // Getters & Setters
    public int getApplicationID() { return applicationID; }
    public void setApplicationID(int applicationID) { this.applicationID = applicationID; }

    public String getStudentID() { return studentID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }

    public String getSchemeCode() { return schemeCode; }
    public void setSchemeCode(String schemeCode) { this.schemeCode = schemeCode; }

    public Date getApplicationDate() { return applicationDate; }
    public void setApplicationDate(Date applicationDate) { this.applicationDate = applicationDate; }

    public double getGpaAtApplication() { return gpaAtApplication; }
    public void setGpaAtApplication(double gpaAtApplication) { this.gpaAtApplication = gpaAtApplication; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getAwardAmount() { return awardAmount; }
    public void setAwardAmount(double awardAmount) { this.awardAmount = awardAmount; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
