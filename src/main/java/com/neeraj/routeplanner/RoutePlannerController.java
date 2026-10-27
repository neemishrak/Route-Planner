package com.neeraj.routeplanner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class RoutePlannerController {
    private final RoutePlanner routePlanner;

    public RoutePlannerController(RoutePlanner routePlanner) {
        this.routePlanner = routePlanner;
    }
    @GetMapping("/stops/evaluate")
    public StopEvaluation evaluateStop() {
        return routePlanner.evaluateStop(
                LocalDateTime.of(2026, 10, 25, 9, 0),
                20,
                LocalDateTime.of(2026, 10, 25, 9, 30),
                LocalDateTime.of(2026, 10, 25, 10, 15),
                46);
    }

    @PostMapping("/stops/evaluate")
    public StopEvaluation evaluateStop(
            @RequestBody StopEvaluationRequest request) {
        return routePlanner.evaluateStop(
                request.getPreviousDeparture(),
                request.getTravelMinutes(),
                request.getWindowStart(),
                request.getWindowEnd(),
                request.getDurationMinutes());
    }
}
