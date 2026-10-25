package com.neeraj.routeplanner;
/**
 * Represents a physical location that may be referenced by a stop
 * or used as a route's start or end.
 *
 * <p>Coordinates are supplied manually for now. A map provider
 * may supply them in a later version.
 */
public class Location {
    private String id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }
}
