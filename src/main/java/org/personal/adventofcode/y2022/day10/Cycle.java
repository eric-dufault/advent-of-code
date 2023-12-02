package org.personal.adventofcode.y2022.day10;

public class Cycle {
  private int cycleNumber;
  private int registerValue;

  public Cycle(int cycleNumber, int registerValue) {
    this.cycleNumber = cycleNumber;
    this.registerValue = registerValue;
  }

  public int getCycleNumber() {
    return cycleNumber;
  }

  public int getRegisterValue() {
    return registerValue;
  }
}
