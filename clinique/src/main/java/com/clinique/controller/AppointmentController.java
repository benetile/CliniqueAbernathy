package com.clinique.controller;

import com.clinique.model.Appointment;
import com.clinique.service.dao.AppointmentServiceDao;
import com.clinique.service.impl.GenerateIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class AppointmentController {

    @Autowired
    private GenerateIdService generateIdService;

    @Autowired
    private AppointmentServiceDao appointmentServiceDao;

    @PostMapping("/appointment/add")
    public Appointment createAppointment(@RequestBody Appointment input) throws SQLException {
        input.setId(generateIdService.getSequenceNumberAppointment(Appointment.SEQUENCE));
        return appointmentServiceDao.makeAnAppointment(input);
    }

    @GetMapping("/appointment/appointments/")
    public List<Appointment> getAllAppointments() {
        return appointmentServiceDao.findAll();
    }

    @GetMapping("/appointment/appointments/id/{id}")
    public Optional getAppointmentById(@PathVariable ("id") int id) throws SQLException {
        return appointmentServiceDao.findAppointmentById(id);
    }

    @PutMapping("/appointment/appointments/update/{id}")
    public void updateAppointment(@PathVariable("id") int id,@RequestBody Appointment update) throws SQLException {
        update.setId(id);
        appointmentServiceDao.updateAnAppointment(id, update);
    }

    @DeleteMapping("/appointment/appointments/delete/{id}")
    public void deleteAppointment(@PathVariable("id") int id) throws SQLException {
        appointmentServiceDao.deleteAnAppointment(id);
    }
}
