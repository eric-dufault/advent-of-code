package org.personal.adventofcode.y2024.day07;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PartB {

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

		List<List<Node>> paths = new ArrayList<>();
		List<Node> path = new ArrayList<>();
		addPaths(lhs, root, path, paths);

		if (paths.isEmpty())
			return false;
		else
			return paths.stream().anyMatch(p -> p.get(p.size() - 1).cumulativeData == lhs);
	}

	private static void addPaths(long lhs, Node node, List<Node> path, List<List<Node>> paths) {
		if (node == null)
			return;

		path.add(node);

		if (node.leftNode == null && node.middleNode == null && node.rightNode == null) {
			paths.add(new ArrayList<>(path));
		}
		else {
			node.leftNode.cumulativeData = node.cumulativeData + node.leftNode.data;
			if (node.leftNode.cumulativeData <= lhs) {
				addPaths(lhs, node.leftNode, path, paths);
			}

			node.middleNode.cumulativeData = Long.parseLong(node.cumulativeData + "" + node.middleNode.data);
			if (node.middleNode.cumulativeData <= lhs) {
				addPaths(lhs, node.middleNode, path, paths);
			}

			node.rightNode.cumulativeData = node.cumulativeData * node.rightNode.data;
			if (node.rightNode.cumulativeData <= lhs) {
				addPaths(lhs, node.rightNode, path, paths);
			}
		}

		path.remove(path.size() - 1);
	}

	private static void buildTree(Node current, int i, long[] rhs) {
		if (i == rhs.length)
			return;

		Node leftNode = new Node(rhs[i]);
		current.leftNode = leftNode;
		buildTree(leftNode, i + 1, rhs);

		Node middleNode = new Node(rhs[i]);
		current.middleNode = middleNode;
		buildTree(middleNode, i + 1, rhs);

		Node rightNode = new Node(rhs[i]);
		current.rightNode = rightNode;
		buildTree(rightNode, i + 1, rhs);
	}


	private static class Node {
		long data;
		long cumulativeData;
		Node leftNode;
		Node middleNode;
		Node rightNode;

		Node(long data) {
			this(data, 0);
		}

		Node(long data, long cumulativeData) {
			this.data = data;
			this.cumulativeData  = cumulativeData;
		}

		public String toString() {
			return this.data + ":" + this.cumulativeData;
		}
	}




	private static void addPathsOld(long lhs, Node node, String path, List<String> paths) {
		if (node.leftNode == null && node.middleNode == null && node.rightNode == null) {
			paths.add(path);
		}

		if (node.leftNode != null) {
			node.leftNode.cumulativeData = node.cumulativeData + node.leftNode.data;
			if (node.leftNode.cumulativeData <= lhs) {
				addPathsOld(lhs, node.leftNode, path + "+" + node.leftNode, paths);
			}
		}

		if (node.middleNode != null) {
			node.middleNode.cumulativeData = Long.parseLong(node.cumulativeData + "" + node.middleNode.data);
			if (node.middleNode.cumulativeData <= lhs) {
				addPathsOld(lhs, node.middleNode, path + "||" + node.middleNode, paths);
			}
		}

		if (node.rightNode != null) {
			node.rightNode.cumulativeData = node.cumulativeData * node.rightNode.data;
			if (node.rightNode.cumulativeData <= lhs) {
				addPathsOld(lhs, node.rightNode, path + "*" + node.rightNode, paths);
			}
		}
	}
}
