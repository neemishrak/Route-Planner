package com.neeraj.routeplanner;

import java.time.LocalTime;

public class StopEvaluation {
    private LocalTime arrival;
    private LocalTime departure;
    private LocalTime serviceStart;
    private boolean fitsWindow;
    //getters and Setters//

    public StopEvaluation(LocalTime arrival, LocalTime serviceStart,
                          LocalTime departure, boolean fitsWindow) {
        this.arrival = arrival;
        this.departure = departure;
        this.fitsWindow = fitsWindow;
        this.serviceStart = serviceStart;
    }

    public LocalTime getArrival() {
        return arrival;
    }

    public LocalTime getDeparture() {
        return departure;
    }

    public LocalTime getServiceStart() {
        return serviceStart;
    }

    public void setArrival(LocalTime arrival) {
        this.arrival = arrival;
    }

    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }

    public void setServiceStart(LocalTime serviceStart) {
        this.serviceStart = serviceStart;
    }

    public void setFitsWindow(boolean fitsWindow) {
        this.fitsWindow = fitsWindow;
    }

    public boolean isFitsWindow() {
        return fitsWindow;
    }
}
