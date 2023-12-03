package org.personal.adventofcode.y2023.day02;

public class GameRound {
	private Integer red;
	private Integer green;
	private Integer blue;

	public GameRound(Integer red, Integer green, Integer blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public Integer getPower() {
		return red * green * blue;
	}

	public Integer getRed() {
		return red;
	}

	public void setRed(Integer red) {
		this.red = red;
	}

	public Integer getGreen() {
		return green;
	}

	public void setGreen(Integer green) {
		this.green = green;
	}

	public Integer getBlue() {
		return blue;
	}

	public void setBlue(Integer blue) {
		this.blue = blue;
	}
}
