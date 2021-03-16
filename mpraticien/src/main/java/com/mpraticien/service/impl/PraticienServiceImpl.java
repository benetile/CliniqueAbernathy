package com.mpraticien.service.impl;

import com.mpraticien.Config.ConfigDB;
import com.mpraticien.model.Praticien;
import com.mpraticien.service.dao.PraticienRepository;
import com.mpraticien.service.dao.PraticienServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PraticienServiceImpl implements PraticienServiceDao {

    @Autowired
    private ConfigDB configDB;

    @Autowired
    PraticienRepository repository;

    private Connection connection ;

    @Override
    public List<Praticien> findAllPraticien() throws SQLException {
        return repository.findAll();
    }

    @Override
    public Optional findById(int id) throws SQLException {
        return repository.findById(id);
    }

    @Override
    public List<Praticien> findByFirstName(String firstName) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT idPraticien,firstName,LastName,categorie FROM praticien WHERE firstName LIKE '%?%';");
        statement.setString(1,firstName);
        ResultSet rs = statement.executeQuery();
        List<Praticien> praticiens = new ArrayList<>();

        while (rs.next()){
            Praticien praticien = new Praticien();
            praticien.setIdPraticien(rs.getInt("idPraticien"));
            praticien.setFirstName(rs.getString("firstName"));
            praticien.setLastName(rs.getString("lastName"));
            praticien.setSpeciality(rs.getString("speciality"));

            praticiens.add(praticien);
        }

        return praticiens;
    }

    @Override
    public List<Praticien> findByLastName(String lastName) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT idPraticien,firstName,LastName,categorie FROM praticien WHERE lastName LIKE '%?%';");
        statement.setString(1,lastName);
        ResultSet rs = statement.executeQuery();
        List<Praticien> praticiens = new ArrayList<>();

        while (rs.next()){
            Praticien praticien = new Praticien();
            praticien.setIdPraticien(rs.getInt("idPraticien"));
            praticien.setFirstName(rs.getString("firstName"));
            praticien.setLastName(rs.getString("lastName"));
            praticien.setSpeciality(rs.getString("speciality"));

            praticiens.add(praticien);
        }

        return praticiens;
    }

    @Override
    public List<Praticien> findPraticiensBySpecilality(String speciality) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT idPraticien,firstName,LastName,categorie FROM praticien WHERE speciality LIKE '%?%';");
        statement.setString(1,speciality);
        ResultSet rs = statement.executeQuery();
        List<Praticien> praticiens = new ArrayList<>();

        while (rs.next()){
            Praticien praticien = new Praticien();
            praticien.setIdPraticien(rs.getInt("idPraticien"));
            praticien.setFirstName(rs.getString("firstName"));
            praticien.setLastName(rs.getString("lastName"));
            praticien.setSpeciality(rs.getString("speciality"));

            praticiens.add(praticien);
        }

        return praticiens;
    }


    @Override
    public Praticien findPraticien(String firstName, String lastName) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM praticien WHERE firstName=? AND lastName=?");
        statement.setString(1,firstName);
        statement.setString(2,lastName);

        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            Praticien praticien = new Praticien();
            praticien.setIdPraticien(rs.getInt("idPraticien"));
            praticien.setFirstName(rs.getString("firstName"));
            praticien.setLastName(rs.getString("lastName"));
            praticien.setSpeciality(rs.getString("speciality"));
            praticien.setSex(rs.getString("sex"));
            praticien.setPhone(rs.getString("phone"));

            return praticien;
        }
        return null;
    }

    @Override
    public void updateInfoPraticien(int id, Praticien praticien) throws SQLException {
        //update praticien in database Mongodb
        repository.save(praticien);
        //update praticien in postgresql
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE praticien SET firstName=?,lastName=?,speciality=?,phone=? WHERE idPraticien=? ");
        statement.setString(1,praticien.getFirstName());
        statement.setString(2, praticien.getLastName());
        statement.setString(3, praticien.getSpeciality());
        statement.setString(4,praticien.getPhone());
        statement.setInt(5,id);

        statement.executeUpdate();
        statement.close();
    }

    @Override
    public Praticien addPraticien(Praticien praticien) throws SQLException {
        //create new praticien in MongoDb
        repository.save(praticien);

        //create new praticien to sql database
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO praticien(idPraticien,firstName,lastName,speciality,sex,phone) VALUES(?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1,praticien.getIdPraticien());
        statement.setString(2,praticien.getFirstName());
        statement.setString(3, praticien.getLastName());
        statement.setString(4, praticien.getSpeciality());
        statement.setString(5, praticien.getSex());
        statement.setString(6,praticien.getPhone());

        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()){
            praticien.setIdPraticien(rs.getInt(1));
        }
        return praticien;
    }

    @Override
    public void deletePraticien(int id) throws SQLException {
        //delete praticien in database MongoDb
        repository.deleteById(id);

        //And delete praticien in my database postgresql
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM praticien WHERE idPraticien=?;");
        statement.setInt(1,id);
        statement.executeUpdate();
        statement.close();
    }
}
