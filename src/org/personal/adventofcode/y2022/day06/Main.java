package org.personal.adventofcode.y2022.day06;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Eric\\Projects\\advent-of-code\\src\\org\\personal\\adventofcode\\y2022\\day06\\input06.txt"));

    Packet packet = getPacket(lines.get(0), 4);
    System.out.print(packet.getPackendEndIndex() + " " + packet.getPacketString());
    System.out.println();

    packet = getPacket(lines.get(0), 14);
    System.out.print(packet.getPackendEndIndex() + " " + packet.getPacketString());
    System.out.println();
  }

  private static Packet getPacket(String datastream, int packetSize) {
    Packet packet = null;

    for (int i = packetSize; i < datastream.length(); i++) {
      String candidate = datastream.substring(i-packetSize, i);
      Set<Character> testUniqueness = new HashSet<>();
      for (int j = 0; j < candidate.length(); j++)
        testUniqueness.add(candidate.charAt(j));

      if (testUniqueness.size() == candidate.length()) {
        packet = new Packet(i, candidate);
        break;
      }
    }

    return packet;
  }

  private static class Packet {
    private int packendEndIndex;
    private String packetString;

    Packet(int packendEndIndex, String packetString) {
      this.packendEndIndex = packendEndIndex;
      this.packetString = packetString;
    }

    public int getPackendEndIndex() {
      return packendEndIndex;
    }

    public String getPacketString() {
      return packetString;
    }
  }
}
