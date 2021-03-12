package com.patient.controller;

import com.patient.model.Patient;
import com.patient.service.dao.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/add")
    public Patient addNewPatient(@RequestBody Patient patient) throws SQLException {

       /* Patient inputPatient = patientServiceDao.findPatientByFirstAndLastName(patient.getFirstName(),patient.getLastName());
        patientServiceDao.addPatient(patient);*/
        return patientRepository.save(patient);
    }

    @GetMapping("/patients")
    public List<Patient> showAllPatients() throws SQLException{
       List<Patient> patients = (List<Patient>) patientRepository.findAll();
        return patients;
    }

    @GetMapping("/patients/{id}")
    public Optional<Patient> getPatientById(@PathVariable int id) throws SQLException{
        return patientRepository.findById(id);
    }

    @PutMapping("/patients/update/{id}")
    public Patient updateInfoPatient(@PathVariable("id") int id, @RequestBody Patient patient){

        return patient;
    }
    @DeleteMapping("/patients/delete/{id}")
    public void deletePatient(@PathVariable("id") int id) throws SQLException {
        patientRepository.deleteById(id);
    }
}
