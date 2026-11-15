package com.neeraj.routeplanner;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RoutePlannerTest {

	@Test
	void serviceEndingExactlyAtWindowCloseFits() {
		// Arrange
		RoutePlanner planner = new RoutePlanner(new MockTravelTimeProvider());
		Stop stop = new Stop();
		LocalDateTime previousDeparture = LocalDateTime.of(2026, 10, 26, 9, 15);
		LocalDateTime windowStart = LocalDateTime.of(2026, 10, 26, 9, 45);
		LocalDateTime windowEnd = LocalDateTime.of(2026, 10, 26, 10, 15);

		// Act
		StopEvaluation result = planner.evaluateStop(
				stop, previousDeparture, 25, windowStart, windowEnd, 31);

		// Assert
		assertEquals(windowStart, result.getServiceStart());
		assertEquals(windowEnd.plusMinutes(1), result.getDeparture());
		assertFalse(result.isFitsWindow());
	}
}