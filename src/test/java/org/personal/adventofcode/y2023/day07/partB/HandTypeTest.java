package org.personal.adventofcode.y2023.day07.partB;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandTypeTest {

	@Test
	public void testHand_KTJJT() {
		Hand hand = new Hand(1);
		hand.addCard(CardType.KING);
		hand.addCard(CardType.THIEF);
		hand.addCard(CardType.JOKER);
		hand.addCard(CardType.JOKER);
		hand.addCard(CardType.THIEF);

		assertFalse(HandType.FIVE_OF_A_KIND.getFunction().apply(hand));
		assertTrue(HandType.FOUR_OF_A_KIND.getFunction().apply(hand));
		assertFalse(HandType.FULL_HOUSE.getFunction().apply(hand));
		assertFalse(HandType.THREE_OF_A_KIND.getFunction().apply(hand));
		assertFalse(HandType.TWO_PAIR.getFunction().apply(hand));
		assertFalse(HandType.ONE_PAIR.getFunction().apply(hand));
		assertFalse(HandType.HIGH_CARD.getFunction().apply(hand));
	}

	@Test
	public void testHand_TTJJT() {
		Hand hand = new Hand(1);
		hand.addCard(CardType.THIEF);
		hand.addCard(CardType.THIEF);
		hand.addCard(CardType.JOKER);
		hand.addCard(CardType.JOKER);
		hand.addCard(CardType.THIEF);

		assertTrue(HandType.FIVE_OF_A_KIND.getFunction().apply(hand));
		assertFalse(HandType.FOUR_OF_A_KIND.getFunction().apply(hand));
		assertFalse(HandType.FULL_HOUSE.getFunction().apply(hand));
		assertFalse(HandType.THREE_OF_A_KIND.getFunction().apply(hand));
		assertFalse(HandType.TWO_PAIR.getFunction().apply(hand));
		assertFalse(HandType.ONE_PAIR.getFunction().apply(hand));
		assertFalse(HandType.HIGH_CARD.getFunction().apply(hand));
	}

	@Test
	public void testHand_JJJJJ() {
		Hand hand = new Hand(1);
		hand.addCard(CardType.JOKER);
		hand.addCard(CardType.JOKER);
		hand.addCard(CardType.JOKER);
		hand.addCard(CardType.JOKER);
		hand.addCard(CardType.JOKER);

		assertTrue(HandType.FIVE_OF_A_KIND.getFunction().apply(hand));
		assertFalse(HandType.FOUR_OF_A_KIND.getFunction().apply(hand));
		assertFalse(HandType.FULL_HOUSE.getFunction().apply(hand));
		assertFalse(HandType.THREE_OF_A_KIND.getFunction().apply(hand));
		assertFalse(HandType.TWO_PAIR.getFunction().apply(hand));
		assertFalse(HandType.ONE_PAIR.getFunction().apply(hand));
		assertFalse(HandType.HIGH_CARD.getFunction().apply(hand));
	}

	@Test
	public void testHand_JJJ7A() {
		Hand hand = new Hand(1);
		hand.addCard(CardType.JOKER);
		hand.addCard(CardType.JOKER);
		hand.addCard(CardType.JOKER);
		hand.addCard(CardType.SEVEN);
		hand.addCard(CardType.ACE);

		assertFalse(HandType.FIVE_OF_A_KIND.getFunction().apply(hand));
		assertTrue(HandType.FOUR_OF_A_KIND.getFunction().apply(hand));
		assertFalse(HandType.FULL_HOUSE.getFunction().apply(hand));
		assertFalse(HandType.THREE_OF_A_KIND.getFunction().apply(hand));
		assertFalse(HandType.TWO_PAIR.getFunction().apply(hand));
		assertFalse(HandType.ONE_PAIR.getFunction().apply(hand));
		assertFalse(HandType.HIGH_CARD.getFunction().apply(hand));
	}
}
