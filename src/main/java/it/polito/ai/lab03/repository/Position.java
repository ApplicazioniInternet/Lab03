package it.polito.ai.lab03.repository;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "positions")
public class Position {

    private double latitude;
    private double longitude;
    private long timestamp;
    private String userId;

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

    public String getUserId() {

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
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
