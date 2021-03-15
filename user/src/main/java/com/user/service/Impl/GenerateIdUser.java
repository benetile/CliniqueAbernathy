package com.user.service.Impl;

import com.user.model.DbSeqUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GenerateIdUser {

    @Autowired
    private MongoOperations mongoOperations;

    public int getSequenceNumber(String sequenceName){
        //get sequence number
        Query query = new Query(Criteria.where("id").is(sequenceName));
        //update the sequence
        Update update = new Update().inc("seq",1);
        //modify in document
        DbSeqUser counter = mongoOperations.findAndModify(query,update, FindAndModifyOptions.options().returnNew(true).upsert(true),
                DbSeqUser.class);
        return !Objects.isNull(counter)? counter.getSeq() :1;
    }
}
