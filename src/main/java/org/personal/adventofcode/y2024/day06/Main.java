package org.personal.adventofcode.y2024.day06;

import org.personal.helpers.coord.Coord;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	private static final char UP = '^';
	private static final char DOWN = 'v';
	private static final char LEFT = '<';
	private static final char RIGHT = '>';

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2024\\day06\\input.txt"));

		Set<Coord> path = new HashSet<>();
		Guard guard = new Guard();
		Set<Coord> obstructions = new HashSet<>();
		for (int x = 0; x < fileLines.size(); x++) {
			for (int y = 0; y < fileLines.get(0).length(); y++) {
				if (fileLines.get(x).charAt(y) == '#') {
					obstructions.add(new Coord(x, y));
				}
				else if (fileLines.get(x).charAt(y) == UP) {
					guard.currentPosition = new Coord(x, y);
					guard.facing = UP;
					path.add(guard.currentPosition);
				}
				else if (fileLines.get(x).charAt(y) == DOWN) {
					guard.currentPosition = new Coord(x, y);
					guard.facing = DOWN;
					path.add(guard.currentPosition);
				}
				else if (fileLines.get(x).charAt(y) == LEFT) {
					guard.currentPosition = new Coord(x, y);
					guard.facing = LEFT;
					path.add(guard.currentPosition);
				}
				else if (fileLines.get(x).charAt(y) == RIGHT) {
					guard.currentPosition = new Coord(x, y);
					guard.facing = RIGHT;
					path.add(guard.currentPosition);
				}
			}
		}

		while (true) {
			if (guard.facing == UP) {
				Coord nextStep = new Coord(guard.currentPosition.getX()-1, guard.currentPosition.getY());
				if (obstructions.contains(nextStep)) {
					guard.facing = RIGHT;
				} else {
					if (!inBounds(nextStep, fileLines)) {
						break;
					}
					guard.currentPosition = nextStep;
					path.add(nextStep);
				}
			}
			else if (guard.facing == RIGHT) {
				Coord nextStep = new Coord(guard.currentPosition.getX(), guard.currentPosition.getY()+1);
				if (obstructions.contains(nextStep)) {
					guard.facing = DOWN;
				} else {
					if (!inBounds(nextStep, fileLines)) {
						break;
					}
					guard.currentPosition = nextStep;
					path.add(nextStep);
				}
			}
			else if (guard.facing == DOWN) {
				Coord nextStep = new Coord(guard.currentPosition.getX()+1, guard.currentPosition.getY());
				if (obstructions.contains(nextStep)) {
					guard.facing = LEFT;
				} else {
					if (!inBounds(nextStep, fileLines)) {
						break;
					}
					guard.currentPosition = nextStep;
					path.add(nextStep);
				}
			}
			else if (guard.facing == LEFT) {
				Coord nextStep = new Coord(guard.currentPosition.getX(), guard.currentPosition.getY()-1);
				if (obstructions.contains(nextStep)) {
					guard.facing = UP;
				} else {
					if (!inBounds(nextStep, fileLines)) {
						break;
					}
					guard.currentPosition = nextStep;
					path.add(nextStep);
				}
			}
		}

		System.out.println(path.size());
	}

	private static void print(List<String> fileLines, Set<Coord> path) {
		for (int x = 0; x < fileLines.size(); x++) {
			for (int y = 0; y < fileLines.get(0).length(); y++) {
				if (path.contains(new Coord(x,y)))
					System.out.print('X');
				else
					System.out.print(fileLines.get(x).charAt(y));
			}
			System.out.println();
		}
	}

	private static boolean inBounds(Coord c, List<String> fileLines) {
		return inBounds(c.getX(), c.getY(), fileLines);
	}

	private static boolean inBounds(int x, int y, List<String> fileLines) {
		return 0 <= x && x < fileLines.size() && 0 <= y && y < fileLines.get(0).length();
	}

	private static class Guard {
		private Coord currentPosition;
		private char facing;
	}
}
