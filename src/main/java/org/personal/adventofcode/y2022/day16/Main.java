package org.personal.adventofcode.y2022.day16;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	private static final String FILENAME = "src\\main\\resources\\y2022\\day16\\input16.txt";

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get(FILENAME));
		List<Valve> valves = createValves(fileLines);

		Valve start = valves.stream().filter(v -> "AA".equals(v.getKey())).findFirst().orElse(null);

		List<Valve> flowValves = valves.stream().filter(v -> v.getFlowRate() > 0 || v.equals(start)).collect(Collectors.toList());

		Path bestPath = findBestPath(flowValves, start, 30);
		System.out.println(bestPath.getTotalFlow());
	}

	private static Path findBestPath(List<Valve> flowValves, Valve start, int rounds) {
		Path winner = new Path(start);

		Queue<Path> queue = new ArrayDeque<>();
		queue.add(new Path(start));

		while (!queue.isEmpty()) {
			Path current = queue.poll();

			current.getVisited().add(current.getValve());

			if (current.getTotalFlow() > winner.getTotalFlow())
				winner = current;

			for (Valve flowValve : flowValves) {
				Integer stepsToValve = current.getValve().getStepsToValve(flowValve);

				if (!current.getVisited().contains(flowValve)
						&& !current.getValve().equals(flowValve)
						&& (current.getSteps() + stepsToValve) <= rounds) {

					Path path = new Path(flowValve, current.getVisited());
					path.setSteps(current.getSteps() + stepsToValve);
					path.setFlow((current.getFlowRate() * stepsToValve) + current.getFlow());
					path.setFlowRate(current.getFlowRate() + flowValve.getFlowRate());

					path.setTotalFlow((path.getFlowRate() * (rounds - path.getSteps())) + path.getFlow());

					//optimization, only consider paths that might result as winners
					if (path.getTotalFlow() >= winner.getTotalFlow() || path.getSteps() < winner.getSteps()) {
						queue.add(path);
					}
				}
			}
		}

		return winner;
	}


	//determines shortest paths between all valves
	private static void calculateDistances(List<Valve> valves) {
		valves.forEach(Main::calculateDistances);
	}

	private static void calculateDistances(Valve valve) {
		//BFS setting "depth" from root vertex to other vertices in graph
		Queue<Valve> queue = new ArrayDeque<>();
		queue.add(valve);

		Set<Valve> visited = new HashSet<>();
		visited.add(valve);

		while (!queue.isEmpty()) {
			Valve v = queue.poll();

			Integer steps = valve.getStepsToValve(v);
			if (steps == null)
				steps = 1;

			for (Valve w : v.getAdjacentValves()) {
				if (!visited.contains(w)) {
					visited.add(w);
					queue.add(w);

					valve.setStepsToValve(w, steps + 1);
				}
			}
		}
	}

	private static List<Valve> createValves(List<String> fileLines) {
		List<Valve> valves = new ArrayList<>();

		for (String fileLine : fileLines) {
			String[] mainParts = fileLine.split(";");

			String[] valveParts = mainParts[0].split(" ");
			String[] rateParts = valveParts[4].split("=");
			Valve valve = new Valve(valveParts[1], Integer.parseInt(rateParts[1].replace(";","")));
			valves.add(valve);
		}

		for (String fileLine : fileLines) {
			String[] mainParts = fileLine.split(";");

			String[] valveParts = mainParts[0].split(" ");
			Valve valve = getValve(valves, valveParts[1]);

			String[] incidentsParts = mainParts[1].trim().split(" ");
			for (int i = 4; i < incidentsParts.length; i++) {
				String incidentValveKey = incidentsParts[i].replace(",", "");
				Valve incidentValve = getValve(valves, incidentValveKey);

				valve.addAdjacentValve(incidentValve);
			}
		}

		calculateDistances(valves);

		return valves;
	}

	private static Valve getValve(List<Valve> valves, String key) {
		return valves.stream().filter(v -> v.getKey().equals(key)).findFirst().orElse(null);
	}
}
