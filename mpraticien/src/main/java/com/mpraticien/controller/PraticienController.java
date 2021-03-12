package com.mpraticien.controller;

import com.mpraticien.model.Praticien;
import com.mpraticien.service.dao.PraticienServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class PraticienController {

    @Autowired
    PraticienServiceDao praticienServiceDao;

    @GetMapping("/praticiens")
    public List<Praticien> getAllPraticiens() throws SQLException {
        return praticienServiceDao.findAllPraticien();
    }

    @PostMapping("/praticiens/add")
    public Praticien addNewPraticien(Praticien praticien) throws SQLException{
        Praticien inputPraticien = praticienServiceDao.findPraticien(praticien.getFirstName(), praticien.getLastName());
        if (inputPraticien!=null){
            praticienServiceDao.addPraticien(inputPraticien);
            return inputPraticien;
        }
        return null;
    }

    @GetMapping("/praticiens/{speciality}")
    public List<Praticien> getPraticensBySpeciality(@PathVariable ("speciality") String speciality) throws SQLException{
        List<Praticien> praticiensSpec = praticienServiceDao.findPraticiensBySpecilality(speciality);
        if(praticiensSpec!=null){
            return praticiensSpec;
        }
        else {
            throw new SQLException("Aucun Praticiens " +speciality+ " n'a été trouvé");
        }
    }
    @GetMapping("/praticiens/{id}")
    public Praticien getPraticenById(@PathVariable("id") int id)throws SQLException{
        Praticien praticien = praticienServiceDao.findById(id);
        if(praticien!=null){
            return praticien;
        }
        else {
            throw new SQLException("n'existe pas");
        }
    }
    @GetMapping("/praticiens/{firstName}")
    public List<Praticien> getPraticensByFirstName(@PathVariable ("firstName") String firstName) throws SQLException {
        List<Praticien> byFirstNames = praticienServiceDao.findByFirstName(firstName);
        if(byFirstNames!=null){
            return byFirstNames;
        }
        else {
            throw new SQLException("Aucun Praticiens " +firstName+ " n'a été trouvé");
        }
    }
    @GetMapping("/praticiens/{lastName}")
    public List<Praticien> getPraticiensByLastName(@PathVariable ("lastName") String lastName) throws SQLException{
        List<Praticien> byLastNames = praticienServiceDao.findByLastName(lastName);
        if(byLastNames!=null){
            return byLastNames;
        }
        else {
            throw new SQLException("Aucun Praticiens " +lastName+ " n'a été trouvé");
        }
    }
    @GetMapping("/praticiens/{firstName}/{lastName}")
    public Praticien getPratcien(@PathVariable ("firstName") String firstName, @PathVariable("lastName") String lastName) throws SQLException{
        Praticien praticien = praticienServiceDao.findPraticien(firstName, lastName);
        if (praticien!=null){
            return praticien;
        }
        else {
            throw new SQLException("Le Praticien "+firstName+ " "+lastName+" n'existe pas");
        }
    }
    @PutMapping("/praticiens/update/{id}")
    public void updatePraticien(@PathVariable ("id") int id,@RequestBody Praticien praticien) throws SQLException{
        Praticien updatePraticien = praticienServiceDao.findById(id);
        if(updatePraticien!=null){
             praticienServiceDao.updateInfoPraticien(id, praticien);
        }
        else {
            throw new SQLException(" :( ");
        }
    }
    @DeleteMapping("/praticiens/delete/{id}")
    public void deletePraticien(@PathVariable ("id") int id)throws SQLException{
        Praticien praticien = praticienServiceDao.findById(id);
        if (praticien==null){
            throw new SQLException("erreur "+id+ " n'existe pas ");
        }
        else{
            praticienServiceDao.deletePraticien(id);
        }
    }

}
