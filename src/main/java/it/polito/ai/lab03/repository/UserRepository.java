package it.polito.ai.lab03.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

/**
 * Questa interfaccia sarà quella che estenderà un'interfaccia che ci permetterà di fare le query
 * direttamente a mongoDB.
 *
 * @Query --> per specificare la query
 * @Param --> per specificare i parametri che riceve la funzione
 */

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
