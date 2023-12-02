package org.personal.adventofcode.y2022.day11;

import java.math.BigInteger;
import java.util.function.BiFunction;

public enum OperationType {
	ADDITION("+", BigInteger::add),
	MULTIPLICATION("*", BigInteger::multiply);

	private String symbol;
	private BiFunction<BigInteger, BigInteger, BigInteger> biFunction;

	OperationType(String symbol, BiFunction<BigInteger, BigInteger, BigInteger> biFunction) {
		this.symbol = symbol;
		this.biFunction = biFunction;
	}

	public String getSymbol() {
		return symbol;
	}

	public BiFunction<BigInteger, BigInteger, BigInteger> getBiFunction() {
		return biFunction;
	}
}
