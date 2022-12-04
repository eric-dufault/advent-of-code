package org.personal.adventofcode.y2022.day02;

public enum PlayerSymbol {
  X(Shape.ROCK),
  Y(Shape.PAPER),
  Z(Shape.SCISSORS);

  private Shape shape;

  PlayerSymbol(Shape shape) {
    this.shape = shape;
  }

  public Shape getShape() {
    return shape;
  }
}
