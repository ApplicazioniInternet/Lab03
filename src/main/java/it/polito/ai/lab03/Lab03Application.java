package it.polito.ai.lab03;

import it.polito.ai.lab03.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("it.polito.ai.lab03")
public class Lab03Application {

    @Autowired
    private UserRepo repository;

    public static void main(String[] args) {
        SpringApplication.run(Lab03Application.class, args);
    }
}
