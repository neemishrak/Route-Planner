package com.neeraj.routeplanner;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MockTravelTimeProvider implements TravelTimeProvider {
    @Override
    public int getTravelMinutes(Location origin, Location destination, LocalDateTime departure){
        return 25;
    }
}
