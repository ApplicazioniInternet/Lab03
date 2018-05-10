package it.polito.ai.lab03.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Questo service ci permette di tirare su dal database tutte le informazioni per uno specifico
 * utente.
 */
@Service
public class UserDetailsImpl implements UserDetailsService {
    /*@Autowired
    private UserRepository userRepository;*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*User user = userRepository.findByUsername(username);

        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(username);*/
        return null;
    }
}
