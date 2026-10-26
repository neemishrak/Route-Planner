# Route Planner

A learning-first Java and Spring Boot project working toward a
traffic-aware, multi-stop appointment planner.

## Current functionality

- Evaluate arrival, service start, and departure for a single stop.
- Wait until the appointment window opens when arriving early.
- Check whether the entire service fits inside the window.
- Preserve dates when travel or service crosses midnight.
- Accept evaluation inputs through a POST endpoint.
- Reject missing times, negative durations, and reversed windows
  with HTTP 400 responses.

## Technology

- Java 17 target
- Spring Boot 4.1.2
- Maven Wrapper
- Spring Web MVC

## Run locally

A compatible JDK is required.

```bash
git clone https://github.com/neemishrak/Route-Planner.git
cd Route-Planner
./mvnw spring-boot:run
```

The application listens on port 8080 by default.

## Example request

Run this in a second terminal while the application is running:

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

Expected response body:

```json
{
  "arrival": "2026-10-25T23:50:00",
  "serviceStart": "2026-10-25T23:50:00",
  "departure": "2026-10-26T00:20:00",
  "fitsWindow": true
}
```

Finishing exactly at the window end is allowed.

## Domain model in progress

The project includes Stop, StopType, Location, and TimeWindow classes.
These are not yet integrated into the evaluation endpoint.

Travel time belongs to a journey between locations, rather than
being a fixed property of a stop.

## Planned features

- Evaluate multiple stops in a fixed order.
- Introduce deterministic mock travel estimates based on departure time.
- Explore stop orders using backtracking and pruning.
- Investigate dynamic programming for suitable planning states.
- Add a map-based interface.
- Investigate real traffic-aware routing providers.
- Support timezone-aware scheduling.

## Current limitations

- The API evaluates one stop at a time.
- Travel duration is supplied by the caller.
- No map, live traffic integration, or persistence is implemented.
- Date-time values are interpreted in one shared timezone;
  timezone conversion and daylight-saving handling are not implemented.

## Learning approach

This project is being built incrementally with AI-assisted tutoring
and code review. The focus is understanding Java, Spring, domain
modeling, graph algorithms, and backtracking—not simply generating
a complete application.