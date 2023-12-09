package org.personal.adventofcode.y2023.day08;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2023\\day08\\input.txt"));
		List<Node> nodes = parseNodes(fileLines);
		char[] navInstructions = parseNavInstructions(fileLines);

		partA(nodes, navInstructions);
		partB(nodes, navInstructions);
	}

	private static void partA(List<Node> nodes, char[] navInstructions) {
		Node node = nodes.stream().filter(n -> "AAA".equals(n.getLabel())).findFirst().orElseThrow(IllegalArgumentException::new);
		Node endNode = nodes.stream().filter(n -> "ZZZ".equals(n.getLabel())).findFirst().orElseThrow(IllegalArgumentException::new);

		int i = 0, steps = 0;
		boolean foundEndNode = false;
		while (!foundEndNode) {
			if (navInstructions[i] == 'L')
				node = node.getLeftNode();
			else
				node = node.getRightNode();

			steps++;
			foundEndNode = node.equals(endNode);

			i = (i == navInstructions.length - 1) ? 0 : i + 1;
		}

		System.out.println(steps);
	}

	private static void partB(List<Node> nodes, char[] navInstructions) {
		List<BigInteger> stepsList = new ArrayList<>();
		List<Node> startNodes = nodes.stream().filter(n -> 'A' == n.getLabel().charAt(n.getLabel().length() - 1)).toList();
		startNodes.parallelStream().forEach(n -> {
			Node node = n;

			int i = 0, steps = 0;
			boolean foundEndNode = false;
			while (!foundEndNode) {
				if (navInstructions[i] == 'L')
					node = node.getLeftNode();
				else
					node = node.getRightNode();

				steps++;
				foundEndNode = 'Z' == node.getLabel().charAt(node.getLabel().length()-1);

				i = (i == navInstructions.length - 1) ? 0 : i + 1;
			}
			stepsList.add(BigInteger.valueOf(steps));
		});

		System.out.println(stepsList);
		System.out.println(lcm(stepsList));
	}

	private static BigInteger lcm(Collection<BigInteger> nums) {
		Iterator<BigInteger> i = nums.iterator();
		BigInteger result = i.next();
		while (i.hasNext()) {
			result = lcm(result, i.next());
		}
		return result;
	}

	private static BigInteger lcm(BigInteger numOne, BigInteger numTwo) {
		//euclidean method of solving for lcm
		BigInteger gcd = numOne.gcd(numTwo);
		BigInteger absProduct = numOne.multiply(numTwo).abs();
		return absProduct.divide(gcd);
	}

	private static List<Node> parseNodes(List<String> fileLines) {
		List<Node> nodes = new ArrayList<>();

		for (int i = 2; i < fileLines.size(); i++)
			nodes.add(parseNode(fileLines.get(i)));

		for (int i = 2; i < fileLines.size(); i++)
			linkNode(fileLines.get(i), nodes.get(i-2), nodes);

		return nodes;
	}

	private static Node parseNode(String line) {
		String[] nodeParts = line.trim().split("=");
		return new Node(nodeParts[0].trim());
	}

	private static void linkNode(String line, Node node, List<Node> nodes) {
		String[] nodeParts = line.trim().split("=");
		String[] linkParts = nodeParts[1].trim().split(",");

		String leftLinkNodeName = linkParts[0].trim().substring(1);
		Node leftLinkNode = nodes.stream().filter(n -> n.getLabel().equals(leftLinkNodeName)).findFirst().orElseThrow(IllegalArgumentException::new);
		node.setLeftNode(leftLinkNode);

		String rightLinkNodeName = linkParts[1].trim().substring(0, linkParts[1].trim().length() - 1);
		Node rightLinkNode = nodes.stream().filter(n -> n.getLabel().equals(rightLinkNodeName)).findFirst().orElseThrow(IllegalArgumentException::new);
		node.setRightNode(rightLinkNode);
	}

	private static char[] parseNavInstructions(List<String> fileLines) {
		return fileLines.get(0).trim().toCharArray();
	}
}
