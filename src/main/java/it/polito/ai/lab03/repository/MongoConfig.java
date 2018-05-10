package it.polito.ai.lab03.repository;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    public @Bean Mongo mongo() throws Exception {
        return new Mongo(new ServerAddress("localhost", 27017));
    }

    public @Bean MongoTemplate mongoTemplate() throws Exception{
        return new MongoTemplate((MongoClient) mongo(), "bugged-group");
    }

}
