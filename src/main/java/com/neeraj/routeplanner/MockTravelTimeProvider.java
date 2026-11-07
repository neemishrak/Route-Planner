package com.neeraj.routeplanner;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MockTravelTimeProvider implements TravelTimeProvider {

    private final Map<OriginDestKey, TravelTimeProfile> travelTimeProfileMap = new HashMap<>();
    public MockTravelTimeProvider() {
        OriginDestKey jobToSuppliesKey =
                new OriginDestKey("loc-job", "loc-supplies");

        TravelTimeInterval jobToSuppliesEarlyInterval = new TravelTimeInterval(
                LocalDateTime.of(2026, 10, 26, 10, 20),
                LocalDateTime.of(2026, 10, 26, 10, 30),
                20);

        TravelTimeInterval jobToSuppliesLaterInterval = new TravelTimeInterval(
                LocalDateTime.of(2026, 10, 26, 10, 30),
                LocalDateTime.of(2026, 10, 26, 10, 40),
                30);

        TravelTimeProfile jobToSuppliesProfile = new TravelTimeProfile(
                List.of(jobToSuppliesEarlyInterval, jobToSuppliesLaterInterval));

        travelTimeProfileMap.put(jobToSuppliesKey, jobToSuppliesProfile);

        OriginDestKey homeToJobKey =
                new OriginDestKey("loc-home", "loc-job");

        TravelTimeInterval homeToJobInterval = new TravelTimeInterval(
                LocalDateTime.of(2026, 10, 26, 9, 0),
                LocalDateTime.of(2026, 10, 26, 10, 0),
                25);

        TravelTimeProfile homeToJobProfile =
                new TravelTimeProfile(List.of(homeToJobInterval));

        travelTimeProfileMap.put(homeToJobKey, homeToJobProfile);
    }
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
        OriginDestKey key = new OriginDestKey(origin.getId(), destination.getId());
        TravelTimeProfile profile = travelTimeProfileMap.get(key);
        if (profile == null) throw new IllegalArgumentException("No mock travel time for this pair");
        return profile.getTravelMinutes(departure);
    }
}
