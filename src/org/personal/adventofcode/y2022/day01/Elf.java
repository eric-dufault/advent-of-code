package org.personal.adventofcode.y2022.day01;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Elf {
  private List<BigDecimal> calories = new ArrayList<>();

  public void addCalory(BigDecimal calory) {
    this.calories.add(calory);
  }

  public BigDecimal getTotalCalories() {
    return calories.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
