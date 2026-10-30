package com.neeraj.routeplanner;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Calculates timing and appointment-window feasibility for a single stop.
 *
 * <p>Travel time is supplied by the caller. This class does not currently
 * retrieve traffic estimates or choose the order of stops.
 *
 * <p>Uses local dates and times in one shared timezone; timezone conversion is not supported.
 */
@Service
public class RoutePlanner {

    /**
     * Returns the latter of arrival and the appointment window's opening time.
     */
    public LocalDateTime calculateServiceStart(
            LocalDateTime arrival, LocalDateTime windowStart) {

        // Early arrivals must wait until the appointment window opens.
        if (arrival.isBefore(windowStart)) {
            return windowStart;
        }
        return arrival;
    }

    /**
     * Calculates departure by adding service duration to the service start.
     */
    public LocalDateTime calculateDeparture(
            LocalDateTime serviceStart, int durationMinutes) {

        return serviceStart.plusMinutes(durationMinutes);
    }

    /**
     * Checks the window's upper boundary.
     * Assumes service starts no earlier than the window opening.
     */
    public boolean fitsWindow(
            LocalDateTime departure, LocalDateTime windowEnd) {

        // Finishing exactly at the window end is permitted.
        return !departure.isAfter(windowEnd);
    }

    /**
     * Calculates arrival using the supplied travel duration.
     */
    public LocalDateTime calculateArrival(
            LocalDateTime previousDeparture, int travelMinutes) {

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
            LocalDateTime previousDeparture,
            int travelMinutes,
            LocalDateTime windowStart,
            LocalDateTime windowEnd,
            int durationMinutes) {

        // Validate required times before invoking methods on them.
        if (previousDeparture == null) {
            throw new IllegalArgumentException("Previous departure is required");
        }

        if (durationMinutes < 0) {
            throw new IllegalArgumentException(
                    "Service duration cannot be negative");
        }

        if (travelMinutes < 0) {
            throw new IllegalArgumentException(
                    "Travel time cannot be negative");
        }
        // A window must be completely supplied or completely absent.
        if ((windowStart == null && windowEnd != null)
                || (windowStart != null && windowEnd == null)) {
            throw new IllegalArgumentException(
                    "Window start and window end must be supplied together");
        }
        // Without a window, service starts immediately on arrival.
        if (windowStart == null) {
            LocalDateTime arrival = calculateArrival(previousDeparture, travelMinutes);
            LocalDateTime departure = calculateDeparture(arrival, durationMinutes);

            return new StopEvaluation(arrival, arrival, departure, true);
        }

        // Appointment windows currently describe a period .
        if (windowEnd.isBefore(windowStart)) {
            throw new IllegalArgumentException(
                    "Window end Cannot be before window Start");
        }

        LocalDateTime arrival = calculateArrival(previousDeparture, travelMinutes);
        LocalDateTime serviceStart = calculateServiceStart(arrival, windowStart);
        LocalDateTime departure = calculateDeparture(serviceStart, durationMinutes);
        boolean fitsWindow = fitsWindow(departure, windowEnd);

        return new StopEvaluation(
                arrival, serviceStart, departure, fitsWindow);
    }
     /**
     * Evaluates a stop using its appointment window and service duration.
     * Delegates calculations and input validation to the five-argument overload.
     *
     * @param previousDeparture departure time from the preceding location
     * @param travelMinutes travel duration to this stop, in minutes
     * @param stop stop to evaluate; its time window may be absent
     * @return calculated timing and appointment-window feasibility
     */
    public StopEvaluation evaluateStop(LocalDateTime previousDeparture, int travelMinutes, Stop stop) {
        if (stop == null) {
            throw new IllegalArgumentException("Stop is required");
        }
        TimeWindow window = stop.getTimeWindow();

        if (window == null) {
            return evaluateStop(
                    previousDeparture, travelMinutes, null, null,
                    stop.getDurationMinutes());
        }

        return evaluateStop(
                previousDeparture, travelMinutes,
                window.getWindowStart(), window.getWindowEnd(),
                stop.getDurationMinutes());
    }
    /**
     * Evaluates one fixed stop order, carrying each departure into the next leg.
     *
     * @param stops stops in visiting order
     * @param travelMinutes incoming travel minutes for each stop;
     *                      the first entry covers the start location to stop 0
     * @param initialDeparture departure date and time from the start location
     * @return stop evaluations in the supplied order
     */
    public List<StopEvaluation> evaluateRoute(
            List<Stop> stops,
            int[] travelMinutes,
            LocalDateTime initialDeparture){
        List<StopEvaluation> evaluations = new ArrayList<>();
        LocalDateTime previousDeparture = initialDeparture;
        for (int i = 0; i < stops.size(); i++){
            StopEvaluation result = evaluateStop(previousDeparture,travelMinutes[i],stops.get(i));
            evaluations.add(result);
            previousDeparture = result.getDeparture();
        }
        return evaluations;

    }



}