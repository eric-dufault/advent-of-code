package org.personal.adventofcode.y2022.day11;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) throws Exception {
		List<String> lines = Files.readAllLines(Paths.get("src\\main\\resources\\y2022\\day11\\input11.txt"));
		partA(lines);
		partB(lines);
	}

	private static void partA(List<String> lines) {
		List<Monkey> monkeys = createMonkeys(lines);
		int rounds = 20;
		for (int i = 0; i < rounds; i++) {
			for (Monkey monkey : monkeys) {
				Item item = monkey.getNextItem();
				while (item != null) {
					monkey.inspect(item);
					item.applyRelief();
					monkey.toss(item);
					item = monkey.getNextItem();
				}
			}
		}

		Collections.sort(monkeys, Collections.reverseOrder());
		System.out.println(monkeys.get(0).getInspections().multiply(monkeys.get(1).getInspections()));
	}

	private static void partB(List<String> lines) {
		List<Monkey> monkeys = createMonkeys(lines);
		BigInteger leastCommonDivisor = monkeys.stream().map(Monkey::getTestDivisor).reduce(BigInteger.ONE, BigInteger::multiply);
		int rounds = 10000;
		for (int i = 0; i < rounds; i++) {
			for (Monkey monkey : monkeys) {
				Item item = monkey.getNextItem();
				while (item != null) {
					monkey.inspect(item);
					item.applyRelief(leastCommonDivisor);
					monkey.toss(item);
					item = monkey.getNextItem();
				}
			}
		}

		Collections.sort(monkeys, Collections.reverseOrder());
		System.out.println(monkeys.get(0).getInspections().multiply(monkeys.get(1).getInspections()));
	}

	private static Monkey getMonkey(int targetKey, List<Monkey> monkeys) {
		return monkeys.stream().filter(m ->m.getKey() == targetKey).findFirst().orElse(null);
	}

	private static List<Monkey> createMonkeys(List<String> lines) {
		List<Monkey> monkeys = new ArrayList<>();
		for (int i = 6; i <= lines.size(); i += 7) {
			monkeys.add(createMonkey(lines.get(i-6)));
		}

		for(int i = 6; i <= lines.size(); i += 7) {
			Monkey monkey = getMonkey(Integer.parseInt(Character.toString(lines.get(i-6).charAt(7))), monkeys);
			createItems(lines.get(i - 5).trim(), monkey);
			createOperation(lines.get(i - 4).trim(), monkey);
			createTestDivisor(lines.get(i - 3).trim(), monkey);
			createTargetMonkeys(lines.get(i - 2).trim(), lines.get(i - 1).trim(), monkey, monkeys);
		}
		return monkeys;
	}

	private static Monkey createMonkey(String monkeyString) {
		return new Monkey(Integer.parseInt(Character.toString(monkeyString.charAt(7))));
	}

	private static void createItems(String startingItems, Monkey monkey) {
		String[] firstSplit = startingItems.split(":");
		String[] worryLevels = firstSplit[1].trim().split(",");
		for (String worryLevel : worryLevels) {
			monkey.addItem(new Item(new BigInteger(worryLevel.trim())));
		}
	}

	private static void createOperation(String operationString, Monkey monkey) {
		String[] equationParts = operationString.split("=");
		String[] rightHandSideParts = equationParts[1].trim().split(" ");

		OperationType operationType = null;
		for (OperationType type : OperationType.values()) {
			if (type.getSymbol().equals(rightHandSideParts[1])) {
				operationType = type;
				break;
			}
		}

		BigInteger secondOperand = getBigInteger(rightHandSideParts[2]);
		monkey.setOperation(new Operation(operationType, secondOperand));
	}

	private static void createTestDivisor(String testString, Monkey monkey) {
		String[] testStringParts = testString.split(" ");
		monkey.setTestDivisor(new BigInteger(testStringParts[3]));
	}

	private static void createTargetMonkeys(String trueString, String falseString, Monkey monkey, List<Monkey> monkeys) {
		String[] trueStringSplit = trueString.split(" ");
		monkey.setTrueMonkey(getMonkey(Integer.parseInt(trueStringSplit[5]), monkeys));

		String[] falseStringSplit = falseString.split(" ");
		monkey.setFalseMonkey(getMonkey(Integer.parseInt(falseStringSplit[5]), monkeys));
	}

	private static BigInteger getBigInteger(String string) {
		BigInteger result = null;
		try {
			result = new BigInteger(string);
		}
		catch (NumberFormatException e) {/* no op */}
		return result;
	}
}
