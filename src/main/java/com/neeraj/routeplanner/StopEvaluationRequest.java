package com.neeraj.routeplanner;

import java.time.LocalTime;

public class StopEvaluationRequest {
    LocalTime previousDeparture;
    int travelMinutes;
    LocalTime windowStart;
    LocalTime windowEnd;
    int durationMinutes;

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public int getTravelMinutes() {
        return travelMinutes;
    }

    public LocalTime getPreviousDeparture() {
        return previousDeparture;
    }

    public LocalTime getWindowEnd() {
        return windowEnd;
    }

    public LocalTime getWindowStart() {
        return windowStart;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setPreviousDeparture(LocalTime previousDeparture) {
        this.previousDeparture = previousDeparture;
    }

    public void setTravelMinutes(int travelMinutes) {
        this.travelMinutes = travelMinutes;
    }

    public void setWindowEnd(LocalTime windowEnd) {
        this.windowEnd = windowEnd;
    }

    public void setWindowStart(LocalTime windowStart) {
        this.windowStart = windowStart;
    }
}
