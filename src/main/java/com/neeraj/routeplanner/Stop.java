package com.neeraj.routeplanner;
/** Represents a Stop along a Route */

public class Stop {
    private String id;
    private String name;
    private StopType stopType;
    private TimeWindow timeWindow;
    private Location location;
    private int durationMinutes;
    private boolean required;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isRequired() {
        return required;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public Location getLocation() {
        return location;
    }

    public StopType getStopType() {
        return stopType;
    }

    public String getId() {
        return id;
    }

    public TimeWindow getTimeWindow() {
        return timeWindow;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public void setStopType(StopType stopType) {
        this.stopType = stopType;
    }

    public void setTimeWindow(TimeWindow timeWindow) {
        this.timeWindow = timeWindow;
    }

}
