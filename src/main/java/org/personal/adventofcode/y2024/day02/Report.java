package org.personal.adventofcode.y2024.day02;

import java.util.ArrayList;
import java.util.List;

public class Report {
	private static final int DEFAULT_LEVELS_SIZE = 10;

	private final List<Integer> levels = new ArrayList<>(DEFAULT_LEVELS_SIZE);

	public void addLevel(Integer level) {
		this.levels.add(level);
	}

	public boolean isSafe() {
		return isSafe(this.levels);
	}

	private boolean isSafe(List<Integer> levels) {
		SafetyResult s = getSafetyResult(levels);
		return s.isSafe();
	}

	private SafetyResult getSafetyResult(List<Integer> levels) {
		int i = 0, j = 1;

		SafetyResult s = new SafetyResult();
		while (j < levels.size()) {
			s.levelsVaryBadly = Math.abs(levels.get(i) - levels.get(j)) == 0 || Math.abs(levels.get(i) - levels.get(j)) > 3;
			if (s.levelsVaryBadly) {
				s.lastLevelVaryBadlyIndex = j;
				break;
			}

			if (levels.get(i) < levels.get(j)) {
				s.foundIncreasing = true;
				s.lastIncreasingIndex = j;
			}

			if (levels.get(i) > levels.get(j)) {
				s.foundDecreasing = true;
				s.lastDecreasingIndex = j;
			}

			i++;
			j++;
		}

		return s;
	}

	public boolean isSafeWithDampener() {
		SafetyResult s = getSafetyResult(this.levels);
		if (s.isSafe())
			return true;
		else {
			if (s.levelsVaryBadly) {
				if (isSafe(getLevelsWithRemoved(s.lastLevelVaryBadlyIndex)))
					return true;

				if (isSafe(getLevelsWithRemoved(s.lastLevelVaryBadlyIndex - 1)))
					return true;
			}

			if (s.foundIncreasing && s.foundDecreasing) {
				if (isSafe(getLevelsWithRemoved(s.lastDecreasingIndex)))
					return true;

				if (isSafe(getLevelsWithRemoved(s.lastDecreasingIndex - 1)))
					return true;

				if (isSafe(getLevelsWithRemoved(s.lastIncreasingIndex)))
					return true;

				if (isSafe(getLevelsWithRemoved(s.lastIncreasingIndex - 1)))
					return true;
			}

			return false;
		}
	}

	private List<Integer> getLevelsWithRemoved(int omitIndex) {
		List<Integer> levels = new ArrayList<>(DEFAULT_LEVELS_SIZE);
		for (int i = 0; i < this.levels.size(); i++) {
			if (i != omitIndex)
				levels.add(this.levels.get(i));
		}
		return levels;
	}

	private static class SafetyResult {
		private boolean levelsVaryBadly;
		private int lastLevelVaryBadlyIndex;
		private boolean foundIncreasing;
		private int lastIncreasingIndex;
		private boolean foundDecreasing;
		private int lastDecreasingIndex;

		private boolean isSafe() {
			return !(levelsVaryBadly) && ((foundIncreasing && !foundDecreasing) || (!foundIncreasing && foundDecreasing));
		}
	}
}
