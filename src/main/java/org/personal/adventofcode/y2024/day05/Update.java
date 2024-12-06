package org.personal.adventofcode.y2024.day05;

import org.personal.helpers.graph.DiGraphImpl;
import org.personal.helpers.graph.Graph;
import org.personal.helpers.graph.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Update {
	public final List<Integer> updates = new ArrayList<>();

	public void addUpdate(Integer update) {
		this.updates.add(update);
	}

	public boolean isCorrectOrder(Map<Integer, List<Integer>> orderingRules) {
		boolean correctOrder = true;
		for (int i = 0; i < updates.size(); i++) {
			List<Integer> orderingRule = orderingRules.get(updates.get(i));
			if (orderingRule != null) {
				for (int j = i + 1; j < updates.size(); j++) {
					if (!orderingRule.contains(updates.get(j))) {
						correctOrder = false;
						break;
					}
				}

				for (int k = i - 1; k >= 0; k--) {
					if (orderingRule.contains(updates.get(k))) {
						correctOrder = false;
						break;
					}
				}
			}
		}
		return correctOrder;
	}

	public Graph<Integer> buildDiGraph(Map<Integer, List<Integer>> orderingRules) {
		Graph<Integer> g = new DiGraphImpl<>();

		for (Integer i : this.updates) {
			g.insertVertex(i);
		}

		for (Vertex<Integer> v : g.vertices()) {
			if (orderingRules.containsKey(v.getData())) {
				List<Integer> values = orderingRules.get(v.getData());
				for (Vertex<Integer> w : g.vertices()) {
					if (!w.equals(v) && values.contains(w.getData())) {
						g.insertEdge(v, w);
					}
				}
			}
		}
		return g;
	}

	public String toString() {
		return updates.toString();
	}
}
