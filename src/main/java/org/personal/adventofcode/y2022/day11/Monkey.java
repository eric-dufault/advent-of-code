package org.personal.adventofcode.y2022.day11;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

public class Monkey implements Comparable<Monkey> {
	private int key;
	private Queue<Item> items = new LinkedList<>();
	private Operation operation;
	private BigInteger testDivisor;
	private Monkey trueMonkey;
	private Monkey falseMonkey;
	private BigInteger inspections = BigInteger.ZERO;

	public Monkey(int key) {
		this.key = key;
	}

	public int getKey() {
		return this.key;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public Item getNextItem() {
		return items.poll();
	}

	public void inspect(Item item) {
		BigInteger secondOperand = operation.getSecondOperand() != null ? operation.getSecondOperand() : item.getWorryLevel();
		BigInteger worryLevel = operation.getOperationType().getBiFunction().apply(item.getWorryLevel(), secondOperand);
		item.setWorryLevel(worryLevel);

		inspections = inspections.add(BigInteger.ONE);
	}

	public void toss(Item item) {
		if (item.getWorryLevel().mod(testDivisor).equals(BigInteger.ZERO))
			trueMonkey.addItem(item);
		else
			falseMonkey.addItem(item);
	}

	@Override
	public int compareTo(Monkey o) {
		return this.getInspections().compareTo(o.getInspections());
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public BigInteger getTestDivisor() {
		return testDivisor;
	}

	public void setTestDivisor(BigInteger testDivisor) {
		this.testDivisor = testDivisor;
	}

	public void setTrueMonkey(Monkey trueMonkey) {
		this.trueMonkey = trueMonkey;
	}

	public void setFalseMonkey(Monkey falseMonkey) {
		this.falseMonkey = falseMonkey;
	}

	public BigInteger getInspections() {
		return inspections;
	}
}
