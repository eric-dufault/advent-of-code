package org.personal.adventofcode.y2023.day03;

import java.util.Objects;

public class Coordinate {
	private Integer i;
	private Integer j;

	public Coordinate() {}

	public Coordinate(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public Integer getJ() {
		return j;
	}

	public void setJ(Integer j) {
		this.j = j;
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Coordinate))
			return false;

		Coordinate other = (Coordinate) o;
		return Objects.equals(getI(), other.getI()) && Objects.equals(getJ(), other.getJ());
	}

	public int hashCode() {
		return (31 * i) + j;
	}
}