package com.mpraticien.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Document(value = "recommandation")
public class Recommandation {

    @Id
    private Integer id;
    private Integer idPatient;
    private String observation;

    @Transient
    public static final String SEQUENCE_RECOMMANDATION = "sequenceRecommandation";

    public Recommandation() {
    }

    public Recommandation(Integer idPatient, String observation) {
        this.idPatient = idPatient;
        this.observation = observation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer  idPatient) {
        this.idPatient = idPatient;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

}
