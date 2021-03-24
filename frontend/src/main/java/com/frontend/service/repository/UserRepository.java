package com.frontend.service.repository;

import com.frontend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value ="SELECT u FROM users u WHERE u.username = ?1",nativeQuery = true)
    User findByUsername(String username);

    @Query(value ="SELECT u FROM users u WHERE u.username = ?1 and u.password = ?2",nativeQuery = true)
    User findByUsernameAndPassword(String username, String password);

    @Query(value ="SELECT u from USERS u WHERE u.idPraticien = ?1",nativeQuery = true)
    User findByIdPraticien(int idPraticien);
}
