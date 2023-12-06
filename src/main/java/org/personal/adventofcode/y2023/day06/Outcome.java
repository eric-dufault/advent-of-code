package org.personal.adventofcode.y2023.day06;

public class Outcome {
	private final long velocity;
	private final long travelTime;

	public Outcome(long velocity, long travelTime) {
		this.velocity = velocity;
		this.travelTime = travelTime;
	}

	public long getDistanceTravelled() {
		return getVelocity() * getTravelTime();
	}

	public long getVelocity() {
		return velocity;
	}

	public long getTravelTime() {
		return travelTime;
	}

}
