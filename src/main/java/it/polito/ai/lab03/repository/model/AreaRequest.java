package it.polito.ai.lab03.repository.model;

import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.util.Objects;

public class AreaRequest {
    private GeoJsonPolygon polygon;
    private long timestampBefore;
    private long timestampAfter;

    public GeoJsonPolygon getPolygon() {
        return polygon;
    }

    public void setPolygon(GeoJsonPolygon polygon) {
        this.polygon = polygon;
    }

    public long getTimestampBefore() {
        return timestampBefore;
    }

    public void setTimestampBefore(long timestampBefore) {
        this.timestampBefore = timestampBefore;
    }

    public long getTimestampAfter() {
        return timestampAfter;
    }

    public void setTimestampAfter(long timestampAfter) {
        this.timestampAfter = timestampAfter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AreaRequest)) return false;
        AreaRequest that = (AreaRequest) o;
        return getTimestampBefore() == that.getTimestampBefore() &&
                getTimestampAfter() == that.getTimestampAfter() &&
                Objects.equals(getPolygon(), that.getPolygon());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPolygon(), getTimestampBefore(), getTimestampAfter());
    }
}
