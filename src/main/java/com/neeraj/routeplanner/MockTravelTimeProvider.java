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

        /* Job to Supplies - Travel time Profile */

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


        /* Home  to Job - Travel time Profile */
        OriginDestKey homeToJobKey =
                new OriginDestKey("loc-home", "loc-job");

        TravelTimeInterval homeToJobInterval = new TravelTimeInterval(
                LocalDateTime.of(2026, 10, 26, 9, 0),
                LocalDateTime.of(2026, 10, 26, 10, 0),
                25);

        TravelTimeProfile homeToJobProfile =
                new TravelTimeProfile(List.of(homeToJobInterval));

        travelTimeProfileMap.put(homeToJobKey, homeToJobProfile);
        /* Supplies  to Home - Travel time Profile */
        OriginDestKey suppliesToHome =
                new OriginDestKey("loc-supplies", "loc-home");

        TravelTimeInterval suppliesToHomeInterval00 = new TravelTimeInterval(
                LocalDateTime.of(2026, 10, 26, 11, 0),
                LocalDateTime.of(2026, 10, 26, 11, 20),
                18);
        TravelTimeInterval suppliesToHomeInterval01 = new TravelTimeInterval(
                LocalDateTime.of(2026, 10, 26, 11, 20),
                LocalDateTime.of(2026, 10, 26, 11, 40),
                24);

        TravelTimeProfile suppliesToHomeProfile =
                new TravelTimeProfile(List.of(suppliesToHomeInterval00,suppliesToHomeInterval01));

        travelTimeProfileMap.put(suppliesToHome, suppliesToHomeProfile);
    }

    /** Based on the origin and Destination and Departure time,
     *we determine the travel time between the origin and destination
     * <p>Error handling based on inputs</p>
     * @param origin -Origin Location of the Journey
     * @param destination destination Location of the journey
     * @param departure - Time of departure
     * @return travel time based on the Origin-Destination Key and the duartion based on the time Interval
     * departure time falls in
     */
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
