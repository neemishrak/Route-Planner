package com.neeraj.routeplanner;

import java.time.LocalDateTime;

/** Planner Demo Class created for testing Stop Creation and assignment of TimeWindow to a Stop*/

public class PlannerDemo {
    public static void main(String[] args) {
       TimeWindow timeWindow = new TimeWindow();
       timeWindow.setWindowStart(LocalDateTime.of(2026,10,26,9,30));
       timeWindow.setWindowEnd(LocalDateTime.of(2026,10,26,10,15));
       Stop stop = new Stop();
       stop.setTimeWindow(timeWindow);
       stop.setRequired(true);
       stop.setStopType(StopType.JOB);
       stop.setName("Job - Design Consultation");
       stop.setDurationMinutes(30);
       System.out.println(stop.getTimeWindow().getWindowStart());
       RoutePlanner routePlanner = new RoutePlanner();
       StopEvaluation stopEvaluation= routePlanner.evaluateStop(LocalDateTime.of(2026,10,26,9,0)
               , 25,stop);
       System.out.println(stopEvaluation.isFitsWindow());

    }
}
