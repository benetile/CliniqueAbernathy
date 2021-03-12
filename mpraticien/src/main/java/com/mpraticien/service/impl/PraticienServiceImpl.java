package com.mpraticien.service.impl;

import com.mpraticien.Config.ConfigDB;
import com.mpraticien.model.Praticien;
import com.mpraticien.service.dao.PraticienServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PraticienServiceImpl implements PraticienServiceDao {

    @Autowired
    private ConfigDB configDB;

    private Connection connection ;

    public PraticienServiceImpl() throws SQLException {
    }

    @Override
    public List<Praticien> findAllPraticien() throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM praticien;");
        ResultSet rs = statement.executeQuery();
        List<Praticien> praticiens = new ArrayList<>();

        while (rs.next()){
            Praticien praticien = new Praticien();
            praticien.setIdPraticien(rs.getInt("idPraticien"));
            praticien.setFirstName(rs.getString("firstName"));
            praticien.setLastName(rs.getString("lastName"));
            praticien.setSpeciality(rs.getString("speciality"));
            praticien.setSex(rs.getString("sex"));
            praticien.setPhone(rs.getString("phone"));

            praticiens.add(praticien);
        }
        return praticiens;
    }

    @Override
    public Praticien findById(int id) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM praticien WHERE idPraticien=?;");
        statement.setInt(1,id);
        ResultSet  rs = statement.executeQuery();

        while(rs.next()){
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
    public void addPraticien(Praticien praticien) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO praticien(lastName,firstName,speciality,sex,phone) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

        statement.setString(1,praticien.getFirstName());
        statement.setString(2, praticien.getLastName());
        statement.setString(3, praticien.getSpeciality());
        statement.setString(4, praticien.getSex());
        statement.setString(5,praticien.getPhone());

        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()){
            praticien.setIdPraticien(rs.getInt(1));
        }
    }

    @Override
    public void deletePraticien(int id) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM praticien WHERE idPraticien=?;");
        statement.setInt(1,id);
        statement.executeUpdate();
        statement.close();
    }
}
