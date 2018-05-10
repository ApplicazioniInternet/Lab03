package it.polito.ai.lab03.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

    /*
     * non è necessaria la query per avere le Position
     * le prendo dall'oggetto User che ha il metodo
     */

    User findUserByUsernameAndPassword(String username, String password);

    /*
     * per controllare le logiche relative agli utenti
     * posso prendere gli utenti con un determinato ruolo
     */

    List<User> findUsersByRole(String role);

    /*
     * qualche metodo a caso giusto per testing
     */

    List<User> findUsersByLastAccessAfter(long access);

    List<User> findUsersByLastAccessBefore(long access);

    List<User> findUsersByLastAccessAfterAndLastAccessBefore(long access1, long access2);

}
