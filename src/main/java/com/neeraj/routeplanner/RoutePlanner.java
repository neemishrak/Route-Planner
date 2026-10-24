package com.neeraj.routeplanner;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
@Service
public class RoutePlanner {
    public LocalTime calculateServiceStart(
            LocalTime arrival, LocalTime windowStart) {
        if (arrival.isBefore(windowStart)){
            return windowStart;
        }
        return arrival;
    }
    public LocalTime calculateDeparture(
            LocalTime serviceStart, int durationMinutes) {
        return serviceStart.plusMinutes(durationMinutes);
    }
    public boolean fitsWindow(
            LocalTime departure, LocalTime windowEnd) {
        return !departure.isAfter(windowEnd);
    }
    public LocalTime calculateArrival(
            LocalTime previousDeparture, int travelMinutes) {
        return previousDeparture.plusMinutes(travelMinutes);
    }
    public StopEvaluation evaluateStop(
            LocalTime previousDeparture,
            int travelMinutes,
            LocalTime windowStart,
            LocalTime windowEnd,
            int durationMinutes) {

            LocalTime arrival = calculateArrival(previousDeparture, travelMinutes);
            LocalTime serviceStart = calculateServiceStart(arrival, windowStart);
            LocalTime departure = calculateDeparture(serviceStart, durationMinutes);
            boolean fitsWindow = fitsWindow(departure,windowEnd);
            return new StopEvaluation(arrival, serviceStart,departure,fitsWindow);
    }


}
