package it.polito.ai.lab03.service;

import org.springframework.context.annotation.Bean;

/**
 * Questa classe ha lo scopo di fornire dei metodi a Spring che restituiscano delle classi che noi vogliamo fare iniettare
 * dalle altre parti.
 */
public class ServiceFactory {

    @Bean
    public PositionService getPositionService(){
        // Di modo da potere iniettare un service per le position
        return new PositionService();
    }
}
