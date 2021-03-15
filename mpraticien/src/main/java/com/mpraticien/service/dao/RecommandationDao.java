package com.mpraticien.service.dao;

import com.mpraticien.model.Recommandation;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecommandationDao {

    List<Recommandation> findById(int idPatient) throws SQLException;

    Optional findRecommandationById(int id) throws SQLException;

    Recommandation addRecommandation(Recommandation recommandation)throws SQLException;

    void updateRecommandation(int id, Recommandation update) throws SQLException;

    void deleteRecommandation(int id) throws SQLException;

}
