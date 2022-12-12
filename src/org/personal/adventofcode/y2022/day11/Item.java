package org.personal.adventofcode.y2022.day11;

import java.math.BigInteger;

public class Item {
	private BigInteger worryLevel;

	public Item(BigInteger worryLevel) {
		this.worryLevel = worryLevel;
	}

	public void applyRelief() {
		worryLevel = worryLevel.divide(BigInteger.valueOf(3));
	}

	public void applyRelief(BigInteger reliefModulo) {
		worryLevel = worryLevel.mod(reliefModulo);
	}

	public BigInteger getWorryLevel() {
		return worryLevel;
	}

	public void setWorryLevel(BigInteger worryLevel) {
		this.worryLevel = worryLevel;
	}
}
