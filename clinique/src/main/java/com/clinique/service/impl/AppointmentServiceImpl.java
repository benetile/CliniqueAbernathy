package com.clinique.service.impl;

import com.clinique.config.ConfigBD;
import com.clinique.model.Appointment;
import com.clinique.service.dao.AppointmentRepository;
import com.clinique.service.dao.AppointmentServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentServiceDao {

    @Autowired
    private ConfigBD configBD;

    @Autowired
    AppointmentRepository repository;

    private Connection connection;

    @Override
    public Appointment makeAnAppointment(Appointment appointment) throws SQLException {
        //save appointment in database mongoDb
        repository.save(appointment);

        //save appointement in sql
        connection = configBD.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO appointment(id,firstName,lastName,pattern,dateAppointment) VALUES (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, appointment.getFirstName());
        statement.setString(2, appointment.getLastName());
        statement.setString(3, appointment.getPattern());
        statement.setDate(4,appointment.getDateAppointment());

        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()){
            appointment.setId(rs.getInt(1));
            return appointment;
        }
        return appointment;
    }

    @Override
    public List<Appointment> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional findAppointmentById(int id) throws SQLException {
       return repository.findById(id);
    }

    @Override
    public Appointment findAppointmentByFirstAndLastName(String firstName, String lastName) throws SQLException {
        connection = configBD.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM appointment WHERE firstName=? AND lastName=?;");
        statement.setString(1,firstName);
        statement.setString(2,lastName);

        ResultSet rs = statement.executeQuery();
        Appointment appointment = new Appointment();
        while (rs.next()){
            appointment.setId(rs.getInt("id"));
            appointment.setFirstName(rs.getString("firstName"));
            appointment.setLastName(rs.getString("lastName"));
            appointment.setPattern(rs.getString("pattern"));
            appointment.setDateAppointment(rs.getDate("dateAppointment"));
        }
        return appointment;
    }

    @Override
    public void updateAnAppointment(int id, Appointment appointment) throws SQLException {
        //update in mongodb
        repository.save(appointment);
        //update sql
        connection = configBD.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE appointment SET firstName=?,lastName=?,pattern=?,dateAppointment=? WHERE id=?;");
        statement.setString(1, appointment.getFirstName());
        statement.setString(2, appointment.getLastName());
        statement.setString(3, appointment.getPattern());
        statement.setDate(4,appointment.getDateAppointment());
        statement.setInt(5,id);
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void deleteAnAppointment(int id) throws SQLException {
        //delete in mongoDb
        repository.deleteById(id);

        //delete in sql
        connection = configBD.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM appointment WHERE id=?;");
        statement.setInt(1,id);
        statement.executeUpdate();
        statement.close();
    }
}
