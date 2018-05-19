package it.polito.ai.lab03.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "positions")
public class Position {

    @Id
    private String id;

    private double latitude;
    private double longitude;
    private long timestamp;
    private String userId;

    /*
    Per le query geografiche non si possono usare long e lat come due double ma servono tipi di geoJson
    Probabilmente long e lat si potrebbero togliere
     */
    @GeoSpatialIndexed(type=GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return Double.compare(position.getLatitude(), getLatitude()) == 0 &&
                Double.compare(position.getLongitude(), getLongitude()) == 0 &&
                getTimestamp() == position.getTimestamp() &&
                Objects.equals(getUserId(), position.getUserId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getLatitude(), getLongitude(), getTimestamp(), getUserId());
    }

    private String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Position() {
    }

    public Position(double latitude, double longitude, long timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.location = new GeoJsonPoint(longitude, latitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }
}
