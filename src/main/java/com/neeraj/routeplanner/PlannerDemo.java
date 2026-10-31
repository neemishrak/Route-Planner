package com.neeraj.routeplanner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Planner Demo Class created for testing Stop Creation and assignment of timeWindowA to a Stop*/

public class PlannerDemo {
    public static void main(String[] args) {
       TimeWindow timeWindowA = new TimeWindow();
       timeWindowA.setWindowStart(LocalDateTime.of(2026,10,26,9,45));
       timeWindowA.setWindowEnd(LocalDateTime.of(2026,10,26,10,15));
       LocalDateTime initialDeparture = LocalDateTime.of(2026,10,26,9,15);
       //Creating the StopA as a Job Type//
       RoutePlanner routePlanner = new RoutePlanner();
       Stop stopA = new Stop();
       stopA.setTimeWindow(timeWindowA);
       stopA.setRequired(true);
       stopA.setStopType(StopType.JOB);
       stopA.setName("Job - Design Consultation");
       stopA.setDurationMinutes(45);
       stopA.setId("stop-a");


       // Create a supplies stop without an appointment window//
       Stop stopB = new Stop();
       stopB.setName("Supplies");
       stopB.setStopType(StopType.PIT_STOP);
       stopB.setRequired(true);
       stopB.setDurationMinutes(20);
       stopB.setId("stop-b");

       List<Stop> stops = new ArrayList<>();
       stops.add(stopA);
       stops.add(stopB);
       int[] travelMinutes = new int[]{25,30};
       List<StopEvaluation> evaluations =
               routePlanner.evaluateRoute(stops, travelMinutes, initialDeparture);
       for (int i = 0; i < evaluations.size(); i++){
          System.out.println(stops.get(i).getName() + " "+  stops.get(i).getStopType() + "   " +
                           evaluations.get(i).getArrival() + " " + evaluations.get(i).getDeparture() + "  " +
                  evaluations.get(i).isFitsWindow());
       }



    }
}
