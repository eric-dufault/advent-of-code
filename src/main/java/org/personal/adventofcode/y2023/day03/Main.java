package org.personal.adventofcode.y2023.day03;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2023\\day03\\input.txt"));
		List<PartNumber> partNumbers = parseNumbers(fileLines);

		partA(partNumbers);
		partB(partNumbers);
	}

	private static void partA(List<PartNumber> partNumbers) {
		Integer sumParts = partNumbers.stream().filter(pn -> pn.getAdjacentConnector() != null).map(PartNumber::getValue).reduce(0, Integer::sum);
		System.out.println(sumParts);
	}
	
	private static void partB(List<PartNumber> partNumbers) {
		Set<Cell> gearCells = partNumbers.stream().map(pn -> pn.getAdjacentConnector('*')).filter(Objects::nonNull).collect(Collectors.toSet());

		int gearRatioSum = 0;
		for (Cell gear : gearCells) {
			Set<PartNumber> partNumbersConnectedToGear = partNumbers.stream().filter(pn -> gear.equals(pn.getAdjacentConnector('*'))).collect(Collectors.toSet());
			if (partNumbersConnectedToGear.size() == 2) {
				int gearRatio = 1;
				for (PartNumber pn : partNumbersConnectedToGear) {
					gearRatio *= pn.getValue();
				}
				gearRatioSum += gearRatio;
			}
		}
		System.out.println(gearRatioSum);
	}

	private static List<PartNumber> parseNumbers(List<String> fileLines) {
		List<PartNumber> partNumbers = new ArrayList<>();

		for (int j = 0; j < fileLines.size(); j++) {
			String line = fileLines.get(j);
			PartNumber partNumber = new PartNumber();
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				if (Character.isDigit(c))
					partNumber.addDigit(new Cell(c, new Coordinate(i,j)));
				else {
					if (!partNumber.isEmpty())
						partNumbers.add(partNumber);
					partNumber = new PartNumber();
				}
			}
			if (!partNumber.isEmpty())
				partNumbers.add(partNumber);
		}

		setAdjacentSymbols(fileLines, partNumbers);

		return partNumbers;
	}

	private static boolean inBounds(List<String> fileList, Integer i, Integer j) {
		return 0 <= i && i < fileList.get(0).length() && 0 <= j && j < fileList.size();
	}

	private static void setAdjacentSymbols(List<String> fileLines, List<PartNumber> numbers) {
		for (PartNumber number : numbers) {
			for (Cell digit : number.getDigits()) {
				if (number.getAdjacentConnector() != null)
					continue;

				int i = digit.getCoordinate().getI();
				int j = digit.getCoordinate().getJ();

				//j, i-1 (left)
				if (inBounds(fileLines, i-1, j)) {
					char c = fileLines.get(j).charAt(i-1);
					if (c != '.' && !Character.isDigit(c)) {
						digit.setConnectorCell(new Cell(c, new Coordinate(i-1, j)));
					}
				}

				//j-1, i-1  (top left)
				if (inBounds(fileLines, i-1, j-1)) {
					char c = fileLines.get(j-1).charAt(i-1);
					if (c != '.' && !Character.isDigit(c)) {
						digit.setConnectorCell(new Cell(c, new Coordinate(i-1, j-1)));
					}
				}

				//j-1, i (top)
				if (inBounds(fileLines, i, j-1)) {
					char c = fileLines.get(j-1).charAt(i);
					if (c != '.' && !Character.isDigit(c)) {
						digit.setConnectorCell(new Cell(c, new Coordinate(i, j-1)));
					}
				}

				//j-1, i + 1 (top right)
				if (inBounds(fileLines, i+1, j-1)) {
					char c = fileLines.get(j-1).charAt(i+1);
					if (c != '.' && !Character.isDigit(c)) {
						digit.setConnectorCell(new Cell(c, new Coordinate(i+1, j-1)));
					}
				}

				//j, i+1 (right)
				if (inBounds(fileLines, i+1, j)) {
					char c = fileLines.get(j).charAt(i+1);
					if (c != '.' && !Character.isDigit(c)) {
						digit.setConnectorCell(new Cell(c, new Coordinate(i+1, j)));
					}
				}

				//j+1, i+1 (bottom right)
				if (inBounds(fileLines, i+1, j+1)) {
					char c = fileLines.get(j + 1).charAt(i + 1);
					if (c != '.' && !Character.isDigit(c)) {
						digit.setConnectorCell(new Cell(c, new Coordinate(i+1, j+1)));
					}
				}

				//j+1, i (bottom)
				if (inBounds(fileLines, i, j+1)) {
					char c = fileLines.get(j + 1).charAt(i);
					if (c != '.' && !Character.isDigit(c)) {
						digit.setConnectorCell(new Cell(c, new Coordinate(i, j+1)));
					}
				}

				//j+1, i-1 (bottom left)
				if (inBounds(fileLines, i-1, j+1)) {
					char c = fileLines.get(j+1).charAt(i-1);
					if (c != '.' && !Character.isDigit(c)) {
						digit.setConnectorCell(new Cell(c, new Coordinate(i-1, j+1)));
					}
				}
			}
		}
	}
}
