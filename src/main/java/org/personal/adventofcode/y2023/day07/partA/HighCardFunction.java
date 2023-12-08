package org.personal.adventofcode.y2023.day07.partA;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class HighCardFunction implements Function<Hand, Boolean> {

	@Override
	public Boolean apply(Hand hand) {
		Set<CardType> uniqueCardTypes = new HashSet<>(hand.getCards());
		return uniqueCardTypes.size() == 5;
	}
}
