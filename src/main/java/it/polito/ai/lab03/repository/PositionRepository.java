package it.polito.ai.lab03.repository;

import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;

import java.util.List;

@SuppressWarnings("ALL")
public interface PositionRepository extends MongoRepository<Position, User> {

    List<Position> findPositionsByUserId(String userId);

    List<Position> findPositionsByLatitudeAndLongitude(@NonNull double latitude, @NonNull double longitude);

    List<Position> findPositionsByTimestampAfter(@NonNull long timestamp);

    List<Position> findPositionsByTimestampBefore(@NonNull long timestamp);

    List<Position> findPositionsByTimestampBeforeAndTimestampAfter(@NonNull long t1, @NonNull long t2);

    Position insert(@NonNull Position position);

    int countByLocationIsWithinAndTimestampAfterAndTimestampBefore(@NonNull GeoJsonPolygon area, @NonNull long t1, @NonNull long t2);

    List<Position> findByLocationIsWithinAndTimestampAfterAndTimestampBefore(@NonNull GeoJsonPolygon area, @NonNull long t1, @NonNull long t2);
}
