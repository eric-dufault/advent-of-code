package org.personal.adventofcode.y2023.day05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RangeTest {

	@Test
	public void testGetMappedValue() {
		Range range = new Range(50L, 52L, 48L);
		long mappedValue = range.getMappedValue(79L);
		assertEquals(81L, mappedValue);
	}

	@Test
	public void testGetMappedValue_boundaries() {
		Range range = new Range(98L, 50L, 2L);

		assertEquals(97L, range.getMappedValue(97L));
		assertEquals(50L, range.getMappedValue(98L));
		assertEquals(51L, range.getMappedValue(99L));
		assertEquals(100L, range.getMappedValue(100L));
	}
}
