package com.clinique.service.dao;

import com.clinique.model.Assessment;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface AssessmentServiceDao {

    Assessment findAssessmentById(int id) throws SQLException;

    Assessment findAssessmentByFirstName(String firstName)throws SQLException;

    Assessment generateAssessment(int id) throws SQLException;

    void saveAssessment(Assessment input) throws SQLException;

    void updateAssessment(int id,Assessment update)throws SQLException;

    void deleteAssessment(int id) throws SQLException;

    List<Assessment> findAssessments() throws SQLException;

}
