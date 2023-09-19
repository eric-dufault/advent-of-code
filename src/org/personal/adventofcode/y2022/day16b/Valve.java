package org.personal.adventofcode.y2022.day16b;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Valve {
	private final String key;
	private final int flowRate;
	private final Set<Valve> adjacentValves = new HashSet<>();  //nodes connected to this node by an edge
	private final Map<Valve, Integer> distances = new HashMap<>();  //distances to other nodes in graph

	public Valve(String key, int flowRate) {
		this.key = key;
		this.flowRate = flowRate;
	}

	public String getKey() {
		return key;
	}

	public int getFlowRate() {
		return flowRate;
	}

	public Set<Valve> getAdjacentValves() {
		return adjacentValves;
	}

	public void addAdjacentValve(Valve valve) {
		adjacentValves.add(valve);
	}

	public Integer getStepsToValve(Valve valve) {
		return distances.get(valve);
	}

	public void setStepsToValve(Valve valve, Integer steps) {
		distances.put(valve, steps);
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null)
			return false;

		if (!(o instanceof Valve))
			return false;

		return ((Valve) o).getKey().equals(this.getKey());
	}

}
