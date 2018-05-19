package it.polito.ai.lab03.controller;

import it.polito.ai.lab03.repository.Position;
import it.polito.ai.lab03.service.PositionService;
import it.polito.ai.lab03.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/secured/customer")
public class CustomerController {

    private PositionService positionService;
    private UserDetailsServiceImpl userService;

    @Autowired
    public CustomerController(PositionService ps, UserDetailsServiceImpl us) {
        this.positionService = ps;
        this.userService=us;
    }

    /*
    Ritorna la lista delle position acquistate
    Probabilmente meglio che nel db siano direttamente aggiunte allo user/customer visto che tali position
    saranno di user diversi e sarebbe un casino reperirle diversamente
     */
    @RequestMapping(
            path = "/{customerId}/positions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Position> getPositionBought(@PathVariable(value = "customerId") String username) {
        //Sarà tipo return userService.getPositionsBoughtForUser(username);
        return null;
    }

    /*
    Poichè il customer non penso possa postare posizioni, si potrebbe usare la POST sulla stessa
    URL /{customerId}/positions con un significato totalmente diverso. Cioè postare il poligono in cui
    si vuole sapere il numero do positions presenti per un eventuale acquisto (ache per non avere troppe URL diverse)
     */
    @RequestMapping(
            path = "/{customerId}/positions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    int getPositionQuantityInArea() {
        //In realtà qua il customerId non serve a nulla
        return 0;
    }


    @RequestMapping(
            path = "/{customerId}/transactions",
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
