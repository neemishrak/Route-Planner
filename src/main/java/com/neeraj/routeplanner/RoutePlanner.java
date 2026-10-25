package com.neeraj.routeplanner;

import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * Calculates timing and appointment-window feasibility for a single stop.
 *
 * <p>Travel time is supplied by the caller. This class does not currently
 * retrieve traffic estimates or choose the order of stops.
 *
 * <p>Current limitation: LocalTime arithmetic wraps at midnight. Calculations
 * that cross midnight are not yet detected and may produce incorrect results.
 */
@Service
public class RoutePlanner {

    /**
     * Returns the later of arrival and the appointment window's opening time.
     */
    public LocalTime calculateServiceStart(
            LocalTime arrival, LocalTime windowStart) {

        // Early arrivals must wait until the appointment window opens.
        if (arrival.isBefore(windowStart)) {
            return windowStart;
        }
        return arrival;
    }

    /**
     * Calculates departure by adding service duration to the service start.
     */
    public LocalTime calculateDeparture(
            LocalTime serviceStart, int durationMinutes) {

        return serviceStart.plusMinutes(durationMinutes);
    }

    /**
     * Checks the window's upper boundary.
     * Assumes service starts no earlier than the window opening.
     */
    public boolean fitsWindow(
            LocalTime departure, LocalTime windowEnd) {

        // Finishing exactly at the window end is permitted.
        return !departure.isAfter(windowEnd);
    }

    /**
     * Calculates arrival using the supplied travel duration.
     */
    public LocalTime calculateArrival(
            LocalTime previousDeparture, int travelMinutes) {

        return previousDeparture.plusMinutes(travelMinutes);
    }

    /**
     * Evaluates a stop, including travel, waiting, and service time.
     *
     * <p>Invalid inputs are rejected. Valid inputs that exceed the appointment
     * window return an evaluation with fitsWindow set to false.
     *
     * @param previousDeparture departure time from the preceding location
     * @param travelMinutes travel duration to this stop; zero is allowed
     * @param windowStart earliest permitted service start
     * @param windowEnd latest permitted service completion
     * @param durationMinutes service duration; zero is allowed
     * @return calculated arrival, service start, departure, and window fit
     * @throws IllegalArgumentException if a required time is missing,
     *         a duration is negative, or the window end precedes its start
     */
    public StopEvaluation evaluateStop(
            LocalTime previousDeparture,
            int travelMinutes,
            LocalTime windowStart,
            LocalTime windowEnd,
            int durationMinutes) {

        // Validate required times before invoking methods on them.
        if (windowEnd == null
                || previousDeparture == null
                || windowStart == null) {
            throw new IllegalArgumentException(
                    "Previous departure, window start and window end are required");
        }

        if (durationMinutes < 0) {
            throw new IllegalArgumentException(
                    "Service duration cannot be negative");
        }

        if (travelMinutes < 0) {
            throw new IllegalArgumentException(
                    "Travel time cannot be negative");
        }

        // Appointment windows currently describe a period within one day.
        if (windowEnd.isBefore(windowStart)) {
            throw new IllegalArgumentException(
                    "Window end Cannot be before window Start");
        }

        LocalTime arrival = calculateArrival(previousDeparture, travelMinutes);
        LocalTime serviceStart = calculateServiceStart(arrival, windowStart);
        LocalTime departure = calculateDeparture(serviceStart, durationMinutes);
        boolean fitsWindow = fitsWindow(departure, windowEnd);

        return new StopEvaluation(
                arrival, serviceStart, departure, fitsWindow);
    }
}