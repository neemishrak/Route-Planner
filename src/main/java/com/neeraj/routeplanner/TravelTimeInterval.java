package com.neeraj.routeplanner;

import java.time.LocalDateTime;

/** Travel duration for departures within a specific date-time interval. */

public class TravelTimeInterval {
    private LocalDateTime departureWindowStart;
    private LocalDateTime departureWindowEnd;
    private int travelDurationMinutes;

    public TravelTimeInterval(LocalDateTime departureWindowStart, LocalDateTime departureWindowEnd, int travelDurationMinutes){
        this.departureWindowEnd = departureWindowEnd;
        this. departureWindowStart = departureWindowStart;
        this.travelDurationMinutes = travelDurationMinutes;

    }

    public void setDepartureWindowStart(LocalDateTime departureWindowStart) {
        this.departureWindowStart = departureWindowStart;
    }

    public void setDepartureWindowEnd(LocalDateTime departureWindowEnd) {
        this.departureWindowEnd = departureWindowEnd;
    }

    public void setTravelDurationMinutes(int travelDurationMinutes) {
        this.travelDurationMinutes = travelDurationMinutes;
    }

    public LocalDateTime getDepartureWindowStart() {
        return departureWindowStart;
    }

    public LocalDateTime getDepartureWindowEnd() {
        return departureWindowEnd;
    }

    public int getTravelDurationMinutes() {
        return travelDurationMinutes;
    }
    public boolean containsDepartureWindow(LocalDateTime departure){
        return !departure.isBefore(departureWindowStart) && departure.isBefore(departureWindowEnd);
    }
}
