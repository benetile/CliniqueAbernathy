package com.mpraticien.model;

import javax.persistence.*;

@Entity
@Table(name = "praticien")
public class Praticien {

    @Id
    private Integer idPraticien;
    private String firstName;
    private String lastName;
    private String speciality;
    private String sex;
    private String phone;

    @Transient
    public static final String SEQUENCE_PRATICIEN = "sequencePraticien";

    public Praticien() {
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

    @Override
    public String toString() {
        return "Praticien{" +
                "id_praticien=" + idPraticien +
                ", prenom='" + lastName + '\'' +
                ", nom='" + firstName + '\'' +
                ", categorie='" + speciality + '\'' +
                ", sex='" + sex + '\'' +
                ", telephone='" + phone + '\'' +
                '}';
    }
}
