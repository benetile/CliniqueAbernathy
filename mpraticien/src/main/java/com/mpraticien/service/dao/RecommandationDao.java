package com.mpraticien.service.dao;

import com.mpraticien.model.Recommandation;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface RecommandationDao {

    List<Recommandation> findById(int idPatient) throws SQLException;

    Recommandation findRecommandationById(int id) throws SQLException;

    Recommandation addRecommandation(Recommandation recommandation)throws SQLException;

    void updateRecommandation(int id, String observation) throws SQLException;

    void deleteRecommandation(int id) throws SQLException;

}
