package org.personal.adventofcode.y2022.day11;

import java.math.BigInteger;

public class Operation {
	private OperationType operationType;
	private BigInteger secondOperand;

	public Operation(OperationType operationType, BigInteger secondOperand) {
		this.operationType = operationType;
		this.secondOperand = secondOperand;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public BigInteger getSecondOperand() {
		return secondOperand;
	}
}
