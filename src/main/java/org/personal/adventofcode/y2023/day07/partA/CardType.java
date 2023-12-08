package org.personal.adventofcode.y2023.day07.partA;

import java.util.HashMap;
import java.util.Map;

public enum CardType {
	ACE('A', 14),
	KING('K', 13),
	QUEEN('Q', 12),
	JACK('J', 11),
	THIEF('T', 10),
	NINE('9', 9),
	EIGHT('8', 8),
	SEVEN('7', 7),
	SIX('6', 6),
	FIVE('5', 5),
	FOUR('4', 4),
	THREE('3', 3),
	TWO('2', 2);

	private static final Map<Character, CardType> map = new HashMap<>();
	static {
		for (CardType ct : CardType.values()) {
			map.put(ct.getSymbol(), ct);
		}
	}

	private final char symbol;
	private final int strength;

	CardType(char symbol, int strength) {
		this.symbol = symbol;
		this.strength = strength;
	}

	public static CardType findBySymbol(char o) {
		return map.get(o);
	}


	public char getSymbol() {
		return symbol;
	}

	public int getStrength() {
		return strength;
	}

}
