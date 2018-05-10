package it.polito.ai.lab03.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class UserRepoImpl implements UserRepoCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void updateRole(User user, User.Role role) {
        /*
        * uso approccio upsert, quindi se la query fallisce, crea l'oggetto
        * per crearlo occorre quindi mettere anche la password nella query
        * altrimenti inserisce uno User senza password
        */
        Query query = new Query(Criteria.where("username").is(user.getUsername()).and("password").is(user.getPassword()));
        Update update = new Update();
        update.set("role", role);
        mongoTemplate.upsert(query, update, User.class);
        /*
        User userTest = mongoTemplate.findOne(query, User.class);
        System.out.println("Username => " + userTest.getUsername() + "\nRole => " + userTest.getRole().name());
        */
    }

}
