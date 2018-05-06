package it.polito.ai.lab03.controller;

import it.polito.ai.lab03.dbinteractions.Position;
import it.polito.ai.lab03.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per tutte le posizioni. Dentro avrà i singoli metodi che mapperanno url più specifiche.
 * Al suo interno conterrà:
 *
 *      1) un PositionService, che è una classe appartenente allo strato di servizio, la quale sarà responsabile
 *         di fornire i metodi per potere accedere al data layer per riuscire ad ottenere i dati.
 */
@RestController
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Position> getAll(){ // Bisognerebbe creare la classe Position che risiede nel data layer.
        return positionService.getAll();
    }
}
