package it.polito.ai.lab03.configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Questa classe ha il compito di mettere dentro l'oggetto Authentication le credenziali e il ruolo
 * ricevuti.
 * L'oggetto Authentication sarà usato dal client per andare a richiedere il token.
 */
public class CustomAuthenticationManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }
}
