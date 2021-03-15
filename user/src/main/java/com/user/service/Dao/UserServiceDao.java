package com.user.service.Dao;

import com.user.model.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public interface UserServiceDao {
    List<User> findAll();

    User createNewUser(User user) throws SQLException;

    User findUserByEmail(String email) throws SQLException;

    User findUserByEmailAndPassword(String email,String password) throws SQLException;

    User findUserByLoginAndPassword(String login,String password) throws SQLException;

    Optional<User> findById(int id);

    void UpdateUser(int id, User user) throws SQLException;

    void deleteById(int id) throws SQLException;

}
