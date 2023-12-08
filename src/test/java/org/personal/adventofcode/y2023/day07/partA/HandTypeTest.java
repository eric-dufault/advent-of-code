package org.personal.adventofcode.y2023.day07.partA;

import org.junit.jupiter.api.Test;
import org.personal.adventofcode.y2023.day07.partA.CardType;
import org.personal.adventofcode.y2023.day07.partA.Hand;
import org.personal.adventofcode.y2023.day07.partA.HandType;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandTypeTest {

	@Test
	public void testHand_A23A4() {
		Hand hand = new Hand(1);
		hand.addCard(CardType.ACE);
		hand.addCard(CardType.TWO);
		hand.addCard(CardType.THREE);
		hand.addCard(CardType.ACE);
		hand.addCard(CardType.FOUR);

		assertFalse(HandType.HIGH_CARD.getFunction().apply(hand));
		assertTrue(HandType.ONE_PAIR.getFunction().apply(hand));
		assertFalse(HandType.TWO_PAIR.getFunction().apply(hand));
		assertFalse(HandType.THREE_OF_A_KIND.getFunction().apply(hand));
		assertFalse(HandType.FULL_HOUSE.getFunction().apply(hand));
		assertFalse(HandType.FOUR_OF_A_KIND.getFunction().apply(hand));
		assertFalse(HandType.FIVE_OF_A_KIND.getFunction().apply(hand));
	}

	@Test
	public void testHand_AAAAA() {
		Hand hand = new Hand(1);
		hand.addCard(CardType.ACE);
		hand.addCard(CardType.ACE);
		hand.addCard(CardType.ACE);
		hand.addCard(CardType.ACE);
		hand.addCard(CardType.ACE);

		assertFalse(HandType.HIGH_CARD.getFunction().apply(hand));
		assertFalse(HandType.ONE_PAIR.getFunction().apply(hand));
		assertFalse(HandType.TWO_PAIR.getFunction().apply(hand));
		assertFalse(HandType.THREE_OF_A_KIND.getFunction().apply(hand));
		assertFalse(HandType.FULL_HOUSE.getFunction().apply(hand));
		assertFalse(HandType.FOUR_OF_A_KIND.getFunction().apply(hand));
		assertTrue(HandType.FIVE_OF_A_KIND.getFunction().apply(hand));
	}

	@Test
	public void testHand_2332() {
		Hand hand = new Hand(1);
		hand.addCard(CardType.TWO);
		hand.addCard(CardType.THREE);
		hand.addCard(CardType.THREE);
		hand.addCard(CardType.THREE);
		hand.addCard(CardType.TWO);

		assertFalse(HandType.HIGH_CARD.getFunction().apply(hand));
		assertFalse(HandType.ONE_PAIR.getFunction().apply(hand));
		assertFalse(HandType.TWO_PAIR.getFunction().apply(hand));
		assertFalse(HandType.THREE_OF_A_KIND.getFunction().apply(hand));
		assertTrue(HandType.FULL_HOUSE.getFunction().apply(hand));
		assertFalse(HandType.FOUR_OF_A_KIND.getFunction().apply(hand));
		assertFalse(HandType.FIVE_OF_A_KIND.getFunction().apply(hand));
	}
}
