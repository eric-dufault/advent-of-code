package org.personal.adventofcode.y2023.day01;

import java.util.*;

import static java.util.Map.entry;

public class PartB {

	private static final Set<String> WORDS_DIGITS = new HashSet<>(Arrays.asList("one", "two", "three", "four", "five", "six", "seven",
			"eight", "nine", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

	private static final Map<String, String> WORD_DIGIT_MAP = Map.ofEntries(
			entry("one", "1"),
			entry("two", "2"),
			entry("three", "3"),
			entry("four", "4"),
			entry("five", "5"),
			entry("six", "6"),
			entry("seven", "7"),
			entry("eight", "8"),
			entry("nine", "9")
	);

	public static void execute(List<String> fileLines) {
		Integer sum = fileLines.stream().map(PartB::getTwoDigitNumber).reduce(0, Integer::sum);
		System.out.println(sum);
	}

	private static Integer getTwoDigitNumber(String line) {
		//look for usages of words and digits in line
		Map<String, Integer> minOccurrences = new HashMap<>();
		for (String word : WORDS_DIGITS) {
			int indexOf = line.indexOf(word);
			if (indexOf >= 0) {
				minOccurrences.put(word, indexOf);
			}
		}

		Map<String, Integer> maxOccurrences = new HashMap<>();
		for (String word : WORDS_DIGITS) {
			int indexOf = line.lastIndexOf(word);
			if (indexOf >= 0) {
				maxOccurrences.put(word, indexOf);
			}
		}

		//get the keys with boundary values
		Map.Entry<String, Integer> min = null;
		for (Map.Entry<String, Integer> entry : minOccurrences.entrySet()) {
			if (min == null || min.getValue() > entry.getValue())
				min = entry;
		}

		Map.Entry<String, Integer> max = null;
		for(Map.Entry<String, Integer> entry : maxOccurrences.entrySet()) {
			if (max == null || max.getValue() < entry.getValue())
				max = entry;
		}

		if (min == null || max == null)
			throw new RuntimeException("Bad data");

		//translate to digits
		String minVal = WORD_DIGIT_MAP.get(min.getKey());
		if (minVal == null)
			minVal = min.getKey();

		String maxVal = WORD_DIGIT_MAP.get(max.getKey());
		if (maxVal == null)
			maxVal = max.getKey();

		return Integer.parseInt(minVal + maxVal);
	}
}
