package com.mpraticien.service.dao;

import com.mpraticien.model.Praticien;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PraticienRepository extends MongoRepository<Praticien,Integer>{
}
