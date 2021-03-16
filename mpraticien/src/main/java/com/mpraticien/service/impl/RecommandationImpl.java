package com.mpraticien.service.impl;

import com.mpraticien.Config.ConfigDB;
import com.mpraticien.model.Recommandation;
import com.mpraticien.service.dao.RecommandationDao;
import com.mpraticien.service.dao.RecommandationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecommandationImpl implements RecommandationDao {

    @Autowired
    RecommandationRepository repository;

    @Autowired
    private ConfigDB configDB;

    private Connection connection ;


    @Override
    public List<Recommandation> findById(int idPatient) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM recommandation WHERE idPatient=? ORDER BY id DESC;");
        statement.setInt(1, idPatient);
        ResultSet rs = statement.executeQuery();
        List<Recommandation> recommandations = new ArrayList<>();

        while (rs.next()){
            Recommandation recommandation = new Recommandation();
            recommandation.setId(rs.getInt("id"));
            recommandation.setIdPatient(rs.getInt("idPatient"));
            recommandation.setObservation(rs.getString("observation"));

            recommandations.add(recommandation);
        }
        return recommandations;
    }

    @Override
    public Optional findRecommandationById(int id) throws SQLException {
       return repository.findById(id);
    }

    @Override
    public Recommandation addRecommandation(Recommandation recommandation) throws SQLException {
        //add recommandation in database mongoDb
        repository.save(recommandation);

        //add recommandation in my database postgresql
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO recommandation(id,idPatient,observation) VALUES (?,?,?);", Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1,recommandation.getId());
        statement.setInt(2,recommandation.getIdPatient());
        statement.setString(3,recommandation.getObservation());

        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if(rs.next()){
            recommandation.setId(rs.getInt(1));
        }
        return recommandation;
    }

    @Override
    public void updateRecommandation(int id, Recommandation update) throws SQLException {
        //update recommandation in my databse mongoDb
        repository.save(update);
        //update recommandation in my sql database
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE recommandation SET observation=? WHERE id=?;");
        statement.setString(1, update.getObservation());
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();

    }

    @Override
    public void deleteRecommandation(int id) throws SQLException {
        //delete recommandation in my database mongoDb
        repository.deleteById(id);

        //delete recommandation in my databse Sql
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM recommandation WHERE id=?;");
        statement.setInt(1,id);
        statement.executeUpdate();
        statement.close();

    }
}
