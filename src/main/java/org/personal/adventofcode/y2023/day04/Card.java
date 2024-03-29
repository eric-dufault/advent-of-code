package org.personal.adventofcode.y2023.day04;

import java.util.ArrayList;
import java.util.List;

public class Card {
	private final Integer number;
	private List<Integer> winningNumbers;
	private List<Integer> ownedNumbers;

	public Card(Integer number) {
		this.number = number;
	}


	public int getPoints() {
		int matches = getOwnedWinningNumbers().size();
		return matches == 0 ? 0 : (int) Math.pow(2.0, matches - 1);
	}

	public List<Integer> getOwnedWinningNumbers() {
		return getOwnedNumbers().stream().filter(on -> getWinningNumbers().contains(on)).toList();
	}

	public Integer getNumber() {
		return number;
	}

	public void addWinningNumber(Integer winningNumber) {
		getWinningNumbers().add(winningNumber);
	}

	public void addOwnedNumber(Integer ownedNumber) {
		getOwnedNumbers().add(ownedNumber);
	}

	public List<Integer> getWinningNumbers() {
		if (winningNumbers == null)
			winningNumbers = new ArrayList<>(12);
		return winningNumbers;
	}

	public List<Integer> getOwnedNumbers() {
		if (ownedNumbers == null)
			ownedNumbers = new ArrayList<>(30);
		return ownedNumbers;
	}

}
