package org.personal.adventofcode.y2023.day07.partA;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class OnePairFunction implements Function<Hand, Boolean> {

	@Override
	public Boolean apply(Hand hand) {
		Map<CardType, Integer> cardTypes = new HashMap<>();
		for (CardType cardType : hand.getCards()) {
			if (cardTypes.containsKey(cardType))
				cardTypes.put(cardType, cardTypes.get(cardType) + 1);
			else
				cardTypes.put(cardType, 1);
		}

		return cardTypes.size() == 4;
	}
}
