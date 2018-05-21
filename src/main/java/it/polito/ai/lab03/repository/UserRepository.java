package it.polito.ai.lab03.repository;

import it.polito.ai.lab03.repository.model.Position;
import it.polito.ai.lab03.repository.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);

    void updateByUsernamePositions(String username, List<Position> positions);
}
