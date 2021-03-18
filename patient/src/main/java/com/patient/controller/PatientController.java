package com.patient.controller;

import com.patient.model.Patient;
import com.patient.service.dao.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private  PatientRepository patientRepository;

    @PostMapping("/add")
    public Patient addNewPatient(@RequestBody Patient patient) throws SQLException {
        return patientRepository.save(patient);
    }

    @GetMapping("/patients")
    public List<Patient> showAllPatients() throws SQLException{
       return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    public Optional<Patient> getPatientById(@PathVariable Integer id) throws SQLException{
        return patientRepository.findById(id);
    }

    @PutMapping("/patients/update/{id}")
    public Patient updateInfoPatient(@PathVariable("id") int id, @RequestBody Patient patient) throws SQLException {
        patient.setId(id);
        return patientRepository.save(patient);
    }

    @DeleteMapping("/patients/delete/{id}")
    public void deletePatient(@PathVariable("id") Integer id) throws SQLException {
        patientRepository.deleteById(id);
    }
}
