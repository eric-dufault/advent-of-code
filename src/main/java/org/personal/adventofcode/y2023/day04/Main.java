package org.personal.adventofcode.y2023.day04;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2023\\day04\\input.txt"));
		List<Card> cards = parseCards(fileLines);

		partA(cards);
		partB(cards);
	}

	private static void partA(List<Card> cards) {
		Integer totalPoints = cards.stream().map(Card::getPoints).reduce(0, Integer::sum);
		System.out.println(totalPoints);
	}

	private static void partB(List<Card> cards) {
		Map<Integer, Integer> cardCounts = new HashMap<>();  //cardNumber, amountOfCards
		for (Card card : cards) {
			cardCounts.put(card.getNumber(), 1);
		}

		int maxCardNumber = cards.stream().map(Card::getNumber).max(Integer::compare).orElseThrow(IllegalArgumentException::new);
		for (Card card : cards) {
			int matches = card.getOwnedWinningNumbers().size();
			for (int i = 1; i <= matches && (card.getNumber() + i) <= maxCardNumber; i++) {
				int copies = cardCounts.get(card.getNumber());
				for (int j = 0; j < copies; j++) {
					cardCounts.put(card.getNumber() + i, cardCounts.get(card.getNumber() + i) + 1);
				}
			}
		}

		int totalCards = cardCounts.values().stream().reduce(Integer::sum).orElse(0);
		System.out.println(totalCards);
	}

	private static List<Card> parseCards(List<String> fileLines) {
		return fileLines.stream().map(Main::parseCard).toList();
	}

	private static Card parseCard(String line) {
		String[] colonParts = line.split(":");

		String[] cardParts = colonParts[0].trim().split("\\s+");
		Integer cardNumber = Integer.parseInt(cardParts[1].trim());
		Card card = new Card(cardNumber);

		String[] numberParts = colonParts[1].trim().split("\\|");

		String[] winningNumberParts = numberParts[0].trim().split("\\s+");
		for (String winningNumberPart : winningNumberParts)
			card.addWinningNumber(Integer.parseInt(winningNumberPart.trim()));

		String[] ownedNumberParts = numberParts[1].trim().split("\\s+");
		for (String ownedNumberPart : ownedNumberParts)
			card.addOwnedNumber(Integer.parseInt(ownedNumberPart.trim()));

		return card;
	}
}
