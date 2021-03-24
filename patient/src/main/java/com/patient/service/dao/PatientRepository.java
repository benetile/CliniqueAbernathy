package com.patient.service.dao;

import com.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

    Patient findByFirstname(String firstname);

    Patient findByFirstnameAndLastname(String firstname, String lastname);

}
