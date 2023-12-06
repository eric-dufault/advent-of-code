package org.personal.adventofcode.y2023.day05;

import java.util.ArrayList;
import java.util.List;

public class RangeCategory implements Comparable<RangeCategory> {
	private List<Range> ranges;
	private final int sequenceNumber;
	private final String name;

	public RangeCategory(String name, int sequenceNumber) {
		this.name = name;
		this.sequenceNumber = sequenceNumber;
	}

	public long getMappedValue(long x) {
		Range range = getMappedRange(x);
		return range != null ? range.getMappedValue(x) : x;
	}

	private Range getMappedRange(long x) {
		Range range = null;
		for (Range sdc : getRanges()) {
			if (sdc.inMapping(x)) {
				range = sdc;
				break;
			}
		}
		return range;
	}

	public void addRange(Range range) {
		getRanges().add(range);
	}

	public List<Range> getRanges() {
		if (ranges == null)
			ranges = new ArrayList<>();
		return ranges;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(RangeCategory o) {
		return Integer.compare(this.getSequenceNumber(), o.getSequenceNumber());
	}
}
