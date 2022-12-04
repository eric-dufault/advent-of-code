package org.personal.adventofcode.y2022.day04;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    try {
      List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Eric\\Projects\\advent-of-code\\src\\org\\personal\\adventofcode\\y2022\\day04\\input04.txt"));
      partAandB(lines);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private static void partAandB(List<String> pairs) {
    int containments = 0;
    int overlaps = 0;
    for(String pair : pairs) {
      String[] elfAssignment = pair.split(",");

      String[] elfAssignmentOne = elfAssignment[0].split("-");
      String[] elfAssignmentTwo = elfAssignment[1].split("-");

      int elfAssignmentOneMin = Integer.parseInt(elfAssignmentOne[0]);
      int elfAssignmentOneMax = Integer.parseInt(elfAssignmentOne[1]);

      int elfAssignmentTwoMin = Integer.parseInt(elfAssignmentTwo[0]);
      int elfAssignmentTwoMax = Integer.parseInt(elfAssignmentTwo[1]);

      if ((elfAssignmentOneMin <= elfAssignmentTwoMin && elfAssignmentTwoMax <= elfAssignmentOneMax)
          || (elfAssignmentTwoMin <= elfAssignmentOneMin && elfAssignmentOneMax <= elfAssignmentTwoMax)) {
        containments++;
        overlaps++;
      }
      else if ((elfAssignmentTwoMin <= elfAssignmentOneMin && elfAssignmentOneMin <= elfAssignmentTwoMax)
          || (elfAssignmentOneMax >= elfAssignmentTwoMin && elfAssignmentTwoMax >= elfAssignmentOneMax)) {
        overlaps++;
      }
    }
    System.out.println(containments);
    System.out.println(overlaps);
  }
}
