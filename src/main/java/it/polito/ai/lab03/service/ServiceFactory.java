package it.polito.ai.lab03.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Questa classe ha lo scopo di fornire dei metodi a Spring che restituiscano delle classi che noi vogliamo fare iniettare
 * dalle altre parti.
 *
 * È annotata come Configuration perché questa mi serve per registrare tutti i miei bean nella Bean Factory di Spring prima che
 * tutto venga creato, così Spring sa quali oggetti possono essere creati e riesce a fare girare l'autowiring correttamente.
 */
@Configuration
public class ServiceFactory {

    @Bean
    public PositionService getPositionService(){
        // Di modo da potere iniettare un service per le position
        return new PositionService();
    }
}
