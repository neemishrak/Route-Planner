# Route Planner

A Java and Spring Boot learning project for a traffic-aware, multi-stop appointment and route planner. The current implementation focuses on the core scheduling logic for a single stop: it calculates arrival, waits for an appointment window to open if needed, computes service start and departure, and checks whether the full service fits within the allowed time window.

## Highlights

- Evaluate a single stop using travel time, appointment window, and service duration
- Wait until the appointment window opens if the traveler arrives early
- Preserve local date/time values across midnight boundaries
- Reject invalid input such as missing times, negative durations, and reversed windows
- Expose the scheduling logic via a Spring Boot REST API

## Tech stack

- Java 17
- Spring Boot 4.1.2
- Maven Wrapper
- Spring Web MVC
- Java time API (`java.time.LocalDateTime`)

## Project structure

```text
Route-Planner/
├── src/
│   ├── main/
│   │   ├── java/com/neeraj/routeplanner/
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   ├── Location.java
│   │   │   ├── PlannerDemo.java
│   │   │   ├── RoutePlanner.java
│   │   │   ├── RoutePlannerApplication.java
│   │   │   ├── RoutePlannerController.java
│   │   │   ├── Stop.java
│   │   │   ├── StopEvaluation.java
│   │   │   ├── StopEvaluationRequest.java
│   │   │   ├── StopType.java
│   │   │   └── TimeWindow.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/java/com/neeraj/routeplanner/
│       └── RoutePlannerApplicationTests.java
├── .mvn/
├── .gitattributes
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── .
```

## Current functionality

The planner currently supports evaluating a single stop with the following inputs:

- `previousDeparture`: the departure time from the previous location
- `travelMinutes`: duration to reach the stop
- `windowStart`: earliest allowed service start time
- `windowEnd`: latest allowed completion time
- `durationMinutes`: time required for the appointment or service

The service does the following:

1. Calculates arrival from the previous departure and travel time.
2. If the arrival is before the window start, it waits until the opening time.
3. Adds service duration to determine the departure time.
4. Verifies whether the departure falls within the appointment window.
5. Rejects invalid values with validation errors.

## Run locally

Requirements:

- JDK 17 or later

```bash
git clone https://github.com/neemishrak/Route-Planner.git
cd Route-Planner
./mvnw spring-boot:run
```

The application runs on port `8080` by default.

## API endpoint

### POST `/stops/evaluate`

Send a JSON body like the example below:

```bash
curl -i -X POST http://localhost:8080/stops/evaluate \
  -H "Content-Type: application/json" \
  -d '{
    "previousDeparture": "2026-10-25T23:30",
    "travelMinutes": 20,
    "windowStart": "2026-10-25T23:00",
    "windowEnd": "2026-10-26T00:20",
    "durationMinutes": 30
  }'
```

Example response:

```json
{
  "arrival": "2026-10-25T23:50:00",
  "serviceStart": "2026-10-25T23:50:00",
  "departure": "2026-10-26T00:20:00",
  "fitsWindow": true
}
```

The system allows a service to end exactly at the window end.

## Domain model in progress

The project includes `Stop`, `StopType`, `Location`, and `TimeWindow` models, but the current controller and evaluation flow are centered on a single-stop calculation rather than a full route optimization engine.

The design is intentionally incremental:

- travel time is supplied by the caller instead of fetched from a traffic provider
- route composition is not yet multi-stop optimized
- timezone-aware scheduling and real-world map integration are still future work

## Roadmap

Planned next steps include:

- evaluate multiple stops in a fixed sequence
- incorporate deterministic mock travel estimates
- explore stop-order search with backtracking and pruning
- investigate dynamic programming for route feasibility states
- add a map-based or UI-based visualization layer
- integrate real traffic-aware routing providers
- support timezone-aware scheduling

## Learning goals

This project is being built as a learning-first implementation focused on:

- Java fundamentals and clean OO modeling
- Spring Boot and MVC APIs
- domain modeling for route and scheduling problems
- algorithmic thinking for route planning and optimization
- practical experience with testing and validation

## Current status

This repository is a working learning prototype for route scheduling logic, not yet a complete production route-planning application. The most mature behavior is the time-window evaluation flow for a single appointment stop.
