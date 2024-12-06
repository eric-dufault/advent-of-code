package org.personal.adventofcode.y2024.day05;

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

	public List<Integer> getCorrectlyOrderedUpdates(Map<Integer, List<Integer>> orderingRules) {
		if (isCorrectOrder(orderingRules))
			return updates;
		else {
			List<Integer> newUpdates = new ArrayList<>();

			for (int i = 0; i < updates.size(); i++) {
				List<Integer> orderingRule = orderingRules.get(updates.get(i));
				if (orderingRule != null) {

				} else {
					//no ordering rule means can be in right spot
					newUpdates.add(updates.get(i));
				}
			}

			return newUpdates;
		}
	}

	public int getMiddle() {
		int half = updates.size() / 2;
		return updates.get(half);
	}

	public String toString() {
		return updates.toString();
	}
}
