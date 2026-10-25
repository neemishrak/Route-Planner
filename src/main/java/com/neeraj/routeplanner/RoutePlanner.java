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
            //Validation and Exception handling//
            if (windowEnd == null || previousDeparture == null || windowStart == null){
                throw new IllegalArgumentException("Previous departure, window start and window end are required");
            }
            if (durationMinutes < 0){
                throw new IllegalArgumentException("Service duration cannot be negative");
            }
            if (travelMinutes < 0){
                throw new IllegalArgumentException("Travel time cannot be negative");
            }
            if (windowEnd.isBefore(windowStart)){
                throw new IllegalArgumentException("Window end Cannot be before window Start");
            }
            //Stop Evaluation Checks//
            LocalTime arrival = calculateArrival(previousDeparture, travelMinutes);
            LocalTime serviceStart = calculateServiceStart(arrival, windowStart);
            LocalTime departure = calculateDeparture(serviceStart, durationMinutes);
            boolean fitsWindow = fitsWindow(departure,windowEnd);
            return new StopEvaluation(arrival, serviceStart,departure,fitsWindow);
    }


}
