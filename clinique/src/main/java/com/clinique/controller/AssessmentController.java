package com.clinique.controller;

import com.clinique.beans.PatientBean;
import com.clinique.model.Assessment;
import com.clinique.proxy.MPatientProxy;
import com.clinique.service.dao.AssessmentRepository;
import com.clinique.service.impl.GenerateAssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@RestController
public class AssessmentController {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private GenerateAssessService assessService;

    @Autowired
    MPatientProxy mPatientProxy;

    @GetMapping("/assess/id/{id}")
    public Assessment generateAssessment(@PathVariable ("id") int id){
        PatientBean patientBean = mPatientProxy.getPatientById(id);
        System.out.println(patientBean.getFirstName());
        if (patientBean==null){
            return null;
        }
        Assessment assess = new Assessment();

        assess.setIdPatient(patientBean.getId());
        assess.setFirstName(patientBean.getFirstName());
        assess.setAge(new Date().getYear() - patientBean.getBirthday().getYear());
        assess.setAssessment(assessService.generateAssessment(id,assess.getAge(), patientBean.getSex()));

        return assess;
    }

    @GetMapping("/assess")
    public List<Assessment> getAllAssessments() throws SQLException {
         List<Assessment> assessments = assessService.getAssessments();
         return assessments;
    }

}
