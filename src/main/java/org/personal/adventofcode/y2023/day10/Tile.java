package org.personal.adventofcode.y2023.day10;

public class Tile {
	private Coordinate coordinate;
	private PipeType pipeType;
	private int distance;

	public Tile(PipeType pipeType, Coordinate coordinate) {
		this.pipeType = pipeType;
		this.coordinate = coordinate;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public PipeType getPipeType() {
		return pipeType;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (!(o instanceof Tile))
			return false;

		Tile other = (Tile) o;
		return getCoordinate().equals(other.getCoordinate());
	}

	public int hashCode() {
		return getCoordinate().hashCode();
	}
}
