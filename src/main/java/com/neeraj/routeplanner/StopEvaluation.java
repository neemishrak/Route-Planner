package com.neeraj.routeplanner;

import java.time.LocalDateTime;

public class StopEvaluation {
    private Stop stop;
    private LocalDateTime arrival;
    private LocalDateTime departure;
    private LocalDateTime serviceStart;
    private boolean fitsWindow;
    //getters and Setters//

    public StopEvaluation(Stop stop, LocalDateTime arrival, LocalDateTime serviceStart,
                          LocalDateTime departure, boolean fitsWindow) {
        this.stop = stop;
        this.arrival = arrival;
        this.departure = departure;
        this.fitsWindow = fitsWindow;
        this.serviceStart = serviceStart;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public LocalDateTime getServiceStart() {
        return serviceStart;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public void setServiceStart(LocalDateTime serviceStart) {
        this.serviceStart = serviceStart;
    }

    public void setFitsWindow(boolean fitsWindow) {
        this.fitsWindow = fitsWindow;
    }

    public boolean isFitsWindow() {
        return fitsWindow;
    }
    public Stop getStop() {
        return stop;
    }
}
