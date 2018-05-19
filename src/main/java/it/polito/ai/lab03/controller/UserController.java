package it.polito.ai.lab03.controller;

import it.polito.ai.lab03.repository.Position;
import it.polito.ai.lab03.repository.User;
import it.polito.ai.lab03.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secured/users")
public class UserController {

    private PositionService positionService;

    @Autowired
    public UserController(PositionService ps) {
        this.positionService = ps;
    }

    // Restituisce una collezione di positions
    @RequestMapping(
            path = "/{userId}/positions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Position> getAll(@PathVariable(value = "userId") String username) {
        return positionService.getPositionsForUser(username);
    }

    // Aggiungiamo una posizione a quelle che già abbiamo
    @RequestMapping(
            path = "/{userId}/positions",
            method = RequestMethod.POST
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addPosition(@PathVariable(value = "userId") String username, @RequestBody Position position, @AuthenticationPrincipal User principal) {
        positionService.insertPosition(position);
    }

    // Credo sia così che funzioni la questione dei path. Questo si aggiunge a quello della classe
    @RequestMapping(
            path = "/{userId}/positions/{id}",
            method = RequestMethod.GET
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public void getPosition(@PathVariable(value = "userId") String username, @PathVariable(value = "id") long positionId) {
    }

    @RequestMapping(
            path = "/{userId}/positions/{id}",
            method = RequestMethod.PUT
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public void updatePosition(@PathVariable(value = "userId") String username, @PathVariable(value = "id") long positionId) {
    }
}
