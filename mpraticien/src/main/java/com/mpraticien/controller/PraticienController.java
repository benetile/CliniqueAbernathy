package com.mpraticien.controller;

import com.mpraticien.model.Praticien;
import com.mpraticien.service.dao.PraticienServiceDao;
import com.mpraticien.service.impl.GenerateIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class PraticienController {

    @Autowired
    private PraticienServiceDao praticienServiceDao;

    @Autowired
    private GenerateIdService generateIdService;

    @GetMapping("/praticiens")
    public List<Praticien> getAllPraticiens() throws SQLException {
        return praticienServiceDao.findAllPraticien();
    }

    @PostMapping("/praticiens/add")
    public Praticien addNewPraticien(@RequestBody Praticien praticien) throws SQLException{
        praticien.setIdPraticien(generateIdService.getSequenceNumberPraticien(Praticien.SEQUENCE_PRATICIEN));
        return praticienServiceDao.addPraticien(praticien);
    }

    @GetMapping("/praticiens/{speciality}")
    public List<Praticien> getPraticensBySpeciality(@PathVariable ("speciality") String speciality) throws SQLException{
        return praticienServiceDao.findPraticiensBySpecilality(speciality);
    }
    @GetMapping("/praticiens/{id}")
    public Optional getPraticenById(@PathVariable("id") int id)throws SQLException{
       return praticienServiceDao.findById(id);
    }
    @GetMapping("/praticiens/{firstName}")
    public List<Praticien> getPraticensByFirstName(@PathVariable ("firstName") String firstName) throws SQLException {
       return praticienServiceDao.findByFirstName(firstName);
    }
    @GetMapping("/praticiens/{lastName}")
    public List<Praticien> getPraticiensByLastName(@PathVariable ("lastName") String lastName) throws SQLException{
       return praticienServiceDao.findByLastName(lastName);
    }
    @GetMapping("/praticiens/{firstName}/{lastName}")
    public Praticien getPratcien(@PathVariable ("firstName") String firstName, @PathVariable("lastName") String lastName) throws SQLException{
       return praticienServiceDao.findPraticien(firstName, lastName);
    }
    @PutMapping("/praticiens/update/{id}")
    public void updatePraticien(@PathVariable ("id") int id,@RequestBody Praticien praticien) throws SQLException{
       praticien.setIdPraticien(id);
       praticienServiceDao.updateInfoPraticien(id, praticien);
    }
    @DeleteMapping("/praticiens/delete/{id}")
    public void deletePraticien(@PathVariable ("id") int id)throws SQLException{
       praticienServiceDao.deletePraticien(id);
    }

}
