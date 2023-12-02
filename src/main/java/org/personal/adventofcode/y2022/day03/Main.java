package org.personal.adventofcode.y2022.day03;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("src\\main\\resources\\y2022\\day03\\input03.txt"));

    partA(lines);
    partB(lines);
  }

  private static void partA(List<String> lines) {
    int sum = 0;
    for (String line: lines) {
      Rucksack r = new Rucksack(line);
      sum += getPriority(r.getDuplicateItem());
    }
    System.out.println(sum);
  }

  private static void partB(List<String> lines) {
    List<RucksackGroup> rucksackGroups = new ArrayList<>();
    RucksackGroup rucksackGroup = new RucksackGroup();
    int currentLine = 0;
    for (String line : lines) {
      rucksackGroup.addRucksack(new Rucksack(line));
      currentLine++;
      if (currentLine % 3 == 0) {
        rucksackGroups.add(rucksackGroup);
        rucksackGroup = new RucksackGroup();
      }
    }

    int sum = 0;
    for (RucksackGroup rs : rucksackGroups) {
      sum += getPriority(rs.getBadge());
    }
    System.out.println(sum);
  }

  private static int getPriority(char c) {
    int result;
    if (Character.isUpperCase(c))
      result = ((int) c) - 38;
    else
      result = ((int) c) - 96;

    return result;
  }
}
