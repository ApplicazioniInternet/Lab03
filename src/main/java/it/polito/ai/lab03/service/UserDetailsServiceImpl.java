package it.polito.ai.lab03.service;

import it.polito.ai.lab03.repository.User;
import it.polito.ai.lab03.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Questo service ci permette di tirare su dal database tutte le informazioni per uno specifico
 * utente.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(username);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
