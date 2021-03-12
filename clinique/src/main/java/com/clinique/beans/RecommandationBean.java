package com.clinique.beans;

import java.sql.Date;

public class RecommandationBean {
    private Integer id;
    private int idPatient;
    private String observation;
    private Date dateDObservation;

    public RecommandationBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Date getDateDObservation() {
        return dateDObservation;
    }

    public void setDateDObservation(Date dateDObservation) {
        this.dateDObservation = dateDObservation;
    }
}
