package com.scholarship.dao;

import java.sql.*;
import com.scholarship.bean.Student;
import com.scholarship.util.DBUtil;

public class StudentDAO {

    public Student findStudent(String studentID) {

        String sql = "SELECT * FROM STUDENT_TBL WHERE STUDENT_ID=?";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, studentID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Student s = new Student();
                s.setStudentID(rs.getString("STUDENT_ID"));
                s.setFullName(rs.getString("FULL_NAME"));
                s.setDepartment(rs.getString("DEPARTMENT"));
                s.setYear(rs.getInt("YEAR"));
                s.setGpa(rs.getDouble("GPA"));
                s.setFamilyIncome(rs.getDouble("FAMILY_INCOME"));
                return s;
            }
        } catch (Exception e) {
            System.out.println("Error finding student: " + e.getMessage());
        }
        return null;
    }
}
