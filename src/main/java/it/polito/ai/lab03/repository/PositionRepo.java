package it.polito.ai.lab03.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PositionRepo extends MongoRepository<Position, User> {

    List<Position> findPositionsByLatitudeAndAndLongitude(double latitude, double longitude);

    List<Position> findPositionsByTimestampAfter(long timestamp);

    List<Position> findPositionsByTimestampBefore(long timestamp);

    List<Position> findPositionsByTimestampBeforeAndTimestampAfter(long t1, long t2);
}
