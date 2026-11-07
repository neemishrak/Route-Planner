package com.neeraj.routeplanner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TravelTimeProfile {
    private List<TravelTimeInterval> intervals;


    public TravelTimeProfile(List<TravelTimeInterval> intervals) {
        this.intervals = new ArrayList<>(intervals);

    }

    public int getTravelMinutes(LocalDateTime departure) {
        for (TravelTimeInterval interval : intervals) {
            if (interval.containsDepartureWindow(departure)) {
                return interval.getTravelDurationMinutes();
            }
        }
        throw new IllegalArgumentException(
                "No travel interval for this departure");
    }
}