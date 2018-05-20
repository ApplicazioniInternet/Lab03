package it.polito.ai.lab03.service;

import it.polito.ai.lab03.repository.PositionRepository;
import it.polito.ai.lab03.repository.model.AreaRequest;
import it.polito.ai.lab03.repository.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    private PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository pr) {
        this.positionRepository = pr;

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

    public int countPositionInArea(GeoJsonPolygon area, long t1, long t2){
        return positionRepository.countByLocationIsWithinAndTimestampAfterAndTimestampBefore(area, t1, t2);
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
}
