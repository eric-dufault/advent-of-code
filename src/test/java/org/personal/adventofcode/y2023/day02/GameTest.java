package org.personal.adventofcode.y2023.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

	@Test
	public void testGetFewestPossibleCubesGameRound() {
		Game game = new Game(1);
		game.addGameRound(new GameRound(20, 8, 6));
		game.addGameRound(new GameRound(4, 13, 5));
		game.addGameRound(new GameRound(1, 5, 0));

		GameRound actual = game.getFewestPossibleCubesGameRound();

		assertEquals(20, actual.getRed());
		assertEquals(13, actual.getGreen());
		assertEquals(6, actual.getBlue());
	}
}
