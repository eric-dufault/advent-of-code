package org.personal.adventofcode.y2023.day05;

public class SeedRange {
	private final long min;
	private final long max;
	private final long length;

	public SeedRange(long min, long length) {
		this.min = min;
		this.max = min + length - 1;
		this.length = length;
	}

	public long getMin() {
		return min;
	}

	public long getMax() {
		return max;
	}

	public long getLength() {
		return length;
	}
}
