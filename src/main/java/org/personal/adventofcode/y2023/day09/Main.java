package org.personal.adventofcode.y2023.day09;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2023\\day09\\input.txt"));
		partA(fileLines);
		partB(fileLines);
	}

	private static void partA(List<String> fileLines) {
		List<History> histories = parseHistories(fileLines);

		BigInteger sumValues = BigInteger.ZERO;
		for (History history : histories) {
			List<BigInteger> lastSequence = history.getLastSequence();
			BigInteger lastValue = lastSequence.get(lastSequence.size() - 1);
			for (int i = history.getSequences().size() - 2; i >= 0 ; i--) {
				List<BigInteger> currentSequence = history.getSequences().get(i);
				BigInteger currentLastValue = currentSequence.get(currentSequence.size() - 1);
				BigInteger newLastValue = lastValue.add(currentLastValue);

				currentSequence.add(newLastValue);

				lastValue = newLastValue;
			}

			sumValues = sumValues.add(history.getFirstSequence().get(history.getFirstSequence().size() - 1));
		}
		System.out.println(sumValues);
	}

	private static void partB(List<String> fileLines) {
		List<History> histories = parseHistories(fileLines);

		BigInteger sumValues = BigInteger.ZERO;
		for (History history : histories) {
			List<BigInteger> lastSequence = history.getLastSequence();
			BigInteger firstValue = lastSequence.get(0);
			for (int i = history.getSequences().size() - 2; i >= 0 ; i--) {
				List<BigInteger> currentSequence = history.getSequences().get(i);
				BigInteger currentFirstValue = currentSequence.get(0);
				BigInteger newFirstValue = currentFirstValue.subtract(firstValue);

				currentSequence.add(0, newFirstValue);

				firstValue = newFirstValue;
			}

			sumValues = sumValues.add(history.getFirstSequence().get(0));
		}
		System.out.println(sumValues);
	}

	private static List<History> parseHistories(List<String> fileLines) {
		return fileLines.stream().map(Main::parseHistory).toList();
	}

	private static History parseHistory(String line) {
		History history = new History(Arrays.stream(line.trim().split("\\s+")).map(BigInteger::new).toList());
		addSubsequences(history);
		return history;
	}

	private static void addSubsequences(History history) {
		List<BigInteger> sequence = history.getFirstSequence();
		while (!allZeroes(sequence)) {
			sequence = getSubSequence(sequence);
			history.addSequence(sequence);
		}
	}

	private static List<BigInteger> getSubSequence(List<BigInteger> sequence) {
		List<BigInteger> subsequence = new ArrayList<>();
		for (int i = 0; i < sequence.size() - 1; i++) {
			subsequence.add(sequence.get(i + 1).subtract(sequence.get(i)));
		}
		return subsequence;
	}

	private static boolean allZeroes(List<BigInteger> sequence) {
		return sequence.stream().allMatch(BigInteger.ZERO::equals);
	}

}
