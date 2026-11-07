package com.neeraj.routeplanner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Planner Demo Class created for testing Stop Creation and assignment of timeWindowA to a Stop*/

public class PlannerDemo {
    public static void main(String[] args) {
       Location startLocation = new Location();
       startLocation.setId("loc-home");
       startLocation.setName("Home");

       Location jobLocation = new Location();
       jobLocation.setId("loc-job");
       jobLocation.setName("Client site");

       Location suppliesLocation = new Location();
       suppliesLocation.setId("loc-supplies");
       suppliesLocation.setName("Supplies shop");


       TimeWindow timeWindowA = new TimeWindow();
       timeWindowA.setWindowStart(LocalDateTime.of(2026,10,26,9,45));
       timeWindowA.setWindowEnd(LocalDateTime.of(2026,10,26,10,15));
       LocalDateTime initialDeparture = LocalDateTime.of(2026,10,26,9,15);
       //Creating the StopA as a Job Type//
       TravelTimeProvider provider = new MockTravelTimeProvider();
       RoutePlanner routePlanner = new RoutePlanner(provider);
       System.out.println();
       System.out.println("--------------------------------------------------------");
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
       stopA.setLocation(jobLocation);
       stopB.setLocation(suppliesLocation);
       List<Stop> stops = new ArrayList<>();
       stops.add(stopA);
       stops.add(stopB);
       List<StopEvaluation> evaluations =
               routePlanner.evaluateRoute(stops, startLocation,initialDeparture);
       for (StopEvaluation evaluation : evaluations) {
          System.out.println(
                  evaluation.getStop().getName() + "      "
                          + evaluation.getStop().getStopType() + " | "
                          + evaluation.getArrival() + " | "
                          + evaluation.getDeparture() + " | "
                          + evaluation.isFitsWindow());
       }



    }
}
