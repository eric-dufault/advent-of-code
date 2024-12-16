package org.personal.adventofcode.y2024.day07;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

public class Main {

	private static final BiFunction<Long, Long, Long> ADDITION = (a, b) -> a + b;
	private static final BiFunction<Long, Long, Long> MULTIPLICATION = (a, b) -> a * b;

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2024\\day07\\input.txt"));

		long sum = 0;
		for (String line : fileLines) {
			String[] equationParts = line.split(":");
			long lhs = Long.parseLong(equationParts[0]);

			String[] rhsParts = equationParts[1].trim().split("\s");
			long[] rhs = new long[rhsParts.length];
			for (int i = 0; i < rhsParts.length; i++)
				rhs[i] = Long.parseLong(rhsParts[i]);

			if (isValid(lhs, rhs))
				sum += lhs;
		}

		System.out.println(sum);
	}

	private static boolean isValid(long lhs, long[] rhs) {
		Node root = new Node(rhs[0], rhs[0]);
		buildTree(root, 1, rhs);

		Set<Node> leafs = new HashSet<>();
		addLeafNodes(root, leafs);

		return leafs.stream().anyMatch(node -> node.cumulativeData == lhs);
	}

	private static void buildTree(Node current, int i, long[] rhs) {
		if (i == rhs.length)
			return;

		Node leftNode = new Node(rhs[i], ADDITION.apply(current.cumulativeData, rhs[i]));
		current.leftNode = leftNode;
		buildTree(leftNode, i + 1, rhs);

		Node rightNode = new Node(rhs[i], MULTIPLICATION.apply(current.cumulativeData, rhs[i]));
		current.rightNode = rightNode;
		buildTree(rightNode, i + 1, rhs);
	}

	private static void addLeafNodes(Node node, Set<Node> leafs) {
		if (node.leftNode == null && node.rightNode == null)
			leafs.add(node);

		if (node.leftNode != null)
			addLeafNodes(node.leftNode, leafs);

		if (node.rightNode != null)
			addLeafNodes(node.rightNode, leafs);
	}


	private static class Node {
		long data;
		long cumulativeData;
		Node leftNode;
		Node rightNode;

		Node(long data, long cumulativeData) {
			this.data = data;
			this.cumulativeData = cumulativeData;
		}
	}

}
