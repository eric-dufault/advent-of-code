package org.personal.adventofcode.y2023.day11;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2023\\day11\\input.txt"));
		List<Sector> galaxySectors = parseGalaxySectors(fileLines);
		List<Integer> noGalaxyRowIndexes = parseNoGalaxyRowIndexes(fileLines);
		List<Integer> noGalaxyColIndexes = parseNoGalaxyColIndexes(fileLines);

		partA(galaxySectors, noGalaxyRowIndexes, noGalaxyColIndexes);
		partB(galaxySectors, noGalaxyRowIndexes, noGalaxyColIndexes);
	}

	private static void partA(List<Sector> galaxySectors, List<Integer> noGalaxyRowIndexes, List<Integer> noGalaxyColIndexes) {
		System.out.println(getSumShortestPaths(2L, galaxySectors, noGalaxyRowIndexes, noGalaxyColIndexes));
	}

	private static void partB(List<Sector> galaxySectors, List<Integer> noGalaxyRowIndexes, List<Integer> noGalaxyColIndexes) {
		System.out.println(getSumShortestPaths(1_000_000L, galaxySectors, noGalaxyRowIndexes, noGalaxyColIndexes));
	}

	private static long getSumShortestPaths(long baseOffset, List<Sector> galaxySectors, List<Integer> noGalaxyRowIndexes, List<Integer> noGalaxyColIndexes) {
		long sumShortestPaths = 0L;
		for (int i = 0; i < galaxySectors.size() - 1; i++) {
			Sector firstGalaxy = galaxySectors.get(i);
			for (int j = i + 1; j < galaxySectors.size(); j++) {
				Sector secondGalaxy = galaxySectors.get(j);

				long manhattanDistance = Math.abs(firstGalaxy.getX() - secondGalaxy.getX()) + Math.abs(firstGalaxy.getY() - secondGalaxy.getY());
				long offset = getOffset(baseOffset, noGalaxyRowIndexes, noGalaxyColIndexes, firstGalaxy, secondGalaxy);

				sumShortestPaths += (manhattanDistance + offset - (offset / baseOffset));
			}
		}
		return sumShortestPaths;
	}

	private static long getOffset(long baseOffset, List<Integer> noGalaxyRowIndexes, List<Integer> noGalaxyColIndexes, Sector firstGalaxy, Sector secondGalaxy) {
		long offset = 0L;
		for (Integer j : noGalaxyColIndexes) {
			if (Math.min(firstGalaxy.getX(), secondGalaxy.getX()) < j && j < Math.max(firstGalaxy.getX(), secondGalaxy.getX())) {
				offset += baseOffset;
			}
		}

		for (Integer i : noGalaxyRowIndexes) {
			if (Math.min(firstGalaxy.getY(), secondGalaxy.getY()) < i && i < Math.max(firstGalaxy.getY(), secondGalaxy.getY())) {
				offset += baseOffset;
			}
		}
		return offset;
	}

	private static List<Sector> parseGalaxySectors(List<String> fileLines) {
		List<Sector> galaxySectors = new ArrayList<>();
		for (int j = 0; j < fileLines.size(); j++) {
			for (int i = 0; i < fileLines.get(j).length(); i++) {
				if (fileLines.get(j).charAt(i) == '#')
					galaxySectors.add(new Sector(i, j));
			}
		}
		return galaxySectors;
	}

	private static List<Integer> parseNoGalaxyColIndexes(List<String> fileLines) {
		List<Integer> noGalaxyColIndexes = new ArrayList<>();
		for (int i = 0; i < fileLines.get(0).length(); i++) {
			boolean galaxyFound = false;
			for (int j = 0; j < fileLines.size(); j++) {
				if (fileLines.get(j).charAt(i) == '#') {
					galaxyFound = true;
					break;
				}
			}
			if (!galaxyFound)
				noGalaxyColIndexes.add(i);
		}
		return noGalaxyColIndexes;
	}

	private static List<Integer> parseNoGalaxyRowIndexes(List<String> fileLines) {
		List<Integer> noGalaxyRowIndexes = new ArrayList<>();
		for (int j = 0; j < fileLines.size(); j++) {
			String line = fileLines.get(j).trim();
			boolean galaxyFound = false;
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '#') {
					galaxyFound = true;
					break;
				}
			}
			if (!galaxyFound)
				noGalaxyRowIndexes.add(j);
		}
		return noGalaxyRowIndexes;
	}
}
