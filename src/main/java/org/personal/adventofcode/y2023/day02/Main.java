package org.personal.adventofcode.y2023.day02;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2023\\day02\\input.txt"));
		List<Game> games = parseGames(fileLines);

		partA(games);
		partB(games);
	}

	private static void partA(List<Game> games) {
		final Integer redLimit = 12;
		final Integer greenLimit = 13;
		final Integer blueLimit = 14;
		Integer idSum = games.stream()
				.filter(game -> game.isPossible(redLimit, greenLimit, blueLimit))
				.map(Game::getId)
				.reduce(0, Integer::sum);

		System.out.println(idSum);
	}

	private static void partB(List<Game> games) {
		Integer powerSum = games.stream()
				.map(Game::getFewestPossibleCubesGameRound)
				.map(GameRound::getPower)
				.reduce(0, Integer::sum);

		System.out.println(powerSum);
	}

	private static List<Game> parseGames(List<String> fileLines) {
		return fileLines.stream().map(line -> parseGame(line)).collect(Collectors.toList());
	}

	private static Game parseGame(String line) {
		String[] firstPart = line.split(":");

		Integer id = Integer.parseInt(firstPart[0].trim().split(" ")[1]);
		Game game = new Game(id);

		String[] rounds = firstPart[1].trim().split(";");
		for (String round : rounds) {
			String[] cubes = round.trim().split(",");

			Integer red = 0;
			Integer green = 0;
			Integer blue = 0;
			for (String cube : cubes) {
				String[] cubeParts = cube.trim().split(" ");
				switch (cubeParts[1]) {
					case "red":
						red = Integer.parseInt(cubeParts[0]);
						break;
					case "green":
						green = Integer.parseInt(cubeParts[0]);
						break;
					case "blue":
						blue = Integer.parseInt(cubeParts[0]);
						break;
				}
			}

			game.addGameRound(new GameRound(red, green, blue));
		}

		return game;
	}
}
