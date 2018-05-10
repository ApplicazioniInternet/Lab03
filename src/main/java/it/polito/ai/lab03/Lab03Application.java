package it.polito.ai.lab03;

import it.polito.ai.lab03.repository.User;
import it.polito.ai.lab03.repository.UserRepo;
import it.polito.ai.lab03.repository.UserRepoCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("it.polito.ai.lab03.service")
public class Lab03Application {

    @Autowired
    private UserRepo ur;

    @Autowired
    private UserRepoCustom urc;

    public static void main(String[] args) {
        SpringApplication.run(Lab03Application.class, args);
    }

    public void run(String... args) throws Exception {

        ur.deleteAll();

        ur.save(new User("Grazia", "Password1", User.Role.ADMIN));
        ur.save(new User("Graziella", "Password2", User.Role.CUSTOMER));
        ur.save(new User("Graziearcazzo", "Password3", User.Role.ADMIN));

        for (User user : ur.findAll()) {
            System.out.println(user);
        }
        System.out.println(ur.findUserByUsernameAndPassword("Graziearcazzo", "Password3"));
        for (User user : ur.findUsersByRole(User.Role.ADMIN.toString())) {
            System.out.println(user);
        }

        User u = ur.findUserByUsernameAndPassword("Graziearcazzo", "Password3");
        urc.updateRole(u, User.Role.USER);

    }

}
