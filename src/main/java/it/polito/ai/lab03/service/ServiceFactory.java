package it.polito.ai.lab03.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Questa classe ha lo scopo di fornire dei metodi a Spring che restituiscano delle classi che noi vogliamo fare iniettare
 * dalle altre parti.
 */
@Configuration
public class ServiceFactory {

    @Bean(name="PositionService")
    public PositionService getPositionService(){
        // Di modo da potere iniettare un service per le position
        return new PositionService();
    }
}
