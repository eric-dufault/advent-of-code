package org.personal.adventofcode.y2023.day02;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private final Integer id;
	private List<GameRound> gameRounds;
	private GameRound fewestPossibleCubesGameRound;

	public Game(Integer id) {
		this.id = id;
	}

	public void addGameRound(GameRound gameRound) {
		getGameRounds().add(gameRound);
	}

	public boolean isPossible(Integer redLimit, Integer greenLimit, Integer blueLimit) {
		return !isNotPossible(redLimit, greenLimit, blueLimit);
	}

	boolean isNotPossible(Integer redLimit, Integer greenLimit, Integer blueLimit) {
		return getGameRounds().stream().anyMatch(gameRound ->
			gameRound.getRed() > redLimit || gameRound.getGreen() > greenLimit || gameRound.getBlue() > blueLimit
		);
	}

	public GameRound getFewestPossibleCubesGameRound() {
		if (fewestPossibleCubesGameRound == null) {
			fewestPossibleCubesGameRound = new GameRound(
					getFewestRedPossibleForGame(),
					getFewestGreenPossibleForGame(),
					getFewestBluePossibleForGame()
			);
		}
		return fewestPossibleCubesGameRound;
	}

	Integer getFewestRedPossibleForGame() {
		return getGameRounds().stream().map(GameRound::getRed).max(Integer::compareTo).orElse(null);
	}

	Integer getFewestGreenPossibleForGame() {
		return getGameRounds().stream().map(GameRound::getGreen).max(Integer::compareTo).orElse(null);
	}

	Integer getFewestBluePossibleForGame() {
		return getGameRounds().stream().map(GameRound::getBlue).max(Integer::compareTo).orElse(null);
	}

	public Integer getId() {
		return id;
	}

	public List<GameRound> getGameRounds() {
		if (gameRounds == null) {
			gameRounds = new ArrayList<>();
		}
		return gameRounds;
	}
}
