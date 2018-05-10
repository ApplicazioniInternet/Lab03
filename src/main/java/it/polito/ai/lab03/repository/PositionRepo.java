package it.polito.ai.lab03.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PositionRepo extends MongoRepository<Position, User> {

    public List<Position> findPositionsByLatitudeAndAndLongitude(double latitude, double longitude);
    public List<Position> findPositionsByTimestampAfter(long timestamp);
    public List<Position> findPositionsByTimestampBefore(long timestamp);
    public List<Position> findPositionsByTimestampBeforeAndTimestampAfter(long t1, long t2);

}
