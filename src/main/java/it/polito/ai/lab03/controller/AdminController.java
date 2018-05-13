package it.polito.ai.lab03.controller;

import it.polito.ai.lab03.repository.Position;
import it.polito.ai.lab03.repository.User;
import it.polito.ai.lab03.service.PositionService;
import it.polito.ai.lab03.service.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/secured/admin")
public class AdminController {

    private PositionService positionService;
    private UserDetailsServiceImpl userService;

    // Restituisce la collezione di tutti gli users
    @RequestMapping(
            path = "/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<User> getAllUsers() {
        return userService.getAll();
    }

    // Restituisce la collezione di tutte le positions di tutti gli utenti
    @RequestMapping(
            path = "/positions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Position> getAllPosition() { // Bisognerebbe creare la classe Position che risiede nel data layer.
        return positionService.getAll();
    }

    // Restituisce la collezione di positions per lo user specifico
    @RequestMapping(
            path = "users/{id}/position",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Position> getAllPositionForUser(@PathVariable(value = "id") String user) {
        return null;
    }

}
