package com.neeraj.routeplanner;


import java.time.LocalDateTime;

public interface TravelTimeProvider {
     int getTravelMinutes(
            Location origin,
            Location destination,
            LocalDateTime departure
    );

}
