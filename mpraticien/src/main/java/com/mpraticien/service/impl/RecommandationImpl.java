package com.mpraticien.service.impl;

import com.mpraticien.Config.ConfigDB;
import com.mpraticien.model.Recommandation;
import com.mpraticien.service.dao.RecommandationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommandationImpl implements RecommandationDao {
    @Autowired
    private ConfigDB configDB;

    private Connection connection ;

    public RecommandationImpl() throws SQLException {
    }

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
    public Recommandation findRecommandationById(int id) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM recommandation WHERE id=?;");
        statement.setInt(1,id);
        ResultSet rs = statement.executeQuery();

        Recommandation recommandation = new Recommandation();
        while (rs.next()){
            recommandation.setId(rs.getInt("id"));
            recommandation.setIdPatient(rs.getInt("idPatient"));
        }
        return recommandation;
    }

    @Override
    public Recommandation addRecommandation(Recommandation recommandation) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO recommandation(idPatient,observation) VALUES (?,?);", Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1,recommandation.getIdPatient());
        statement.setString(2,recommandation.getObservation());

        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if(rs.next()){
            recommandation.setId(rs.getInt(1));
        }
        return recommandation;
    }

    @Override
    public void updateRecommandation(int id, String obrservation) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE recommandation SET observation=? WHERE id=?;");
        statement.setString(1,obrservation);
        statement.setInt(2,id);
        statement.executeUpdate();
        statement.close();

    }

    @Override
    public void deleteRecommandation(int id) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM recommandation WHERE id=?;");
        statement.setInt(1,id);
        statement.executeUpdate();
        statement.close();

    }
}
