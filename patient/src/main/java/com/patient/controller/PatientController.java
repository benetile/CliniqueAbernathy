package com.patient.controller;

import com.patient.model.Patient;
import com.patient.service.dao.PatientDaoService;
import com.patient.service.impl.GenerateIdPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientDaoService patientDaoService;

    @Autowired
    private GenerateIdPatient generateIdPatient;

    @PostMapping("/add")
    public Patient addNewPatient(@RequestBody Patient patient) throws SQLException {
        patient.setId(generateIdPatient.getSequenceNumber(Patient.SEQUENCE_PATIENT));
        return patientDaoService.createPatient(patient);
    }

    @GetMapping("/patients")
    public List<Patient> showAllPatients() throws SQLException{
       List<Patient> patients = patientDaoService.findAll();
       return patients;
    }

    @GetMapping("/patients/{id}")
    public Optional<Patient> getPatientById(@PathVariable Integer id) throws SQLException{
        return patientDaoService.findById(id);
    }

    @PutMapping("/patients/update/{id}")
    public Patient updateInfoPatient(@PathVariable("id") int id, @RequestBody Patient patient) throws SQLException {
        patient.setId(id);
        return patientDaoService.updatePatient(id,patient);
    }
    @DeleteMapping("/patients/delete/{id}")
    public void deletePatient(@PathVariable("id") Integer id) throws SQLException {
        patientDaoService.deletePatient(id);
    }
}
