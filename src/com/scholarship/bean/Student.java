package com.scholarship.bean;

public class Student {

    private String studentID;
    private String fullName;
    private String department;
    private int year;
    private double gpa;
    private double familyIncome;

    public Student() {}

    public Student(String studentID, String fullName, String department,
                   int year, double gpa, double familyIncome) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.department = department;
        this.year = year;
        this.gpa = gpa;
        this.familyIncome = familyIncome;
    }

    public String getStudentID() { return studentID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    public double getFamilyIncome() { return familyIncome; }
    public void setFamilyIncome(double familyIncome) { this.familyIncome = familyIncome; }
}
