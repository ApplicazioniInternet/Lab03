package it.polito.ai.lab03;

import it.polito.ai.lab03.repository.User;
import it.polito.ai.lab03.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("it.polito.ai.lab03")
public class Lab03Application implements CommandLineRunner {

    @Autowired
    private UserRepo repository;

    public static void main(String[] args) {
        SpringApplication.run(Lab03Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of user
        repository.save(new User("Bob", "$2a$04$u7AkEd1xISJiIMLbi0BKIeRRpkViEu6Hk0nxBe.LpMrsySFWb/IkG"));

    }
}
