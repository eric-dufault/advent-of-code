package org.personal.adventofcode.y2022.day03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RucksackGroup {

  private List<Rucksack> rucksacks = new ArrayList<>(3);

  public RucksackGroup() {}

  public void addRucksack(Rucksack r) {
    rucksacks.add(r);
  }

  public char getBadge() {
    char[] itemsOne = this.rucksacks.get(0).getItems();
    char[] itemsTwo = this.rucksacks.get(1).getItems();
    Arrays.sort(itemsTwo);

    char[] itemsThree = this.rucksacks.get(2).getItems();
    Arrays.sort(itemsThree);

    for (char c : itemsOne) {
      int i = Arrays.binarySearch(itemsTwo, c);
      if (i >= 0) {
        int j = Arrays.binarySearch(itemsThree, c);
        if (j >= 0)
          return c;
      }
    }

    throw new IllegalStateException("No badge found in Rucksacks");
  }

}
