package org.personal.adventofcode.y2023.day07.partA;

import java.util.function.Function;

public enum HandType {
	FIVE_OF_A_KIND(7, new FiveOfAKindFunction()),
	FOUR_OF_A_KIND(6, new FourOfAKindFunction()),
	FULL_HOUSE(5, new FullHouseFunction()),
	THREE_OF_A_KIND(4, new ThreeOfAKindFunction()),
	TWO_PAIR(3, new TwoPairFunction()),
	ONE_PAIR(2, new OnePairFunction()),
	HIGH_CARD(1, new HighCardFunction());

	private final int strength;
	private final Function<Hand, Boolean> function;

	HandType(int strength, Function<Hand, Boolean> function) {
		this.strength = strength;
		this.function = function;
	}

	public int getStrength() {
		return strength;
	}

	public Function<Hand, Boolean> getFunction() {
		return function;
	}

}
