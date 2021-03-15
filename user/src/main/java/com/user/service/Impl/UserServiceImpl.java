package com.user.service.Impl;

import com.user.config.ConfigDB;
import com.user.model.User;
import com.user.service.Dao.UserRepository;
import com.user.service.Dao.UserServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceDao {

    @Autowired
    UserRepository repository;

    @Autowired
    private ConfigDB configDB;

    private Connection connection;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User createNewUser(User user) throws SQLException {
        //save user in mongoDb
        repository.save(user);
        //save user in Sql
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO users(idUser,login,password,email,idPraticien) VALUES(?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1,user.getIdUser());
        statement.setString(2,user.getLogin());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getEmail());
        statement.setInt(5,user.getIdPraticien());

        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()){
            user.setIdUser(rs.getInt(1));
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email=?;");
        statement.setString(1,email);

        ResultSet rs = statement.executeQuery();
        User user = new User();
        while (rs.next()){
            user.setIdUser(rs.getInt("idUser"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setIdPraticien(rs.getInt("idPraticien"));
        }
        return user;
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email=? AND password=?;");
        statement.setString(1,email);
        statement.setString(2,password);

        ResultSet rs = statement.executeQuery();
        User user = new User();
        while (rs.next()){
            user.setIdUser(rs.getInt("idUser"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setIdPraticien(rs.getInt("idPraticien"));
        }
        return user;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws SQLException {
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login=? AND password=?;");
        statement.setString(1,login);
        statement.setString(2,password);

        ResultSet rs = statement.executeQuery();
        User user = new User();
        while (rs.next()){
            user.setIdUser(rs.getInt("idUser"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setPassword(rs.getString("email"));
            user.setIdPraticien(rs.getInt("idPraticien"));
        }
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void UpdateUser(int id, User user) throws SQLException {
        //update user in mongoDb
        repository.save(user);
        //update in Sql
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE users SET login=?,password=? WHERE idUser=?;");

        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setInt(3,id);

        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void deleteById(int id) throws SQLException {
        //delete user in MongoDb
        repository.deleteById(id);
        //delete user in Sql
        connection = configDB.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE idUser=?;");
        statement.setInt(1,id);
        statement.executeUpdate();
        statement.close();
    }
}
