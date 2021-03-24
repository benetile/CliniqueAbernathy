package com.mpraticien.controller;

import com.mpraticien.model.Praticien;
import com.mpraticien.service.dao.PraticienRepository;
import com.mpraticien.service.impl.GenerateIdService;
import com.mpraticien.service.impl.PraticienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class PraticienController {

    @Autowired
    private PraticienRepository praticienRepository;

    @Autowired
    private PraticienService praticienService;

    @Autowired
    private GenerateIdService generateIdService;

    @GetMapping("prat/praticiens")
    public List<Praticien> getAllPraticiens() throws SQLException {
        return praticienRepository.findAll();
    }

    @PostMapping("/prat/add")
    public Praticien addNewPraticien(@RequestBody Praticien praticien) throws SQLException{
        praticien.setId(generateIdService.getSequenceNumberPraticien(Praticien.SEQUENCE_PRATICIEN));
        return praticienRepository.save(praticien);
    }

    @GetMapping("/prat/{speciality}")
    public List<Praticien> getPraticensBySpeciality(@PathVariable ("speciality") String speciality) throws SQLException{
        return praticienRepository.findBySpeciality(speciality);
    }
    @GetMapping("/prat/id/{id}")
    public Optional getPraticenById(@PathVariable("id") int id)throws SQLException{
       return praticienRepository.findById(id);
    }
    @GetMapping("/prat/firstname/{firstName}")
    public List<Praticien> getPraticensByFirstName(@PathVariable ("firstName") String firstName) throws SQLException {
       return praticienRepository.findByFirstname(firstName);
    }
    @GetMapping("/prat/lastname/{lastName}")
    public List<Praticien> getPraticiensByLastName(@PathVariable ("lastName") String lastName) throws SQLException{
       return praticienRepository.findByLastname(lastName);
    }
    @GetMapping("/prat/{firstName}/{lastName}")
    public Praticien getPratcien(@PathVariable ("firstName") String firstName, @PathVariable("lastName") String lastName) throws SQLException{
       return praticienRepository.findByFirstnameAndLastname(firstName, lastName);
    }
    @PutMapping("/prat/update/{id}")
    public void updatePraticien(@PathVariable ("id") int id,@RequestBody Praticien praticien) throws SQLException{
       praticien.setId(id);
     //  praticienService.updatePraticien(id, praticien);
    }
    @GetMapping("/prat/delete/{id}")
    public void deletePraticien(@PathVariable ("id") int id)throws SQLException{
       praticienRepository.deleteById(id);
    }

}
