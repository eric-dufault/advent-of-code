package org.personal.adventofcode.y2022.day16;

import java.util.ArrayList;
import java.util.List;

public class Path {
	private Valve valve;
	private List<Valve> visited = new ArrayList<>();
	private int steps;
	private int flowRate;
	private int flow;
	private int totalFlow;

	public Path(Valve valve) {
		this.valve = valve;
	}

	public Path(Valve valve, List<Valve> visited) {
		this.valve = valve;
		this.visited.addAll(visited);
	}

	public Valve getValve() {
		return valve;
	}

	public List<Valve> getVisited() {
		return visited;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getFlowRate() {
		return flowRate;
	}

	public void setFlowRate(int flowRate) {
		this.flowRate = flowRate;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	public int getTotalFlow() {
		return totalFlow;
	}

	public void setTotalFlow(int totalFlow) {
		this.totalFlow = totalFlow;
	}
}
