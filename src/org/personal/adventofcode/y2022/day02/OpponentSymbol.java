package org.personal.adventofcode.y2022.day02;

public enum OpponentSymbol {
  A(Shape.ROCK),
  B(Shape.PAPER),
  C(Shape.SCISSORS);

  private Shape shape;

  OpponentSymbol(Shape shape) {
    this.shape = shape;
  }

  public Shape getShape() {
    return shape;
  }
}
