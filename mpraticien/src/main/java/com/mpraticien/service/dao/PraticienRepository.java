package com.mpraticien.service.dao;

import com.mpraticien.model.Praticien;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PraticienRepository extends MongoRepository<Praticien,Integer>{

    @Query("{'speciality' :?0}")
    List<Praticien> findBySpeciality(String speciality);

    @Query("{'firstName' :?0}")
    List<Praticien> findByFirstname(String firstname);

    @Query("{'lastName' :?0}")
    List<Praticien> findByLastname(String lastname);

    @Query("{'firstName' :?0, 'lastName' :?1}")
    Praticien findByFirstnameAndLastname(String firstname, String lastname);
}
