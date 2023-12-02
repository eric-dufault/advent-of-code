package org.personal.adventofcode.y2022.day03;

import java.util.Arrays;

public class Rucksack {
  private String compartmentOneItems;
  private String compartmentTwoItems;

  public Rucksack(String items) {
    int itemsCount = items.length();
    if (itemsCount % 2 != 0)
      throw new IllegalStateException("Rucksack contains uneven number of items");

    int mid = itemsCount / 2;

    this.compartmentOneItems = items.substring(0, mid);
    this.compartmentTwoItems = items.substring(mid);
  }

  public char getDuplicateItem() {
    char[] compartmentTwoItemsArray = this.compartmentTwoItems.toCharArray();
    Arrays.sort(compartmentTwoItemsArray);

    for (char i : this.compartmentOneItems.toCharArray()) {
      int result = Arrays.binarySearch(compartmentTwoItemsArray, i);
      if (result >= 0)
        return i;
    }

    throw new IllegalStateException("No duplicates found in Rucksack");
  }

  public char[] getItems() {
    return (this.compartmentOneItems + this.compartmentTwoItems).toCharArray();
  }

}
