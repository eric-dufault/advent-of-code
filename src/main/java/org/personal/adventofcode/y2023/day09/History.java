package org.personal.adventofcode.y2023.day09;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class History {
	private List<List<BigInteger>> sequences;

	public History(List<BigInteger> sequence) {
		addSequence(new ArrayList<>(sequence));
	}


	public List<BigInteger> getFirstSequence() {
		return getSequences().get(0);
	}

	public List<BigInteger> getLastSequence() {
		return getSequences().get(getSequences().size() - 1);
	}

	public void addSequence(List<BigInteger> sequence) {
		getSequences().add(sequence);
	}

	public List<List<BigInteger>> getSequences() {
		if (sequences == null)
			sequences = new ArrayList<>();
		return sequences;
	}
}
