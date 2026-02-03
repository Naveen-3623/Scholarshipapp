package com.scholarship.dao;

import java.sql.*;
import java.util.*;
import com.scholarship.bean.ScholarshipApplication;
import com.scholarship.util.DBUtil;

public class ScholarshipApplicationDAO {

    public int generateApplicationID() {
        String sql = "SELECT NVL(MAX(APPLICATION_ID),710000)+1 FROM SCHOLARSHIP_APP_TBL";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 710001;
    }

    public boolean recordApplication(ScholarshipApplication app) {

        String sql = "INSERT INTO SCHOLARSHIP_APP_TBL VALUES (?,?,?,?,?,?,?,?)";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, app.getApplicationID());
            ps.setString(2, app.getStudentID());
            ps.setString(3, app.getSchemeCode());
            ps.setDate(4, new java.sql.Date(app.getApplicationDate().getTime()));
            ps.setDouble(5, app.getGpaAtApplication());
            ps.setString(6, app.getStatus());
            ps.setDouble(7, app.getAwardAmount());
            ps.setString(8, app.getRemarks());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Insert error: " + e.getMessage());
        }
        return false;
    }

    public List<ScholarshipApplication> findActiveApplicationsForStudentAndScheme(
            String sid, String sc) {

        List<ScholarshipApplication> list = new ArrayList<>();
        String sql = "SELECT APPLICATION_ID FROM SCHOLARSHIP_APP_TBL " +
                     "WHERE STUDENT_ID=? AND SCHEME_CODE=? " +
                     "AND STATUS IN ('SUBMITTED','APPROVED')";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sid);
            ps.setString(2, sc);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) list.add(new ScholarshipApplication());
        } catch (Exception e) {
            System.out.println("Error finding active applications: " + e.getMessage());
        }
        return list;
    }

    public ScholarshipApplication findApplicationByID(int id) {

        String sql = "SELECT * FROM SCHOLARSHIP_APP_TBL WHERE APPLICATION_ID=?";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ScholarshipApplication a = new ScholarshipApplication();
                a.setApplicationID(id);
                a.setStudentID(rs.getString("STUDENT_ID"));
                a.setSchemeCode(rs.getString("SCHEME_CODE"));
                a.setApplicationDate(rs.getDate("APPLICATION_DATE"));
                a.setGpaAtApplication(rs.getDouble("GPA_AT_APPLICATION"));
                a.setStatus(rs.getString("STATUS"));
                a.setAwardAmount(rs.getDouble("AWARD_AMOUNT"));
                a.setRemarks(rs.getString("REMARKS"));
                return a;
            }
        } catch (Exception e) {
            System.out.println("Error finding application: " + e.getMessage());
        }
        return null;
    }

    public int countApprovedApplicationsForScheme(String schemeCode) {

        String sql = "SELECT COUNT(*) FROM SCHOLARSHIP_APP_TBL " +
                     "WHERE SCHEME_CODE=? AND STATUS='APPROVED'";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, schemeCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            System.out.println("Error counting approved applications: " + e.getMessage());
        }
        return 0;
    }

    public boolean updateApplicationStatus(
            int id, String status, double amount, String remarks) {

        String sql = "UPDATE SCHOLARSHIP_APP_TBL " +
                     "SET STATUS=?, AWARD_AMOUNT=?, REMARKS=? " +
                     "WHERE APPLICATION_ID=?";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setDouble(2, amount);
            ps.setString(3, remarks);
            ps.setInt(4, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Update error: " + e.getMessage());
        }
        return false;
    }
}
