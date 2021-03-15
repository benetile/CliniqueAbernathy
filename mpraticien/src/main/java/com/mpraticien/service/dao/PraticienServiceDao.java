package com.mpraticien.service.dao;

import com.mpraticien.model.Praticien;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public interface PraticienServiceDao {

    List<Praticien> findAllPraticien() throws SQLException;

    Optional findById(int id) throws SQLException;

    List<Praticien> findByFirstName(String firstName) throws SQLException;

    List<Praticien> findByLastName(String lastName) throws SQLException;

    List<Praticien> findPraticiensBySpecilality(String speciality) throws SQLException;

    Praticien findPraticien(String firstName, String lastName) throws SQLException;

    void updateInfoPraticien(int id, Praticien praticien) throws SQLException;

    Praticien addPraticien(Praticien praticien) throws SQLException;

    void deletePraticien(int id) throws SQLException;
}
