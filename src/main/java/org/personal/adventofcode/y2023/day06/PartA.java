package org.personal.adventofcode.y2023.day06;

import java.util.ArrayList;
import java.util.List;

public class PartA {

	public static void execute(List<String> fileLines) {
		List<Race> races = parseRaces(fileLines);

		long margin = 1L;
		for (Race race : races) {
			for (long velocity = 0; velocity < race.getTimeAllotted() - 1; velocity++) {
				race.addOutcome(new Outcome(velocity, race.getTimeAllotted() - velocity));
			}
			margin *= race.getWinningOutcomes().size();
		}
		System.out.println(margin);
	}

	private static List<Race> parseRaces(List<String> fileLines) {
		List<Race> races = new ArrayList<>();

		String[] timeParts = fileLines.get(0).split(":");
		String[] individualTimeParts = timeParts[1].trim().split("\\s+");

		String[] distanceParts = fileLines.get(1).split(":");
		String[] individualDistanceParts = distanceParts[1].trim().split("\\s+");

		for (int i = 0; i < individualTimeParts.length; i++) {
			races.add(new Race(Long.parseLong(individualTimeParts[i]), Long.parseLong(individualDistanceParts[i])));
		}

		return races;
	}
}
