package com.patient.service.dao;

import com.patient.model.Patient;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public interface PatientDaoService {

    Patient createPatient(Patient patient) throws SQLException;

    List<Patient> findAll() throws SQLException;

    Optional<Patient> findById(int id) throws SQLException;

    Patient findPatientByFirstAndLastName(String firstName, String lastName) throws SQLException;

    Patient updatePatient( int id,Patient patient) throws SQLException;

    void deletePatient(int id) throws SQLException;



}
