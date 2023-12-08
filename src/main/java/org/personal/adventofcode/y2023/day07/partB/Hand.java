package org.personal.adventofcode.y2023.day07.partB;

import java.util.ArrayList;
import java.util.List;

public class Hand implements Comparable<Hand> {
	private List<CardType> cards;
	private Integer bid;
	private HandType handType;

	public Hand(Integer bid) {
		this.bid = bid;
	}

	public void addCard(CardType cardType) {
		getCards().add(cardType);
	}

	public List<CardType> getCards() {
		if (cards == null)
			cards = new ArrayList<>();
		return cards;
	}

	public Integer getBid() {
		return bid;
	}

	public HandType getHandType() {
		return handType;
	}

	public void setHandType(HandType handType) {
		this.handType = handType;
	}

	@Override
	public int compareTo(Hand o2) {
		int result = 0;
		if (getHandType().getStrength() == o2.getHandType().getStrength()) {
			for (int i = 0 ; i < getCards().size(); i++) {
				if (!getCards().get(i).equals(o2.getCards().get(i))) {
					result = Integer.compare(getCards().get(i).getStrength(), o2.getCards().get(i).getStrength());
					break;
				}
			}
		}
		else
			result = Integer.compare(getHandType().getStrength(), o2.getHandType().getStrength());

		return result;
	}
}
