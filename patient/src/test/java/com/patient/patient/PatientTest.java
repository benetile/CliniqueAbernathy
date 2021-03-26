package com.patient.patient;

import com.patient.controller.PatientController;
import com.patient.model.Patient;
import com.patient.service.dao.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientController patientController;

   @Test
   public void addPatient() throws SQLException {
       Patient patient =new Patient("firstname11","lastname11",new java.sql.Date(new Date().getTime()),"M","address1","0000");

       patientRepository.save(patient);

       Patient test = patientRepository.findByFirstname(patient.getFirstname());
       assertNotNull(patient);
       assertEquals(patient.getLastname(),test.getLastname());
   }

   @Test
    public void getAllPatient(){

       List<Patient> patients =patientRepository.findAll();

       assertNotNull(patients.size());
   }

}
