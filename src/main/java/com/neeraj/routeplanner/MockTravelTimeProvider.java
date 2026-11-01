package com.neeraj.routeplanner;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class MockTravelTimeProvider implements TravelTimeProvider {
    @Override
    public int getTravelMinutes(Location origin, Location destination, LocalDateTime departure){
        if (origin == null) {
            throw new IllegalArgumentException("Origin is required");
        }
        if (destination == null) {
            throw new IllegalArgumentException("destination is required");
        }
        if (departure == null) {
            throw new IllegalArgumentException("departure is required");
        }
        System.out.println(
                origin.getId() + " -> " + destination.getId()
                        + " departing " + departure);

        if ("loc-home".equals(origin.getId())
                && "loc-job".equals(destination.getId())) {
            return 25;
        }
        if ("loc-supplies".equals(destination.getId()) && "loc-job".equals(origin.getId()))
                {
            return departure.toLocalTime().isBefore(LocalTime.of(10, 30)) ? 20 :30;
        } else throw new IllegalArgumentException("No Mock travel time for this pair");


    }
}
