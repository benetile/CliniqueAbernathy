package com.clinique.service.dao;

import com.clinique.model.Appointment;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public interface AppointmentServiceDao {

    Appointment makeAnAppointment(Appointment appointment) throws SQLException;

    List<Appointment> findAll();

    Optional findAppointmentById(int id) throws SQLException;

    Appointment findAppointmentByFirstAndLastName(String firstName, String lastName) throws SQLException;

    void updateAnAppointment(int id,Appointment appointment) throws SQLException;

    void deleteAnAppointment(int id) throws SQLException;

}
