package org.personal.adventofcode.y2024.day01;

public class Location {
	private final Integer id;
	private int occurrences = 0;

	public Location(Integer id) {
		this.id = id;
	}

	public void incrementOccurrences() {
		this.occurrences++;
	}

	public Integer getSimilarityScore() {
		return this.id * this.occurrences;
	}
}
