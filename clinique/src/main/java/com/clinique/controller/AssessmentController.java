package com.clinique.controller;

import com.clinique.beans.PatientBean;
import com.clinique.model.Assessment;
import com.clinique.proxy.MPatientProxy;
import com.clinique.service.dao.AssessmentServiceDao;
import com.clinique.service.impl.AssessmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AssessmentController {


    @Autowired
    private AssessmentServiceDao assessmentServiceDao;

    @Autowired
    private MPatientProxy mPatientProxy;

    @GetMapping("/assess/delete/{id}")
    public void deleteAnAssessment(@PathVariable("id") int id) throws SQLException{
        Assessment assessment = assessmentServiceDao.findAssessmentById(id);
        if(assessment!=null){
            assessmentServiceDao.deleteAssessment(id);
        }
        else{
            throw new SQLException("n'existe pas");
        }
    }
    @GetMapping("/assess/id/{id}")
    public Assessment generateAssessment(@PathVariable ("id") int id) throws SQLException {
        Assessment generateAssess = assessmentServiceDao.generateAssessment(id);
        Assessment assess = assessmentServiceDao.findAssessmentById(id);
        if(assess!=null){
            assessmentServiceDao.updateAssessment(id,generateAssess);
            return generateAssess;
        }
        else {
            assessmentServiceDao.saveAssessment(generateAssess);

        }
        return generateAssess;
    }

    @GetMapping("/assess")
    public List<Assessment> getAllAssessments() throws SQLException {
         List<Assessment> assessments = assessmentServiceDao.findAssessments();
         return assessments;
    }

    @GetMapping("/assess/firstName/{firstName}")
    public Assessment getAssementByname(@PathVariable("firstName") String firstName)throws SQLException{
        Assessment assessment = assessmentServiceDao.findAssessmentByFirstName(firstName);
        if(assessment!=null){
            return assessment;
        }
        else{
            throw new SQLException("n'existe pas");
        }
    }
}
