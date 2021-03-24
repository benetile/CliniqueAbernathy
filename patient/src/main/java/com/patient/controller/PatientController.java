package com.patient.controller;

import com.patient.model.Patient;
import com.patient.service.dao.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    Patient pat = new Patient();

    @PostMapping("/pat/add")
    public Patient validatePatient(@RequestBody Patient patient, BindingResult result) throws SQLException {
        pat = patientRepository.findByFirstnameAndLastname(patient.getFirstname(),patient.getLastname());
        if (!result.hasErrors()){
            if (pat==null){
                return patientRepository.save(patient);
            }
        }
        return null;
    }

    @GetMapping("/pat/patients/{firstname}/{lastname}")
    public Patient getPatientByFirstAndLastname(@PathVariable("firstname") String firstname,
                                                @PathVariable("lastname") String lastname){
        return patientRepository.findByFirstnameAndLastname(firstname,lastname);
    }

    @GetMapping("/pat/patients")
    public List<Patient> showAllPatients(){
        return patientRepository.findAll();
    }

    @GetMapping("/pat/id/{id}")
    public Patient getPatientById(@PathVariable ("id") Integer id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid patient "+id));
        return patient;
    }

    @PutMapping("/pat/update/{id}")
    public Patient updateInfoPatient(@PathVariable("id") Integer id, @RequestBody Patient patient){
        Optional<Patient> update = patientRepository.findById(id);
        if (update.isPresent()){
            return patientRepository.save(patient);
        }
        return null;
    }

    @DeleteMapping("/pat/delete/{id}")
    public void deletePatient(@PathVariable("id") Integer id,Model model) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid patient " +id));
        patientRepository.deleteById(id);
    }
}
