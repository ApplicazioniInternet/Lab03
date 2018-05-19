package it.polito.ai.lab03.controller;

import it.polito.ai.lab03.repository.Position;
import it.polito.ai.lab03.service.PositionService;
import it.polito.ai.lab03.utils.PositionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.NotAcceptableStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/secured/user")
public class UserController {

    private PositionService positionService;
    private PositionValidator positionValidator;

    @Autowired
    public UserController(PositionService ps, PositionValidator pv) {
        this.positionService = ps;
        this.positionValidator = pv;
    }

    /**
     * Funzione per prendere tutte le posizioni dell'utente con il dato token
     *
     * @param principal --> le informazioni sul token che è arrivato con la richiesta
     * @return List<Position> --> lista delle posizioni dell'utente
     */
    @RequestMapping(
            path = "/positions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Position> getAll(Principal principal) {
        return positionService.getPositionsForUser(principal.getName());
    }

    /**
     * Funzione per aggiungere una posizione all'utente che possiede il token che ci viene dato con
     * la richiesta
     *
     * @param position  --> è la posizione che vuole aggiungere l'utente
     * @param principal --> le informazioni sul token che è arrivato con la richiesta
     */
    @RequestMapping(
            path = "/positions",
            method = RequestMethod.POST
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addPosition(@RequestBody Position position, Principal principal) {
        position.setUserId(principal.getName());
        if (positionValidator.isValidPosition(position, principal.getName()))
            positionService.insertPosition(position);
        else
            throw new NotAcceptableStatusException("La posizione inserita non è valida.");
    }
}
