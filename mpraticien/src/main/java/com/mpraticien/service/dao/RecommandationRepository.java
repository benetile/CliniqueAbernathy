package com.mpraticien.service.dao;

import com.mpraticien.model.Recommandation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommandationRepository extends MongoRepository<Recommandation,Integer>{

    @Query("{'idPatient' :?0 }")
    List<Recommandation> findByIdPatient(int idPatient);

}
