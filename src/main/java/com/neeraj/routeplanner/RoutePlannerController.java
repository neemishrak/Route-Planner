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
        TimeWindow timeWindow = new TimeWindow();
        timeWindow.setWindowStart(LocalDateTime.of(2026,10,25,9,30));
        timeWindow.setWindowEnd(LocalDateTime.of(2026,10,25,10,45));

        Stop demoStop = new Stop();
        demoStop.setName("Demo Stop");
        demoStop.setRequired(true);
        demoStop.setStopType(StopType.JOB);
        demoStop.setDurationMinutes(45);
        demoStop.setTimeWindow(timeWindow);
        demoStop.setId("DM");
        return routePlanner.evaluateStop(
                LocalDateTime.of(2026, 10, 25, 9, 0),
                20,
                demoStop);
    }

    @PostMapping("/stops/evaluate")
    public StopEvaluation evaluateStop(
            @RequestBody StopEvaluationRequest request) {
        return routePlanner.evaluateStop(
                request.getPreviousDeparture(),
                request.getTravelMinutes(),
                request.getStop());
    }
}
