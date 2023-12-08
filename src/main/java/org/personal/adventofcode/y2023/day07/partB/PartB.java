package org.personal.adventofcode.y2023.day07.partB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartB {

	public static void execute(List<String> fileLines) {
		List<Hand> hands = parseHands(fileLines);
		calculateTotalWinnings(hands);
	}

	private static void calculateTotalWinnings(List<Hand> hands) {
		Collections.sort(hands);

		int totalWinnings = 0;
		for (int rank = 1; rank <= hands.size(); rank++) {
			totalWinnings += (rank * hands.get(rank-1).getBid());
		}
		System.out.println(totalWinnings);
	}

	private static void calculateHandType(Hand hand) {
		for (HandType handType : HandType.values()) {
			boolean isType = handType.getFunction().apply(hand);
			if (isType) {
				hand.setHandType(handType);
				break;
			}
		}
	}

	private static List<Hand> parseHands(List<String> fileLines) {
		List<Hand> hands = new ArrayList<>();
		for (String line : fileLines) {
			hands.add(parseHand(line));
		}
		return hands;
	}

	private static Hand parseHand(String line) {
		String[] handBidParts = line.trim().split("\\s+");

		Hand hand = new Hand(Integer.parseInt(handBidParts[1]));
		for (Character c : handBidParts[0].toCharArray()) {
			hand.addCard(CardType.findBySymbol(c));
		}

		calculateHandType(hand);

		return hand;
	}

}
