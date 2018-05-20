package it.polito.ai.lab03.controller;

import it.polito.ai.lab03.repository.model.AreaRequest;
import it.polito.ai.lab03.repository.model.Position;
import it.polito.ai.lab03.service.PositionService;
import it.polito.ai.lab03.service.UserDetailsServiceImpl;
import it.polito.ai.lab03.utils.IAuthorizationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/secured/customer")
public class CustomerController {

    private PositionService positionService;
    private IAuthorizationFacade authorizationFacade;

    @Autowired
    public CustomerController(PositionService ps, UserDetailsServiceImpl us, IAuthorizationFacade iaf) {
        this.positionService = ps;
        this.authorizationFacade = iaf;
    }

    /**
     * Ritorna la lista delle position acquistate
     *
     * @return List<Position> --> lista delle posizioni acquistate da un certo customer
     */
    @RequestMapping(
            path = "/positions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Position> getPositionBought() {
        String username = authorizationFacade.getAuthorization().getPrincipal().toString();
        return positionService.getPositionsForUser(username);
    }

    /**
     * Questo metodo poichè ritorna una lista di posizioni dato un poligono, deve essere l'acquisto
     * Due possibili ResponseStatus a seconda che lácquisto sia andato a buon fine o meno
     */
    @RequestMapping(
            path = "/positions/list",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    List<Position> getPositionInArea(@RequestBody AreaRequest locationRequest) {
        //Ricavo l'username per passarlo al service layer in modo da settare il buyer nella transazione
        String username = authorizationFacade.getAuthorization().getPrincipal().toString();
        /*
        Bisognerebbe verificare se la transazione è andata a buon fine e in caso contrario dare un messaggio di errore.
        Possibili errori:
        - area non valida (bad request)
        - nessuna posizione in quell'area (si può anche tornare un ok e lista vuota)
        - soldi non sufficienti per transazione (che errore? forse forbidden?)
        - transazione fallita (internal server error)
         */

        //return positionService.getPositionsInArea(locationRequest);
        return positionService.buyPositionsInArea(locationRequest, username);
    }

    @RequestMapping(
            path = "/positions/count",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    int getPositionQuantityInArea(@RequestBody AreaRequest locationRequest) {
        return positionService.getNumberPositionsInArea(locationRequest);
    }


    @RequestMapping(
            path = "/transactions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void buyPosition(){
        /*
        Immagino che ci sarà una collection di transazioni e se ne aggiunge una
        O ritorna void o l'id autogenerato della transazione aggiunta
        Cosa contiene il body della richiesta?:
            Le coordinate dei punti che delimitano il poligono in cui compro le posizioni
            Forse dalla consegna anche il limite temporale che mi interessa (before e after)
            Magari il timestamp della transazione
            Altro?
        Il metodo invocato deve poi virtualmente fare il pagamento (non dovrebbe essere da implementare)
        Pagamento e modifica del customer (aggiungendogli le posizioni acuistate) dovrebbe avvenire in transazione
        */
    }

    /*
    Oltre ai metodi già elencati si possono e forse devono aggiungere:
        - getAllTransactions
        - getSingleTransaction
        - getSingleBoughtPopsition
     */
}
