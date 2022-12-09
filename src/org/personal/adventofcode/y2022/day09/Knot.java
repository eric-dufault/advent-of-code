package org.personal.adventofcode.y2022.day09;

import java.util.ArrayList;
import java.util.List;

public class Knot {
  private String identifier;
  private Position currentPosition;
  private List<Position> visitedPositions = new ArrayList<>();
  private boolean hasMoved = false;

  public Knot(String identifier) {
    this.identifier = identifier;
    this.currentPosition = new Position(0,0);
    this.visitedPositions.add(this.currentPosition);
  }

  public void moveUp() {
    currentPosition = new Position(currentPosition.getRow()+1, currentPosition.getCol());
    hasMoved = true;
  }

  public void moveDown() {
    currentPosition = new Position(currentPosition.getRow()-1, currentPosition.getCol());
    hasMoved = true;
  }

  public void moveRight() {
    currentPosition = new Position(currentPosition.getRow(), currentPosition.getCol()+1);
    hasMoved = true;
  }

  public void moveLeft() {
    currentPosition = new Position(currentPosition.getRow(), currentPosition.getCol()-1);
    hasMoved = true;
  }

  public void updateVisitedPositions() {
    if (hasMoved)
      visitedPositions.add(currentPosition);

    hasMoved = false;
  }

  public boolean isAbove(Knot knot) {
    return this.getCurrentPosition().isAbove(knot.getCurrentPosition());
  }

  public boolean isBelow(Knot knot) {
    return this.getCurrentPosition().isBelow(knot.getCurrentPosition());
  }

  public boolean isRightOf(Knot knot) {
    return this.getCurrentPosition().isRightOf(knot.getCurrentPosition());
  }

  public boolean isLeftOf(Knot knot) {
    return this.getCurrentPosition().isLeftOf(knot.getCurrentPosition());
  }

  public boolean isAdjacent(Knot knot) {
    return this.getCurrentPosition().isAdjacent(knot.getCurrentPosition());
  }

  public String getIdentifier() {
    return identifier;
  }

  public Position getCurrentPosition() {
    return currentPosition;
  }

  public List<Position> getVisitedPositions() {
    return visitedPositions;
  }
}
