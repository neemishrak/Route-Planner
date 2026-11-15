package com.neeraj.routeplanner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** A Route Plan is a List of Stops of 	an itinerary with a starting location,
 * planned departure, and stops in visit order.
 */
public class RoutePlan {
    private final UUID routePlanId;
    private Location origin;
    private LocalDateTime departure;
    private List<Stop> stops;

    public RoutePlan( Location origin, LocalDateTime departure, List<Stop> stops){
        this.origin = origin;
        this.departure = departure;
        this.stops = new ArrayList<>(stops);
        routePlanId = UUID.randomUUID();
    }

    public List<Stop> getStops() {
        return List.copyOf(stops);
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public Location getOrigin() {
        return origin;
    }

    public UUID getRoutePlanId() {
        return routePlanId;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public void setStops(List<Stop> stops) {
        this.stops = new ArrayList<>(stops);
    }

}
