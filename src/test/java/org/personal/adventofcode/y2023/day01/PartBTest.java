package org.personal.adventofcode.y2023.day01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartBTest {

	@Test
	public void testGetTwoDigitNumber_eighthree() {
		Integer actual = PartB.getTwoDigitNumber("eighthree");
		assertEquals(83, actual);
	}

	@Test
	public void testGetTwoDigitNumber_7pqrstsixteen() {
		Integer actual = PartB.getTwoDigitNumber("7pqrstsixteen");
		assertEquals(76, actual);
	}

	@Test
	public void testGetTwoDigitNumber_xtwone3fourone() {
		Integer actual = PartB.getTwoDigitNumber("xtwone3fourone");
		assertEquals(21, actual);
	}
}
