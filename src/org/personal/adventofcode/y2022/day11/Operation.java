package org.personal.adventofcode.y2022.day11;

import java.math.BigInteger;

public class Operation {
	private OperationType operationType;
	private BigInteger secondOperand;
	private boolean applyFirstOperand;

	public Operation(OperationType operationType, BigInteger secondOperand, boolean applyFirstOperand) {
		this.operationType = operationType;
		this.secondOperand = secondOperand;
		this.applyFirstOperand = applyFirstOperand;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public BigInteger getSecondOperand() {
		return secondOperand;
	}

	public boolean isApplyFirstOperand() {
		return applyFirstOperand;
	}


}
