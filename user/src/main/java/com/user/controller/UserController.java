package com.user.controller;

import com.user.feign.PraticienFeign;
import com.user.model.User;
import com.user.service.Dao.UserServiceDao;
import com.user.service.Impl.GenerateIdUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserServiceDao userServiceDao;

    @Autowired
    private GenerateIdUser generateIdUser;

    @Autowired
    private PraticienFeign praticienFeign;

    @PostMapping("/user/add")
    public User addNewUser(@RequestBody User user) throws SQLException {
        user.setIdUser(generateIdUser.getSequenceNumber(User.SEQUENCE));
        if(praticienFeign.getPraticenById(user.getIdPraticien())!=null) {
            return userServiceDao.createNewUser(user);
        }
        return null;
    }

    @GetMapping("/user/id/{id}")
    public Optional<User> getUserById(@PathVariable("id") int id)throws SQLException{
        return userServiceDao.findById(id);
    }

    @PutMapping("/user/update/{id}")
    public void updateUserInfo(@PathVariable("id") int id,@RequestBody User update)throws  SQLException{
        userServiceDao.UpdateUser(id,update);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable("id") int id)throws  SQLException{
        userServiceDao.deleteById(id);
    }
}
