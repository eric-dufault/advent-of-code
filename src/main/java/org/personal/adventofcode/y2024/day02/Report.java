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
		int i = 0, j = 1;
		boolean foundIncreasing = false;
		boolean foundDecreasing = false;
		boolean levelsVaryTooMuch = false;
		while (j < levels.size()) {
			int a = levels.get(i);
			int b = levels.get(j);

			levelsVaryTooMuch = Math.abs(a - b) == 0 || Math.abs(a - b) > 3;
			if (levelsVaryTooMuch)
				break;

			if (a < b)
				foundIncreasing = true;

			if (a > b)
				foundDecreasing = true;

			i++;
			j++;
		}

		return !levelsVaryTooMuch && ((foundIncreasing && !foundDecreasing) || (!foundIncreasing && foundDecreasing));
	}

	public boolean isSafeWithDampener() {
		if (isSafe())
			return true;
		else {
			int i = 0, j = 1;
			boolean foundIncreasing = false;
			int foundIncreasingI = 0, foundIncreasingJ = 0;
			boolean foundDecreasing = false;
			int foundDecreasingI = 0, foundDecreasingJ = 0;
			boolean levelsVaryTooMuch = false;
			int levelsVaryTooMuchI = 0, levelsVaryTooMuchJ = 0;

			while (j < levels.size()) {
				int a = levels.get(i);
				int b = levels.get(j);

				levelsVaryTooMuch = Math.abs(a - b) == 0 || Math.abs(a - b) > 3;
				if (levelsVaryTooMuch) {
					levelsVaryTooMuchI = i;
					levelsVaryTooMuchJ = j;
					break;
				}

				if (a < b) {
					foundIncreasing = true;
					foundIncreasingI = i;
					foundIncreasingJ = j;
				}

				if (a > b) {
					foundDecreasing = true;
					foundDecreasingI = i;
					foundDecreasingJ = j;
				}

				i++;
				j++;
			}

			if (levelsVaryTooMuch) {
				if (isSafe(getRemainingLevels(levelsVaryTooMuchI)))
					return true;

				if (isSafe(getRemainingLevels(levelsVaryTooMuchJ)))
					return true;
			}

			if (foundIncreasing && foundDecreasing) {
				if (isSafe(getRemainingLevels(foundDecreasingI)))
					return true;

				if (isSafe(getRemainingLevels(foundDecreasingJ)))
					return true;

				if (isSafe(getRemainingLevels(foundIncreasingI)))
					return true;

				if (isSafe(getRemainingLevels(foundIncreasingJ)))
					return true;
			}

			return false;
		}
	}

	private List<Integer> getRemainingLevels(int omitIndex) {
		List<Integer> levels = new ArrayList<>(DEFAULT_LEVELS_SIZE);
		for (int i = 0; i < this.levels.size(); i++) {
			if (i != omitIndex)
				levels.add(this.levels.get(i));
		}
		return levels;
	}
}
