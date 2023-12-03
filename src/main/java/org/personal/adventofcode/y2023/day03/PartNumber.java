package org.personal.adventofcode.y2023.day03;

import java.util.ArrayList;
import java.util.List;

public class PartNumber {
	private List<Cell> digits;


	public Cell getAdjacentConnector() {
		Cell adjacentConnector = null;
		for (Cell digit : getDigits()) {
			if (digit.getConnectorCell() != null) {
				adjacentConnector = digit.getConnectorCell();
				break;
			}
		}
		return adjacentConnector;
	}

	public Cell getAdjacentConnector(Character symbol) {
		Cell adjacentConnector = null;
		for (Cell digit : getDigits()) {
			if (digit.getConnectorCell() != null && digit.getConnectorCell().getSymbol().equals(symbol)) {
				adjacentConnector = digit.getConnectorCell();
				break;
			}
		}
		return adjacentConnector;
	}

	public Integer getValue() {
		StringBuilder sb = new StringBuilder();
		for (Cell digit : getDigits()) {
			sb.append(digit.getSymbol());
		}
		return Integer.parseInt(sb.toString());
	}

	public void addDigit(Cell digit) {
		getDigits().add(digit);
	}

	public boolean isEmpty() {
		return getDigits().isEmpty();
	}

	public List<Cell> getDigits() {
		if (digits == null)
			digits = new ArrayList<>();
		return digits;
	}
}
