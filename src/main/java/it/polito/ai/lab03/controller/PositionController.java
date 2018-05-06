package it.polito.ai.lab03.controller;

import it.polito.ai.lab03.repository.Position;
import it.polito.ai.lab03.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per tutte le posizioni. Dentro avrà i singoli metodi che mapperanno url più specifiche.
 * Dovrò ovviamente considerare solo le posizioni di un certo utente, che discriminerò andando a guardare
 * il token che mi arriverà nell'header della richiesta.
 * Al suo interno conterrà:
 *
 *      1) un PositionService, che è una classe appartenente allo strato di servizio, la quale sarà responsabile
 *         di fornire i metodi per potere accedere al data layer per riuscire ad ottenere i dati.
 */
@RestController
@RequestMapping("/positions")
public class PositionController {

    private PositionService positionService;

    @Autowired
    public PositionController(PositionService ps) {
        this.positionService = ps;
    }

    // Restituisce una collezione di positions
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Position> getAll(){ // Bisognerebbe creare la classe Position che risiede nel data layer.
        return positionService.getAll();
    }

    // Aggiungiamo una posizione a quelle che già abbiamo
    @RequestMapping(
            method = RequestMethod.POST
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addPosition(@Param(value = "position") Position position) {
    }

    // Credo sia così che funzioni la questione dei path. Questo si aggiunge a quello della classe
    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.GET
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public void getPosition(@PathVariable(value = "id") long positionId) {
    }

    @RequestMapping(
            path = "/{id}",
            method = RequestMethod.PUT
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public void updatePosition(@PathVariable(value = "id") long positionId) {
    }
}
