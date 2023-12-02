package org.personal.adventofcode.y2022.day02;

public enum Shape {
  ROCK(1),
  PAPER(2),
  SCISSORS(3);

  private int additionalScore;

  Shape(int additionalScore) {
    this.additionalScore = additionalScore;
  }

  public int getAdditionalScore() {
    return additionalScore;
  }
}
