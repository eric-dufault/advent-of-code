package org.personal.adventofcode.y2023.day06;

import java.util.ArrayList;
import java.util.List;

public class Race {
	private final long timeAllotted;
	private final long recordDistance;
	private List<Outcome> outcomes;

	public Race(long timeAllotted, long recordDistance) {
		this.timeAllotted = timeAllotted;
		this.recordDistance = recordDistance;
	}

	public long getTimeAllotted() {
		return timeAllotted;
	}

	public long getRecordDistance() {
		return recordDistance;
	}

	public List<Outcome> getWinningOutcomes() {
		return getOutcomes().stream().filter(o -> o.getDistanceTravelled() > recordDistance).toList();
	}

	public void addOutcome(Outcome outcome) {
		getOutcomes().add(outcome);
	}

	public List<Outcome> getOutcomes() {
		if (outcomes == null)
			outcomes = new ArrayList<>();
		return outcomes;
	}
}
