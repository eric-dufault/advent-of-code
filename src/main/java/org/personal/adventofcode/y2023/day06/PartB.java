package org.personal.adventofcode.y2023.day06;

import java.util.List;

public class PartB {

	public static void execute(List<String> fileLines) {
		Race race = parseRace(fileLines);

		long winningOutcomesCount = 0L;
		for (long velocity = 0; velocity < race.getTimeAllotted(); velocity++) {
			long outcomeDistance = velocity * (race.getTimeAllotted() - velocity);
			if (outcomeDistance > race.getRecordDistance()) {
				winningOutcomesCount++;
			}
		}
		System.out.println(winningOutcomesCount);
	}

	private static Race parseRace(List<String> fileLines) {
		String[] timeParts = fileLines.get(0).split(":");
		String[] individualTimeParts = timeParts[1].trim().split("\\s+");

		String[] distanceParts = fileLines.get(1).split(":");
		String[] individualDistanceParts = distanceParts[1].trim().split("\\s+");

		StringBuilder timeBuilder = new StringBuilder();
		for (int i = 0; i < individualTimeParts.length; i++)
			timeBuilder.append(individualTimeParts[i].trim());

		StringBuilder distanceBuilder = new StringBuilder();
		for (int i = 0; i < individualDistanceParts.length; i++)
			distanceBuilder.append(individualDistanceParts[i].trim());

		return new Race(Long.parseLong(timeBuilder.toString()), Long.parseLong(distanceBuilder.toString()));
	}
}
