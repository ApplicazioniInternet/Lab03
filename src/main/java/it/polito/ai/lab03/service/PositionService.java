package it.polito.ai.lab03.service;

import it.polito.ai.lab03.repository.Position;
import it.polito.ai.lab03.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
