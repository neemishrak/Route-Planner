package com.neeraj.routeplanner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class RoutePlannerController {
    private final RoutePlanner routePlanner;

    public RoutePlannerController(RoutePlanner routePlanner) {
        this.routePlanner = routePlanner;
    }
    @GetMapping("/stops/evaluate")
    public StopEvaluation evaluateStop() {
       return routePlanner.evaluateStop(LocalTime.of(9,0)
               , 20, LocalTime.of(9,30),LocalTime.of(10,15),46);
    }

    @PostMapping("/stops/evaluate")
    public StopEvaluation evaluateStop(
            @RequestBody StopEvaluationRequest request) {
           return routePlanner.evaluateStop(request.previousDeparture, request.travelMinutes
                   , request.windowStart, request.windowEnd, request.durationMinutes);
    }
}
