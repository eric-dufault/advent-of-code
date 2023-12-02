package org.personal.adventofcode.y2022.day15;

public class Interval {
  private int upper;
  private int lower;

  public Interval(int lower, int upper) {
    this.lower = lower;
    this.upper = upper;
  }

  public int getUpper() {
    return upper;
  }

  public void setUpper(int upper) {
    this.upper = upper;
  }

  public int getLower() {
    return lower;
  }

  public void setLower(int lower) {
    this.lower = lower;
  }

  @Override
  public String toString() {
    return "["+lower+","+upper+"]";
  }
}
