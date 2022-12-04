package org.personal.adventofcode.y2022.day02;

public enum Outcome {
  WIN(6),
  LOSE(0),
  DRAW(3);

  private int score;

  Outcome(int score) {
    this.score = score;
  }

  public int getScore() {
    return score;
  }

}
