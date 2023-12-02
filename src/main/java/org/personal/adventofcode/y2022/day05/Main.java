package org.personal.adventofcode.y2022.day05;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("src\\main\\resources\\y2022\\day05\\input05.txt"));

    int separator = lines.indexOf(lines.stream().filter(l -> "".equals(l.trim())).findFirst().get());
    List<String> headerStrings = lines.subList(0, separator);
    List<String> moveStrings = lines.subList(++separator, lines.size());

    List<Stack<Character>> stacks = createStack(headerStrings);
    List<Move> moves = createMoves(moveStrings, stacks);
    moves.forEach(Move::perform);
    peek(stacks);

    stacks = createStack(headerStrings);
    moves = createMoves(moveStrings, stacks);
    moves.forEach(Move::performUnit);
    peek(stacks);
  }

  private static List<Stack<Character>> createStack(List<String> headerStrings) {
    List<Stack<Character>> stacks = new ArrayList<>();

    String stacksLine = headerStrings.get(headerStrings.size()-1).trim();
    for (int i = 0; i < Integer.parseInt(Character.toString(stacksLine.charAt(stacksLine.length()-1))); i++) {
      stacks.add(new Stack<>());
    }

    int currentCrateStackIndex = 1;
    int offset = 4;
    for(Stack<Character> stack : stacks) {
      for (int j = headerStrings.size()-2; j >= 0; j--) {
        String line = headerStrings.get(j);
        if (line.length() >= currentCrateStackIndex && line.charAt(currentCrateStackIndex) != ' ')
          stack.push(line.charAt(currentCrateStackIndex));
      }
      currentCrateStackIndex += offset;
    }

    return stacks;
  }

  private static List<Move> createMoves(List<String> moveStrings, List<Stack<Character>> stacks) {
    List<Move> moves = new ArrayList<>();
    for(String moveString : moveStrings) {
      String[] moveSplit = moveString.split(" ");
      moves.add(new Move(Integer.parseInt(moveSplit[1]), stacks.get(Integer.parseInt(moveSplit[3])-1), stacks.get(Integer.parseInt(moveSplit[5])-1)));
    }

    return moves;
  }

  private static void peek(List<Stack<Character>> stacks) {
    stacks.forEach(s -> System.out.print(s.peek()));
    System.out.println();
  }
}
