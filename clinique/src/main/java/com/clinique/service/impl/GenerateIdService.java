package com.clinique.service.impl;

import com.clinique.model.DbSqAppointment;
import com.clinique.model.DbSqAssessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GenerateIdService {
    @Autowired
    private MongoOperations mongoOperations;

    public int getSequenceNumberAppointment(String sequenceName){
        //get sequence number
        Query query = new Query(Criteria.where("id").is(sequenceName));
        //update the sequence
        Update update = new Update().inc("seq",1);
        //modify in document
        DbSqAppointment counter = mongoOperations.findAndModify(query,update, FindAndModifyOptions.options().returnNew(true).upsert(true),
                DbSqAppointment.class);
        return !Objects.isNull(counter)? counter.getSeq() :1;
    }

    public int getSequenceNumberAssessment(String sequenceName){
        //get sequence number
        Query query = new Query(Criteria.where("id").is(sequenceName));
        //update the sequence
        Update update = new Update().inc("seq",1);
        //modify in document
        DbSqAssessment counter = mongoOperations.findAndModify(query,update, FindAndModifyOptions.options().returnNew(true).upsert(true),
                DbSqAssessment.class);
        return !Objects.isNull(counter)? counter.getSeq() :1;
    }
}
