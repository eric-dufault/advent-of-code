package org.personal.adventofcode.y2023.day07.partB;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseHandFunction {

	Map<CardType, Integer> getCardTypes(Hand hand) {
		Map<CardType, Integer> cardTypes = new HashMap<>();
		for (CardType cardType : hand.getCards()) {
			if (cardTypes.containsKey(cardType))
				cardTypes.put(cardType, cardTypes.get(cardType) + 1);
			else
				cardTypes.put(cardType, 1);
		}

		if (cardTypes.containsKey(CardType.JOKER)) {
			Map.Entry<CardType, Integer> max = null;
			for (Map.Entry<CardType, Integer> entry : cardTypes.entrySet()) {
				if ((max == null || entry.getValue() > max.getValue()) && !CardType.JOKER.equals(entry.getKey()))
					max = entry;
			}

			if (max != null) {
				cardTypes.put(max.getKey(), cardTypes.get(CardType.JOKER) + cardTypes.get(max.getKey()));
			} else {
				cardTypes.put(CardType.ACE, hand.getCards().size());
			}
			cardTypes.remove(CardType.JOKER);
		}
		return cardTypes;
	}
}
