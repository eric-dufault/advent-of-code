package org.personal.adventofcode.y2023.day03;

public class Cell {
	private Character symbol;
	private Coordinate coordinate;
	private Cell connectorCell = null;

	public Cell(Character symbol, Coordinate coordinate) {
		this.symbol = symbol;
		this.coordinate = coordinate;
	}

	public Character getSymbol() {
		return symbol;
	}

	public void setSymbol(Character symbol) {
		this.symbol = symbol;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Cell getConnectorCell() {
		return connectorCell;
	}

	public void setConnectorCell(Cell connectorCell) {
		this.connectorCell = connectorCell;
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Cell))
			return false;

		Cell other = (Cell) o;
		return getSymbol() == other.getSymbol() && getCoordinate().equals(other.getCoordinate());
	}

	public int hashCode() {
		return getSymbol() + getCoordinate().hashCode();
	}
}
