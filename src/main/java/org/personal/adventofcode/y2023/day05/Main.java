package org.personal.adventofcode.y2023.day05;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

	private static final List<String> RANGE_CATEGORIES = Arrays.asList(
			"seed-to-soil map:",
			"soil-to-fertilizer map:",
			"fertilizer-to-water map:",
			"water-to-light map:",
			"light-to-temperature map:",
			"temperature-to-humidity map:",
			"humidity-to-location map:"
	);

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2023\\day05\\input.txt"));
		List<RangeCategory> rangeCategories = parseRangeCategory(fileLines);

		PartA.execute(fileLines, rangeCategories);
	}

	private static List<RangeCategory> parseRangeCategory(List<String> fileLines) {
		List<RangeCategory> rangeCategories = new ArrayList<>();

		Map<String, Integer> rangeCategoryLineNumberMap = new HashMap<>();
		for (int i = 0; i < fileLines.size(); i++) {
			String line = fileLines.get(i).trim();
			if (RANGE_CATEGORIES.contains(line)) {
				rangeCategoryLineNumberMap.put(line, i);
			}
		}

		for (Map.Entry<String,Integer> entry : rangeCategoryLineNumberMap.entrySet()) {
			RangeCategory rangeCategory = new RangeCategory(entry.getKey(), RANGE_CATEGORIES.indexOf(entry.getKey()) + 1);

			int lineNumber = entry.getValue() + 1;
			String line = fileLines.get(lineNumber).trim();
			while (!"".equals(line)) {
				Range range = parseRange(line);
				rangeCategory.addRange(range);

				lineNumber++;
				if (lineNumber >= fileLines.size())
					break;
				line = fileLines.get(lineNumber).trim();
			}

			rangeCategories.add(rangeCategory);
		}

		return rangeCategories;
	}

	private static Range parseRange(String line) {
		String[] parts = line.trim().split("\\s+");
		long destinationMin = Long.parseLong(parts[0]);
		long sourceMin = Long.parseLong(parts[1]);
		long length = Long.parseLong(parts[2]);
		return new Range(sourceMin, destinationMin, length);
	}
}
