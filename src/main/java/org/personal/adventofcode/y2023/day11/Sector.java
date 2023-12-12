package org.personal.adventofcode.y2023.day11;

public class Sector {
	private int x;
	private int y;

	public Sector(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (!(o instanceof Sector))
			return false;

		Sector other = (Sector) o;
		return getX() == other.getX() && getY() == other.getY();
	}

	public int hashCode() {
		return (31 + getX()) + getY();
	}

	public String toString() {
		return "(" + getX() + "," + getY() + ")";
	}
}
