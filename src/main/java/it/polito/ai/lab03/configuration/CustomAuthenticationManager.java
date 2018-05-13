package it.polito.ai.lab03.configuration;

import it.polito.ai.lab03.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Questa classe ha il compito di mettere dentro l'oggetto Authentication le credenziali e il ruolo
 * ricevuti.
 * L'oggetto Authentication sarà usato dal client per andare a richiedere il token.
 */

public class CustomAuthenticationManager implements org.springframework.security.authentication.AuthenticationManager {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userRepo.findUserByUsername(authentication.getName());
        String password = authentication.getCredentials().toString();

        if (passwordEncoder.matches(userDetails.getPassword(), password))
            authentication.setAuthenticated(true);
        else
            authentication.setAuthenticated(false);
        return authentication;
    }
}
