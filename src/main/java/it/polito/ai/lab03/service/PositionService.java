package it.polito.ai.lab03.service;

import it.polito.ai.lab03.repository.Position;
import it.polito.ai.lab03.repository.PositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    PositionRepo positionRepository;

    public PositionService(){

    }

    public List<Position> getAll(){
        return null;
    }
}
