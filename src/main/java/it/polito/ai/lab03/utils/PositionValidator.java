package it.polito.ai.lab03.utils;

import it.polito.ai.lab03.repository.PositionRepository;
import it.polito.ai.lab03.repository.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PositionValidator {

    private PositionRepository positionRepository;

    @Autowired
    public PositionValidator(PositionRepository pr) {
        this.positionRepository = pr;
    }

    public boolean isValidPosition(Position postedPosition, String username) {
        double speed = 0;
        long maxTimestamp = (System.currentTimeMillis() / 1000L);

        Position lastPosition = getLastPositionUser(username); // Prendo l'ultima posizione inseritaci dallo user

        if (lastPosition != null) {
            double distance = PositionUtils.getDistanceBetween(lastPosition, postedPosition); // Calcolo la distanza tra le due e poi la velocità
            speed = (distance * 1000) / (postedPosition.getTimestamp() - lastPosition.getTimestamp()) * 1000; // Perché il timestamp è in millisecondi!!! La formula ritorna i KM
        }

        /*
         * controllo velocità secondo parametri
         * contollo che lon e lat siano entro -180 e 180
         * controllo che il timestamp non sia inferiore al valore di quando abbaimo iniziato il progetto -> passato (si puù anche usare un vaalore più basso
         * controllo che il timestamp non sia maggiore al valore di quando faccio il controlllo -> futuro
         */
        return !(speed > Constants.MAX_SPEED) &&
                Double.compare(postedPosition.getLongitude(), Constants.validValueLowerBound) >= 0 &&
                Double.compare(postedPosition.getLongitude(), Constants.validValueUpperBound) <= 0 &&
                Double.compare(postedPosition.getLatitude(), Constants.validValueLowerBound) >= 0 &&
                Double.compare(postedPosition.getLatitude(), Constants.validValueUpperBound) <= 0 &&
                Long.compare(postedPosition.getTimestamp(), Constants.minTimestamp) >= 0 &&
                Long.compare(postedPosition.getTimestamp(), maxTimestamp) <= 0;
    }

    private Position getLastPositionUser(String username) {
        List<Position> positions = positionRepository.findPositionsByUserId(username);
        if (positions == null || positions.isEmpty())
            return null;

        long maxTimestamp = Collections.max(positions.stream()
                .map(Position::getTimestamp)
                .collect(Collectors.toList()));
        return positions.stream()
                .filter(p -> p.getTimestamp() == maxTimestamp)
                .findFirst()
                .orElse(null);
    }
}
