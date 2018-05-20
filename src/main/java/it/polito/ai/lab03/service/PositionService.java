package it.polito.ai.lab03.service;

import it.polito.ai.lab03.repository.PositionRepository;
import it.polito.ai.lab03.repository.TransactionRepository;
import it.polito.ai.lab03.repository.model.AreaRequest;
import it.polito.ai.lab03.repository.model.Position;
import it.polito.ai.lab03.repository.model.Transaction;
import it.polito.ai.lab03.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PositionService {

    private PositionRepository positionRepository;
    private TransactionRepository transactionRepository;

    @Autowired
    public PositionService(PositionRepository pr, TransactionRepository tr) {
        this.positionRepository = pr;
        this.transactionRepository=tr;

    }

    public List<Position> getAll(){
        return positionRepository.findAll();
    }

    public List<Position> getPositionsForUser(String user) {
        return positionRepository.findPositionsByUserId(user);
    }

    public void insertPosition(Position position) {
        positionRepository.insert(position);
    }

    public List<Position> getPositionsInArea(AreaRequest locationRequest) {
        return positionRepository
                .findByLocationIsWithinAndTimestampAfterAndTimestampBefore(
                        locationRequest.getPolygon(),
                        locationRequest.getTimestampBefore(),
                        locationRequest.getTimestampAfter()
                );
    }

    public int getNumberPositionsInArea(AreaRequest locationRequest) {
        return positionRepository
                .countByLocationIsWithinAndTimestampAfterAndTimestampBefore(
                        locationRequest.getPolygon(),
                        locationRequest.getTimestampBefore(),
                        locationRequest.getTimestampAfter()
                );
    }

    public List<Position> buyPositionsInArea(AreaRequest locationRequest, String buyer){
        List<Position> positions = getPositionsInArea(locationRequest);
        //Divido la lista di posizioni da acquistare in liste divise per owner
        Map<String, List<Position>> ownerPosition = positions.stream()
                .collect(Collectors.groupingBy(Position::getUserId, Collectors.toList()));

        //Per ogni utente diverso che possiede le position che voglio comprare devo fare una transazione
        for(String owner : ownerPosition.keySet()){
            /*
            QUI ANDREBBE RICHIAMATO IL METODO PER FARE IL PAGAMENTO !!!
            Se va storto o eccezione o ritornare null da gestire nel controller per settare status code
             */

            //Attualmente il prezzo penso sia sensato che sia costante * numero di posizioni acquistate
            double pricePayd = Constants.priceSinglePosition * ownerPosition.get(owner).size();
            //Costruzione della transazione (id autogenerato dal DB)
            Transaction transaction = new Transaction(buyer, owner, ownerPosition.get(owner), pricePayd, (System.currentTimeMillis() / 1000L));
            transactionRepository.insert(transaction);
            /*
            Una volta che la transazione va a buon fine ed è stata registrata si possono considerare acquistate le positions
            e vanno aggiunte come comprate dal customer...
             */
        }
        return positions;
    }
}
