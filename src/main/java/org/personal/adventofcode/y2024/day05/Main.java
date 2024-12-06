package org.personal.adventofcode.y2024.day05;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2024\\day05\\input.txt"));

		Map<Integer, List<Integer>> orderingRules = parseOrderingRules(fileLines);
		List<Update> updates = parseUpdates(fileLines);

		int sum = updates.stream().filter(u -> u.isCorrectOrder(orderingRules)).mapToInt(Update::getMiddle).sum();
		System.out.println(sum);
	}


	private static Map<Integer, List<Integer>> parseOrderingRules(List<String> fileLines) {
		Map<Integer, List<Integer>> orderingRules = new HashMap<>();
		for (String line : fileLines) {
			if (line.isBlank())
				break;

			String[] split = line.split("\\|");

			Integer key = Integer.parseInt(split[0]);
			if (orderingRules.containsKey(key)) {
				List<Integer> values = orderingRules.get(key);
				values.add(Integer.parseInt(split[1]));
			} else {
				List<Integer> values = new ArrayList<>();
				values.add(Integer.parseInt(split[1]));
				orderingRules.put(key, values);
			}
		}
		return orderingRules;
	}

	private static List<Update> parseUpdates(List<String> fileLines) {
		List<Update> updates = new ArrayList<>();

		int index = 0;
		while (!fileLines.get(index).isBlank()) {
			index++;
		}

		for (int i = index + 1; i < fileLines.size(); i++) {
			String[] split = fileLines.get(i).split(",");
			Update update = new Update();
			for (int j = 0; j < split.length; j++) {
				update.addUpdate(Integer.parseInt(split[j]));
			}
			updates.add(update);
		}

		return updates;
	}

}
