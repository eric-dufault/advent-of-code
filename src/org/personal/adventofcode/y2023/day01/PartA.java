package org.personal.adventofcode.y2023.day01;

import java.util.List;

public class PartA {

	public static void execute(List<String> fileLines) {
		Integer sum = fileLines.stream().map(PartA::getTwoDigitNumber).reduce(0, Integer::sum);
		System.out.println(sum);
	}

	private static Integer getTwoDigitNumber(String line) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (Character.isDigit(c)) {
				sb.append(c);
				break;
			}
		}

		for (int i = line.length() - 1; i >= 0; i--) {
			char c = line.charAt(i);
			if (Character.isDigit(c)) {
				sb.append(c);
				break;
			}
		}

		if (sb.isEmpty())
			throw new RuntimeException("bad data");

		return Integer.parseInt(sb.toString());
	}
}
