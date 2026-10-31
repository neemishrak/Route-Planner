package com.neeraj.routeplanner;

import java.time.LocalDateTime;

public class StopEvaluationRequest {
    Stop stop;
    LocalDateTime previousDeparture;
    int travelMinutes;



    public int getTravelMinutes() {
        return travelMinutes;
    }

    public LocalDateTime getPreviousDeparture() {
        return previousDeparture;
    }


    public void setPreviousDeparture(LocalDateTime previousDeparture) {
        this.previousDeparture = previousDeparture;
    }

    public void setTravelMinutes(int travelMinutes) {
        this.travelMinutes = travelMinutes;
    }


    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public Stop getStop() {
        return stop;
    }
}
