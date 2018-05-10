package it.polito.ai.lab03.configuration;

import it.polito.ai.lab03.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Conterrà:
 * 1) una classe per prendere i dati delle credenziali dal pool degli utenti;
 * 2) un authentication manager in grado di controllare le credenziali e settare il ruolo opportuno.
 *
 * Queste classi poi saranno restituite tramite metodi annotati con @Bean
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerOauth2Configuration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private UserDetailsImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public TokenStore tokenStore() {
        // Per fare capire a Spring che deve usare il db per i tokens
        return null;
    }

    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
        // Così ci pensa lui per i fatti suoi
        return new OAuth2AccessDeniedHandler();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()) // Usa il token dal database
                .authenticationManager(authenticationManager) // Usa il mio custom authentication manager per autenticare gli utenti
                .userDetailsService(userDetailsService); // Come data source prendi quella da userDetails service
    }

    // BOOOOH
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
    }
}
