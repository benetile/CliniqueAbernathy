package com.mpraticien.service.dao;

import com.mpraticien.model.Recommandation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommandationRepository extends MongoRepository<Recommandation,Integer>{
}
