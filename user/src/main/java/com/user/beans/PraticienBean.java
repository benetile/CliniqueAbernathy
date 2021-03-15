package com.user.beans;

import javax.persistence.Id;

public class PraticienBean {

    private Integer idPraticien;
    private String firstName;
    private String lastName;
    private String speciality;
    private String sex;
    private String phone;

    public PraticienBean() {
    }

    public Integer getIdPraticien() {
        return idPraticien;
    }

    public void setIdPraticien(Integer idPraticien) {
        this.idPraticien = idPraticien;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
