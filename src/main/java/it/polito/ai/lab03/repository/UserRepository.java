package it.polito.ai.lab03.repository;

import it.polito.ai.lab03.repository.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /*
     * non è necessaria la query per avere le Position
     * le prendo dall'oggetto User che ha il metodo
     */

    //User findUserByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
}
