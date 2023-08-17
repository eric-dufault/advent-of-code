package org.personal.adventofcode.y2022.day13;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
	private static final String INPUT = "C:\\Users\\Eric\\Projects\\advent-of-code\\src\\org\\personal\\adventofcode\\y2022\\day13\\input13.txt";

	public static void main(String[] args) throws Exception {
		List<String> lines = Files.readAllLines(Paths.get(INPUT));

		List<PacketPair> packetPairs = new ArrayList<>(lines.size());
		for (int i = 0; i < lines.size(); i += 3) {
			PacketPair packetPair = new PacketPair(lines.get(i), lines.get(i+1));
			packetPairs.add(packetPair);
		}

		//Part A
		int sumIndices = IntStream.range(0, packetPairs.size())
				.filter(i -> packetPairs.get(i).getState() == -1)
				.reduce(0, (a,b) -> a + b + 1);
		System.out.println(sumIndices);

		//Part B
		List<PacketData> packets = new ArrayList<>(lines.size());
		packets.addAll(packetPairs.stream().map(PacketPair::getLeft).toList());
		packets.addAll(packetPairs.stream().map(PacketPair::getRight).toList());

		PacketPair dividerPackets = new PacketPair("[[2]]", "[[6]]");
		packets.add(dividerPackets.getLeft());
		packets.add(dividerPackets.getRight());

		packets.sort(PacketPair.packetComparator);
	}

	private static void printPacketData(PacketData packetData) {
		if (PacketDataType.INTEGER.equals(packetData.getType())) {
			PacketDataInteger pdi = (PacketDataInteger) packetData;
			System.out.print(" " + pdi.getValue() + " ");
		} else {
			System.out.print("[");
			PacketDataList pdl = (PacketDataList) packetData;
			for (PacketData pd : pdl.getList()) {
				printPacketData(pd);
			}
			System.out.print("]");
		}
	}

}
