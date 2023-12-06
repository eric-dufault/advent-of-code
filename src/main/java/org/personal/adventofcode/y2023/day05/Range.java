package org.personal.adventofcode.y2023.day05;

public class Range {
	private final long sourceMin;
	private final long sourceMax;
	private final long destMin;
	private final long destMax;
	private final long length;

	public Range(long sourceMin, long destMin, long length) {
		this.sourceMin = sourceMin;
		this.sourceMax = sourceMin + length - 1;
		this.destMin = destMin;
		this.destMax = destMin + length - 1;
		this.length = length;
	}

	public boolean inMapping(long x) {
		return getSourceMin() <= x && x <= getSourceMax();
	}

	public long getMappedValue(long x) {
		long mappedValue = x;
		if (inMapping(x))
			mappedValue = getDestMin() + (x - getSourceMin());

		return mappedValue;
	}

	public long getSourceMin() {
		return sourceMin;
	}

	public long getSourceMax() {
		return sourceMax;
	}

	public long getDestMin() {
		return destMin;
	}

	public long getDestMax() {
		return destMax;
	}

	public long getLength() {
		return length;
	}

}
