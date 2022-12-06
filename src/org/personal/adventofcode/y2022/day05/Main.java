package org.personal.adventofcode.y2022.day05;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Eric\\Projects\\advent-of-code\\src\\org\\personal\\adventofcode\\y2022\\day05\\input05.txt"));

    int separator = lines.indexOf(lines.stream().filter(l -> "".equals(l.trim())).findFirst().get());
    List<String> header = lines.subList(0, separator);
    List<String> moves = lines.subList(separator + 1, lines.size());

    String stacksLine = header.get(header.size()-1).trim();
    List<Stack<Character>> stacks = new ArrayList<>();
    for (int i = 0; i < Integer.parseInt(Character.toString(stacksLine.charAt(stacksLine.length()-1))); i++) {
      stacks.add(new Stack<>());
    }

    int currentCrateStackIndex = 1;
    int offset = 4;
    for(Stack<Character> stack : stacks) {
      for (int j = header.size()-2; j >= 0; j--) {
        String line = header.get(j);
        if (line.length() >= currentCrateStackIndex && line.charAt(currentCrateStackIndex) != ' ')
          stack.push(line.charAt(currentCrateStackIndex));
      }
      currentCrateStackIndex += offset;
    }

    for (String move : moves) {
      String[] moveSplit = move.split(" ");
      Move m = new Move(Integer.parseInt(moveSplit[1]), stacks.get(Integer.parseInt(moveSplit[3])-1), stacks.get(Integer.parseInt(moveSplit[5])-1));
      m.performUnitMove();
    }

    peek(stacks);
  }

  private static void peek(List<Stack<Character>> stacks) {
    for(Stack<Character> stack : stacks)
      System.out.print(stack.peek());
  }
}
