package com.scholarship.service;

import java.util.Date;
import com.scholarship.bean.*;
import com.scholarship.dao.*;
import com.scholarship.util.*;

public class ScholarshipService {

    private StudentDAO studentDAO = new StudentDAO();
    private ScholarshipApplicationDAO appDAO = new ScholarshipApplicationDAO();

    public boolean submitApplication(String sid, String scheme, Date date)
            throws DuplicateApplicationException {

        Student s = studentDAO.findStudent(sid);
        if (s == null) return false;

        if (!appDAO.findActiveApplicationsForStudentAndScheme(sid, scheme).isEmpty())
            throw new DuplicateApplicationException("Active application already exists");

        ScholarshipApplication app = new ScholarshipApplication();
        app.setApplicationID(appDAO.generateApplicationID());
        app.setStudentID(sid);
        app.setSchemeCode(scheme);
        app.setApplicationDate(date);
        app.setGpaAtApplication(s.getGpa());
        app.setStatus("SUBMITTED");
        app.setAwardAmount(0);

        return appDAO.recordApplication(app);
    }

    public boolean evaluateApplication(int id, boolean approve, double amount,
                                       String remarks, String scheme, int quota)
            throws SchemeQuotaExceededException {

        ScholarshipApplication app = appDAO.findApplicationByID(id);
        if (app == null) return false;

        if (approve &&
            appDAO.countApprovedApplicationsForScheme(scheme) >= quota)
            throw new SchemeQuotaExceededException("Quota exceeded");

        return appDAO.updateApplicationStatus(
                id, approve ? "APPROVED" : "REJECTED",
                approve ? amount : 0, remarks);
    }

    public boolean revokeAward(int id, String remarks) {

        ScholarshipApplication app = appDAO.findApplicationByID(id);
        if (app == null || !"APPROVED".equals(app.getStatus()))
            return false;

        return appDAO.updateApplicationStatus(id, "REVOKED", 0, remarks);
    }
}
