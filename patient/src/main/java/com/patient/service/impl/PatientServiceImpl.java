package com.patient.service.impl;

import com.patient.config.ConfigDB;
import com.patient.model.Patient;
import com.patient.service.dao.PatientDaoService;
import com.patient.service.dao.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientDaoService {

    @Autowired
    private ConfigDB configDB;

    private Connection connection;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient createPatient(Patient patient) throws SQLException {
        connection = configDB.getConnection();
        patientRepository.save(patient);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO patient(idPatient,firstName,lastName,birthday,sex,homeAddress,phone) VALUES(?,?,?,?,?,?,?) ");

        statement.setInt(1,patient.getId());
        statement.setString(2, patient.getFirstName());
        statement.setString(3,patient.getLastName());
        statement.setDate(4,new java.sql.Date(patient.getBirthday().getTime()));
        statement.setString(5,patient.getSex());
        statement.setString(6,patient.getHomeAddress());
        statement.setString(7, patient.getPhone());

        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if(rs.next()){
            patient.setId(rs.getInt(1));
        }
        return  patient;
    }

    @Override
    public List<Patient> findAll() throws SQLException {
        List<Patient> patients = patientRepository.findAll();
        return patients;
    }

    @Override
    public Optional<Patient> findById(int id) throws SQLException {
        connection = configDB.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM patient WHERE idPatient =?;");
        statement.setInt(1,id);

        ResultSet rs = statement.executeQuery();
        Patient patient = new Patient();
        while (rs.next()){
            patient.setId(rs.getInt("idPatient"));
            patient.setFirstName(rs.getString("firstName"));
            patient.setLastName(rs.getString("lastName"));
            patient.setSex(rs.getString("sex"));
            patient.setBirthday(rs.getDate("birthday"));
            patient.setHomeAddress(rs.getString("homeAddress"));
            patient.setPhone(rs.getString("phone"));

        }
        System.out.println(patient.getFirstName());
       // return patient;
        return patientRepository.findById(id);
    }

    @Override
    public Patient findPatientByFirstAndLastName(String firstName, String lastName) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM patient WHERE firstName=? AND lastName=?;");
        statement.setString(1,firstName);
        statement.setString(2,lastName);

        ResultSet rs = statement.executeQuery();
        Patient patient = new Patient();
        while (rs.next()){
            patient.setId(rs.getInt("idPatient"));
            patient.setFirstName(rs.getString("firstName"));
            patient.setLastName(rs.getString("lastName"));
            patient.setSex(rs.getString("sex"));
            patient.setBirthday(rs.getDate("birthday"));
            patient.setHomeAddress(rs.getString("homeAddress"));
            patient.setPhone(rs.getString("phone"));

        }
        return patient;
    }

    @Override
    public Patient updatePatient(int id,Patient patient) throws SQLException {
        patientRepository.save(patient);
        connection = configDB.getConnection();

        PreparedStatement statement = connection.prepareStatement("UPDATE patient SET sex=?,birthday=?,homeAddress=?,phone=? WHERE idPatient=?;");
        statement.setInt(5,id);

        statement.setString(1,patient.getSex());
        statement.setDate(2, new java.sql.Date(patient.getBirthday().getTime()));
        statement.setString(3, patient.getHomeAddress());
        statement.setString(4,patient.getPhone());

        statement.executeUpdate();
        statement.close();

        return patient;
    }

    @Override
    public void deletePatient(int id) throws SQLException {
        connection = configDB.getConnection();

        PreparedStatement statement = connection.prepareStatement("DELETE FROM patient WHERE idPatient=?");
        statement.setInt(1,id);
        statement.executeUpdate();
        statement.close();
        patientRepository.deleteById(id);
    }
}
