package org.personal.adventofcode.y2023.day05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartA {

	public static void execute(List<String> fileLines, List<RangeCategory> rangeCategories) {
		List<Long> seeds = parseSeeds(fileLines);

		Collections.sort(rangeCategories);

		List<Long> mappedSeeds = new ArrayList<>();
		for (Long seed : seeds) {
			long mappedSeed = seed;
			for (RangeCategory rangeCategory : rangeCategories) {
				mappedSeed = rangeCategory.getMappedValue(mappedSeed);
			}
			mappedSeeds.add(mappedSeed);
		}

		Collections.sort(mappedSeeds);
		System.out.println(mappedSeeds.get(0));
	}

	private static List<Long> parseSeeds(List<String> fileLines) {
		List<Long> seeds = new ArrayList<>();
		String[] seedParts = fileLines.get(0).trim().split(":");
		String[] individualSeedParts = seedParts[1].trim().split("\\s+");
		for (String seed : individualSeedParts)
			seeds.add(Long.parseLong(seed));

		return seeds;
	}
}
