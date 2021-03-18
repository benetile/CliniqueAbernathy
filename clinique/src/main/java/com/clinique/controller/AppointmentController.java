package com.clinique.controller;

import com.clinique.model.Appointment;
import com.clinique.service.dao.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping("/appointment/add")
    public Appointment createAppointment(@RequestBody Appointment input) throws SQLException {
        return appointmentRepository.save(input);
    }

    @GetMapping("/appointment/appointments/")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @GetMapping("/appointment/appointments/id/{id}")
    public Optional getAppointmentById(@PathVariable ("id") int id) throws SQLException {
        return appointmentRepository.findById(id);
    }

    @PutMapping("/appointment/appointments/update/{id}")
    public void updateAppointment(@PathVariable("id") int id,@RequestBody Appointment update) throws SQLException {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if(appointment.isPresent()){
            appointmentRepository.save(update);
        }
    }

    @DeleteMapping("/appointment/appointments/delete/{id}")
    public void deleteAppointment(@PathVariable("id") int id) throws SQLException {
        appointmentRepository.deleteById(id);
    }
}
