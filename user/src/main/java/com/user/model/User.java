package com.user.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Document(value ="users")
public class User {

    @Id
    private Integer idUser;
    private String login;
    private String password;
    private String email;
    private int idPraticien;

    @Transient
    public static final String SEQUENCE = "sequenceUser";

    public User() {
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdPraticien() {
        return idPraticien;
    }

    public void setIdPraticien(int idPraticien) {
        this.idPraticien = idPraticien;
    }
}
