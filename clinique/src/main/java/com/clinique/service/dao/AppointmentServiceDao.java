package com.clinique.service.dao;

import com.clinique.model.Appointment;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface AppointmentServiceDao {

    Appointment makeAnAppointment(Appointment appointment) throws SQLException;

    Appointment findAppointmentById(int id) throws SQLException;

    Appointment findAppointmentByFirstAndLastName(String firstName, String lastName) throws SQLException;

    void updateAnAppointment(int id,Appointment appointment) throws SQLException;

    void deleteAnAppointment(int id) throws SQLException;

}
