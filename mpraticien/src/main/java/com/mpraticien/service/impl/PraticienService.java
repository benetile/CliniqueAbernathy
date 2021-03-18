package com.mpraticien.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mpraticien.model.Praticien;
import org.springframework.stereotype.Service;

@Service
public class PraticienService {

   /* Mongo mongo = new Mongo("localhost", 27017);
    DB db = mongo.getDB("cliniqueDbMongo");
    DBCollection collection = db.getCollection("recommandation");

    public void updatePraticien(int id, Praticien praticien) {

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.append("$set",
                new BasicDBObject().append("firstName",praticien.getFirstName()).
                        append("lastName",praticien.getLastName())
                        .append("speciality",praticien.getSpeciality())
                        .append("sex",praticien.getSex()).append("phone",praticien.getPhone()));

        BasicDBObject search = new BasicDBObject().append("_id",id);

        collection.update(search,updateObject);
    }*/
}
