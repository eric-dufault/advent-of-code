package org.personal.adventofcode.y2023.day10;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

	private static int WIDTH;
	private static int HEIGHT;

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2023\\day10\\input.txt"));

		WIDTH = fileLines.get(0).length();
		HEIGHT = fileLines.size();

		partA(fileLines);
	}

	private static void partA(List<String> fileLines) {
		Tile startingTile = getStartingTile(fileLines);
		Set<Tile> visited = new HashSet<>();
		visited.add(startingTile);

		Queue<Tile> queue = new ArrayDeque<>();  //FIFO
		queue.add(startingTile);
		while (!queue.isEmpty()) {
			Tile tile = queue.poll();

			//no end condition, go through whole cycle
			//visited will contain all vertices in graph

			List<Tile> connectedTiles = getConnectedTiles(tile, fileLines);
			for (Tile ct : connectedTiles) {
				if (!visited.contains(ct)) {
					visited.add(ct);
					queue.add(ct);
					ct.setDistance(tile.getDistance() + 1);
				}
			}
		}

		Tile farthestStepsTile = visited.stream().max(Comparator.comparing(Tile::getDistance)).orElseThrow(IllegalArgumentException::new);
		System.out.println(farthestStepsTile.getDistance());
	}

	private static List<Tile> getConnectedTiles(Tile tile, List<String> fileLines) {
		List<Tile> connectedTiles = new ArrayList<>();
		int i = tile.getCoordinate().getX();
		int j = tile.getCoordinate().getY();

		//right
		if (inBounds(i+1,j) && tile.getPipeType().isAcceptsRight() && PipeType.findByGlyph(fileLines.get(j).charAt(i+1)).isAcceptsLeft()) {
			connectedTiles.add(new Tile(PipeType.findByGlyph(fileLines.get(j).charAt(i+1)), new Coordinate(i+1, j)));
		}

		//down
		if (inBounds(i,j+1) && tile.getPipeType().isAcceptsDown() && PipeType.findByGlyph(fileLines.get(j+1).charAt(i)).isAcceptsUp()) {
			connectedTiles.add(new Tile(PipeType.findByGlyph(fileLines.get(j+1).charAt(i)), new Coordinate(i, j+1)));
		}

		//left
		if (inBounds(i-1,j) && tile.getPipeType().isAcceptsLeft() && PipeType.findByGlyph(fileLines.get(j).charAt(i-1)).isAcceptsRight()) {
			connectedTiles.add(new Tile(PipeType.findByGlyph(fileLines.get(j).charAt(i-1)), new Coordinate(i-1, j)));
		}

		//up
		if (inBounds(i,j-1) && tile.getPipeType().isAcceptsUp() && PipeType.findByGlyph(fileLines.get(j-1).charAt(i)).isAcceptsDown()) {
			connectedTiles.add(new Tile(PipeType.findByGlyph(fileLines.get(j-1).charAt(i)), new Coordinate(i, j-1)));
		}
		return connectedTiles;
	}

	private static Tile getStartingTile(List<String> fileLines) {
		Tile tile = null;
		for (int j = 0; j < fileLines.size(); j++) {
			String line = fileLines.get(j).trim();
			for (int i = 0; i < line.length(); i++) {
				PipeType currentPipeType = PipeType.findByGlyph(line.charAt(i));
				if (PipeType.STARTING_POSITION.equals(currentPipeType)) {
					tile = new Tile(currentPipeType, new Coordinate(i, j));
					break;
				}
			}
		}
		return tile;
	}

	public static boolean inBounds(int i, int j) {
		return 0 <= i && i < WIDTH && 0 <= j && j < HEIGHT;
	}
}
