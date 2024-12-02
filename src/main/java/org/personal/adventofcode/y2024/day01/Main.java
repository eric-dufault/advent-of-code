package org.personal.adventofcode.y2024.day01;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2024\\day01\\input.txt"));

		//PART A
		List<Integer> lefts = new ArrayList<>();
		List<Integer> rights = new ArrayList<>();
		fileLines.forEach(line -> {
			String[] parts = line.split("   ");

			lefts.add(Integer.parseInt(parts[0]));
			rights.add(Integer.parseInt(parts[1]));
		});

		Collections.sort(lefts);
		Collections.sort(rights);

		long distances = IntStream.range(0, lefts.size())
				.mapToLong(i -> Math.abs(rights.get(i) - lefts.get(i)))
				.sum();
		System.out.println(distances);


		//PART B
		int[] rightsArray =  rights.stream().mapToInt(Integer::intValue).toArray();
		List<Location> locations = new ArrayList<>();
		for (Integer left : lefts) {
			Location location = new Location(left);

			int found = Arrays.binarySearch(rightsArray, left);
			if (found > 0) {
				location.incrementOccurrences();

				int l = found - 1;
				while (0 < l && l < rightsArray.length && rightsArray[l] == left) {
					location.incrementOccurrences();
					l--;
				}

				int r = found + 1;
				while (0 < r && r < rightsArray.length && rightsArray[r] == left) {
					location.incrementOccurrences();
					r++;
				}
			}

			locations.add(location);
		}

		long similaritiesScore = locations.stream()
				.mapToLong(Location::getSimilarityScore)
				.sum();
		System.out.println(similaritiesScore);
	}

}
