package org.personal.helpers.coord;

public class Coord {
	private int x;
	private int y;

	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Coord c))
			return false;

		return this.x == c.x && this.y == c.y;
	}

	public int hashCode() {
		return 31 * (this.x + this.y);
	}

}
