package org.personal.adventofcode.y2023.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

	@Test
	public void testGame_possible() {
		Game game = new Game(1);
		game.addGameRound(new GameRound(20, 8, 6));

		boolean actual = game.isPossible(12, 13, 14);

		assertFalse(actual);
	}

	@Test
	public void testGame_notPossible() {
		Game game = new Game(1);
		game.addGameRound(new GameRound(6, 3, 0));

		boolean actual = game.isPossible(12, 13, 14);

		assertTrue(actual);
	}
}
