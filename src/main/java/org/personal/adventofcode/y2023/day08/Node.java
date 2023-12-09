package org.personal.adventofcode.y2023.day08;

public class Node {
	private String label;
	private Node leftNode;
	private Node rightNode;

	public Node(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Node getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}

	public Node getRightNode() {
		return rightNode;
	}

	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (!(o instanceof Node))
			return false;

		Node other = (Node) o;
		return getLabel().equals(other.getLabel());
	}
}
