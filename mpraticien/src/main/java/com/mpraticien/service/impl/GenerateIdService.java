package com.mpraticien.service.impl;

import com.mpraticien.model.DbSequencePraticien;
import com.mpraticien.model.DbSequenceRecommandation;
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

    public int getSequenceNumberPraticien(String sequenceName){
        //get sequence number
        Query query = new Query(Criteria.where("id").is(sequenceName));
        //update the sequence
        Update update = new Update().inc("seq",1);
        //modify in document
        DbSequencePraticien counter = mongoOperations.findAndModify(query,update, FindAndModifyOptions.options().returnNew(true).upsert(true),
                DbSequencePraticien.class);
        return !Objects.isNull(counter)? counter.getSeq() :1;
    }

    public int getSequenceNumberRecommandtaion(String sequenceName){
        //get sequence number
        Query query = new Query(Criteria.where("id").is(sequenceName));
        //update the sequence
        Update update = new Update().inc("seq",1);
        //modify in document
        DbSequenceRecommandation counter = mongoOperations.findAndModify(query,update, FindAndModifyOptions.options().returnNew(true).upsert(true),
                DbSequenceRecommandation.class);
        return !Objects.isNull(counter)? counter.getSeq() :1;
    }
}
