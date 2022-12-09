package org.personal.adventofcode.y2022.day09;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

  private static final String UP = "U";
  private static final String DOWN = "D";
  private static final String RIGHT = "R";
  private static final String LEFT = "L";

  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Eric\\Projects\\advent-of-code\\src\\org\\personal\\adventofcode\\y2022\\day09\\input09.txt"));

    //Part A
    List<Knot> rope = createRope(2);
    for (String line : lines)
      processLine(line, rope);

    Set<Position> uniquePositions = rope.get(rope.size() - 1).getVisitedPositions().stream().collect(Collectors.toSet());
    System.out.println(uniquePositions.size());

    //Part B
    rope = createRope(10);
    for (String line : lines)
      processLine(line, rope);

    uniquePositions = rope.get(rope.size() - 1).getVisitedPositions().stream().collect(Collectors.toSet());
    System.out.println(uniquePositions.size());
  }

  private static void processLine(String line, List<Knot> rope) {
    String[] split = line.split(" ");
    int steps = Integer.parseInt(split[1]);
    for (int i = 0; i < steps; i++) {
      switch(split[0]) {
        case UP:
          rope.get(0).moveUp();
          moveTail(rope);
          break;
        case DOWN:
          rope.get(0).moveDown();
          moveTail(rope);
          break;
        case RIGHT:
          rope.get(0).moveRight();
          moveTail(rope);
          break;
        case LEFT:
          rope.get(0).moveLeft();
          moveTail(rope);
          break;
        default:
          break;
      }
    }
  }

  private static void moveTail(List<Knot> rope) {
    for (int k = 1; k < rope.size(); k++) {
      if (!rope.get(k).isAdjacent(rope.get(k-1))) {
        if (rope.get(k - 1).isBelow(rope.get(k)))
          rope.get(k).moveDown();
        else if (rope.get(k - 1).isAbove(rope.get(k)))
          rope.get(k).moveUp();

        if (rope.get(k - 1).isLeftOf(rope.get(k)))
          rope.get(k).moveLeft();
        else if (rope.get(k - 1).isRightOf(rope.get(k)))
          rope.get(k).moveRight();

        rope.get(k).updateVisitedPositions();
      }
    }
  }

  private static List<Knot> createRope(int size) {
    List<Knot> rope = new ArrayList<>();
    for (int i = 0; i < size; i++)
      rope.add(new Knot(Integer.toString(i)));
    return rope;
  }
}
