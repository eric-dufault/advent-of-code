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

    //part A
    List<Position> knots = getKnots(2);
    List<String> tailPositions = new ArrayList<>();
    for (String line : lines) {
      processLine(line, knots, tailPositions);
    }
    Set<String> uniqueTailPositions = tailPositions.stream().collect(Collectors.toSet());
    System.out.println(uniqueTailPositions.size());

    //part B
    knots = getKnots(10);
    tailPositions = new ArrayList<>();
    for (String line : lines) {
      processLine(line, knots, tailPositions);
    }
    uniqueTailPositions = tailPositions.stream().collect(Collectors.toSet());
    System.out.println(uniqueTailPositions.size());
  }

  private static void processLine(String line, List<Position> knots, List<String> tailPositions) {
    String[] split = line.split(" ");
    int steps = Integer.parseInt(split[1]);
    for (int i = 0; i < steps; i++) {
      switch(split[0]) {
        case UP:
          knots.get(0).setCol(knots.get(0).getCol() + 1);
          moveTail(knots);
          tailPositions.add(knots.get(knots.size()-1).toString());
          break;
        case DOWN:
          knots.get(0).setCol(knots.get(0).getCol() - 1);
          moveTail(knots);
          tailPositions.add(knots.get(knots.size()-1).toString());
          break;
        case RIGHT:
          knots.get(0).setRow(knots.get(0).getRow() + 1);
          moveTail(knots);
          tailPositions.add(knots.get(knots.size()-1).toString());
          break;
        case LEFT:
          knots.get(0).setRow(knots.get(0).getRow() - 1);
          moveTail(knots);
          tailPositions.add(knots.get(knots.size()-1).toString());
          break;
        default:
          break;
      }
    }
  }

  private static void moveTail(List<Position> knots) {
    for (int k = 1; k < knots.size(); k++) {
      if (!isAdjacent(knots.get(k-1), knots.get(k))) {
        if (knots.get(k - 1).getRow() < knots.get(k).getRow())
          knots.get(k).setRow(knots.get(k).getRow() - 1);
        else if (knots.get(k - 1).getRow() > knots.get(k).getRow())
          knots.get(k).setRow(knots.get(k).getRow() + 1);

        if (knots.get(k - 1).getCol() < knots.get(k).getCol())
          knots.get(k).setCol(knots.get(k).getCol() - 1);
        else if (knots.get(k - 1).getCol() > knots.get(k).getCol())
          knots.get(k).setCol(knots.get(k).getCol() + 1);
      }
    }
  }

  private static boolean isAdjacent(Position head, Position tail) {
    return((head.getRow()-1) <= tail.getRow() && tail.getRow() <= (head.getRow()+1))
        && ((head.getCol()-1) <= tail.getCol() && tail.getCol() <= (head.getCol()+1));
  }

  private static List<Position> getKnots(int size) {
    List<Position> knots = new ArrayList<>();
    for (int i = 0; i < size; i++)
      knots.add(new Position(0,0));
    return knots;
  }

}
