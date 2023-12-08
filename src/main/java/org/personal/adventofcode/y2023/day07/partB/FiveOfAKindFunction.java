package org.personal.adventofcode.y2023.day07.partB;

import java.util.Map;
import java.util.function.Function;

public class FiveOfAKindFunction extends BaseHandFunction implements Function<Hand, Boolean> {

	@Override
	public Boolean apply(Hand hand) {
		Map<CardType, Integer> cardTypes = getCardTypes(hand);
		return cardTypes.size() == 1 && cardTypes.containsValue(5);
	}
}
