package org.personal.adventofcode.y2022.day01;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    try {
      List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Eric\\Projects\\AdventOfCode2022\\src\\org\\personal\\adventofcode\\y2022\\day01\\input01.txt"));

      partAandB(lines);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void partAandB(List<String> lines) {
    List<Elf> elves = new ArrayList<>();
    Elf elf = new Elf();
    for (String line : lines) {
      if (!"".equals(line.trim()))
        elf.addCalory(new BigDecimal(line));
      else {
        elves.add(elf);
        elf = new Elf();
      }
    }

    List<BigDecimal> totalCalories = elves.stream().map(Elf::getTotalCalories).collect(Collectors.toList());

    Collections.sort(totalCalories, Collections.reverseOrder());
    System.out.println(totalCalories.get(0));
    System.out.println(totalCalories.get(0).add(totalCalories.get(1).add(totalCalories.get(2))));
  }

}
