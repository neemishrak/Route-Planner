package com.neeraj.routeplanner;

import java.time.LocalDateTime;
import java.time.LocalDateTime;

public class StopEvaluationRequest {
    LocalDateTime previousDeparture;
    int travelMinutes;
    LocalDateTime windowStart;
    LocalDateTime windowEnd;
    int durationMinutes;

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public int getTravelMinutes() {
        return travelMinutes;
    }

    public LocalDateTime getPreviousDeparture() {
        return previousDeparture;
    }

    public LocalDateTime getWindowEnd() {
        return windowEnd;
    }

    public LocalDateTime getWindowStart() {
        return windowStart;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setPreviousDeparture(LocalDateTime previousDeparture) {
        this.previousDeparture = previousDeparture;
    }

    public void setTravelMinutes(int travelMinutes) {
        this.travelMinutes = travelMinutes;
    }

    public void setWindowEnd(LocalDateTime windowEnd) {
        this.windowEnd = windowEnd;
    }

    public void setWindowStart(LocalDateTime windowStart) {
        this.windowStart = windowStart;
    }
}
