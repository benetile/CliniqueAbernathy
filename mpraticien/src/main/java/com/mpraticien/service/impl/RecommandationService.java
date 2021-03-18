package com.mpraticien.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class RecommandationService {

   /*

    Mongo mongo = new Mongo("localhost", 27017);
    DB db = mongo.getDB("cliniqueDbMongo");
    DBCollection collection = db.getCollection("recommandation");

    public void updateRecommandation(int id, String observation) {

        BasicDBObject object = new BasicDBObject();
        object.append("$set",new BasicDBObject().append("observation",observation));

        BasicDBObject search = new BasicDBObject().append("_id",id);

        collection.update(search,object);
    }
*/
}
