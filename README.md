# Route Planner

A Java and Spring Boot learning project for a traffic-aware, multi-stop appointment and route planner.

This project models the scheduling logic behind a route planner that evaluates stop timing, appointment windows, and fixed-order route sequencing without yet integrating live traffic data or map services.

## Overview

The application focuses on one core question: given a previous departure time, travel duration, and a stop's appointment window, when should the traveler arrive, when should service begin, and when should they depart?

It currently supports:

- single-stop timing evaluation
- appointment window handling with waiting logic
- validation for invalid inputs
- fixed-order route evaluation that carries each departure into the next leg
- a Spring MVC REST API for demo and request-driven evaluation

## Features

- Evaluate a stop using travel time, appointment window, and service duration
- Wait until the appointment window opens when arriving early
- Preserve local date/time values across midnight boundaries
- Reject invalid input such as missing times, negative travel/service duration, and reversed windows
- Evaluate a fixed route in sequence using each stop's departure as the next leg's starting point
- Expose the scheduling logic through a Spring Boot API

## Tech stack

- Java 17
- Spring Boot 4.1.2
- Maven Wrapper
- Spring Web MVC
- Java Time API (`java.time.LocalDateTime`)

## Architecture

The project uses a small layered design:

- `RoutePlannerController`: REST API endpoints
- `RoutePlanner`: scheduling calculations and validation
- `Stop`, `Location`, `TimeWindow`, `StopType`: domain model
- `StopEvaluation`: response object returned to the client
- `GlobalExceptionHandler`: converts invalid input into HTTP 400 responses

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
└── .github/
```

## Core logic

The planner currently supports these behaviors:

### Single-stop evaluation

`RoutePlanner.evaluateStop(...)` computes:

- arrival time from previous departure + travel duration
- service start, waiting until the appointment window opens if needed
- departure time after service duration
- whether the stop remains within the appointment window

Validation ensures:

- required times are present
- travel time is not negative
- duration is not negative
- both appointment window boundaries are supplied together
- window end is not before window start

### Fixed-route evaluation

`RoutePlanner.evaluateRoute(...)` evaluates a sequence of stops in order and carries each stop's departure time into the next leg. This is a useful first step toward multi-stop planning while still being deterministic and simple.

## Run locally

Requirements:

- JDK 17 or later

```bash
git clone https://github.com/neemishrak/Route-Planner.git
cd Route-Planner
./mvnw spring-boot:run
```

The application runs on port `8080` by default.

## API endpoints

### GET `/stops/evaluate`

Returns a demo evaluation using a fixed sample stop.

```bash
curl http://localhost:8080/stops/evaluate
```

Example response:

```json
{
  "arrival": "2026-10-25T09:20:00",
  "serviceStart": "2026-10-25T09:30:00",
  "departure": "2026-10-25T10:15:00",
  "fitsWindow": true
}
```

### POST `/stops/evaluate`

Send a JSON request body like this:

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

The system permits a service to end exactly at the window end.

## Validation behavior

Invalid requests return HTTP 400 responses from `GlobalExceptionHandler`.

Typical validation failures include:

- missing `previousDeparture`
- negative `travelMinutes`
- negative `durationMinutes`
- only one of `windowStart` or `windowEnd` supplied
- `windowEnd` before `windowStart`

## Current status

This repository is a working learning prototype for route scheduling logic. The most mature behavior is the time-window evaluation flow for single stops and fixed-order route evaluation.

## Roadmap

Planned future work includes:

- evaluate multiple stops in a fixed sequence with richer route context
- deterministic mock travel estimates
- stop-order search with backtracking and pruning
- dynamic programming for route-feasibility states
- map-based or UI-based visualization
- real traffic-aware routing providers
- timezone-aware scheduling

## Learning goals

This project is intended to build practical experience with:

- Java fundamentals and clean object modeling
- Spring Boot and MVC APIs
- domain modeling for route and scheduling problems
- algorithmic thinking for optimization and feasibility checks
- validation and test-driven development

## Notes

The current design is intentionally incremental:

- travel time is supplied by the caller instead of being fetched from a live provider
- route composition is not yet optimized across all stop permutations
- actual map integration and timezone-aware scheduling are future work
