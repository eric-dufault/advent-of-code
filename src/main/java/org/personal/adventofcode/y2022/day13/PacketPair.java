package org.personal.adventofcode.y2022.day13;

import java.util.Comparator;
import java.util.Stack;

public class PacketPair {
	public static final Comparator<PacketData> packetComparator = new PacketComparator();

	private String leftSource;
	private PacketData left;
	private String rightSource;
	private PacketData right;
	private int state;

	public PacketPair(String leftSource, String rightSource) {
		this.leftSource = leftSource;
		this.rightSource = rightSource;

		this.left = createPacketDataList(leftSource);
		this.right = createPacketDataList(rightSource);
		this.state = packetComparator.compare(left, right);
	}

	public PacketData getLeft() {
		return left;
	}

	public PacketData getRight() {
		return right;
	}

	public int getState() {
		return state;
	}

	//assumes there is always an outer PacketDataList
	private PacketDataList createPacketDataList(String packetSource) {
		int i = 0;
		PacketDataList current = null;
		Stack<PacketDataList> stack = new Stack<>();
		PacketDataList lastStack = null;
		while (i < packetSource.length()) {
			char c = packetSource.charAt(i);
			if (c == '[') {
				PacketDataList packetDataList = new PacketDataList(current);
				if (current != null)
					current.add(packetDataList);

				current = packetDataList;
				stack.push(current);
				i++;
			}
			else if (c == ']') {
				current = current.getParentList();
				lastStack = stack.pop();
				i++;
			}
			else if (c == ',') {
				i++;
			}
			else {
				StringBuilder sb = new StringBuilder(2);
				do {
					sb.append(c);
					i++;
					c = packetSource.charAt(i);
				}
				while(c != '[' && c != ']' && c != ',');

				current.add(new PacketDataInteger(Integer.parseInt(sb.toString())));
			}
		}
		return lastStack;
	}

}
