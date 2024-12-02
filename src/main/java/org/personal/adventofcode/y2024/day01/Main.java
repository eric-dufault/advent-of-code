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
		long similarityScore = 0;
		for (Integer left : lefts) {
			long occurrences = 0;

			int found = Arrays.binarySearch(rightsArray, left);
			if (found > 0) {
				occurrences++;

				int l = found - 1;
				while (0 < l && l < rightsArray.length && rightsArray[l] == left) {
					occurrences++;
					l--;
				}

				int r = found + 1;
				while (0 < r && r < rightsArray.length && rightsArray[r] == left) {
					occurrences++;
					r++;
				}
			}

			similarityScore += (left * occurrences);
		}

		System.out.println(similarityScore);
	}

}
