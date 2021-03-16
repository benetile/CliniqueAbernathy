package com.clinique.controller;

import com.clinique.beans.PatientBean;
import com.clinique.model.Assessment;
import com.clinique.proxy.MPatientProxy;
import com.clinique.service.dao.AssessmentServiceDao;
import com.clinique.service.impl.GenerateIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@RestController
public class AssessmentController {

    @Autowired
    private AssessmentServiceDao assessmentServiceDao;

    @Autowired
    MPatientProxy mPatientProxy;

    @Autowired
    private GenerateIdService generateIdService;

    @GetMapping("/assess/delete/{id}")
    public void deleteAnAssessment(@PathVariable("id") int id) throws SQLException{
       assessmentServiceDao.deleteAssessment(id);
    }

    @GetMapping("/assess/id/{id}")
    public Assessment generateAssessment(@PathVariable ("id") int id) throws SQLException {
        PatientBean patientBean = mPatientProxy.getPatientById(id);
        if (patientBean==null){
            return null;
        }
        Assessment assess = new Assessment();
        assess.setId(generateIdService.getSequenceNumberAssessment(Assessment.SEQUENCE));
        assess.setIdPatient(id);
        assess.setFirstName(patientBean.getFirstName());
        assess.setAge(new Date().getYear() - patientBean.getBirthday().getYear());
        assess.setAssessment(assessmentServiceDao.generateAssessment(id,assess.getAge(),patientBean.getSex()));

        return assess;
    }

    @GetMapping("/assess")
    public List<Assessment> getAllAssessments() throws SQLException {
         List<Assessment> assessments = assessmentServiceDao.findAssessments();
         return assessments;
    }

    @GetMapping("/assess/firstName/{firstName}")
    public Assessment getAssementByname(@PathVariable("firstName") String firstName)throws SQLException{
       return assessmentServiceDao.findAssessmentByFirstName(firstName);
    }
}
