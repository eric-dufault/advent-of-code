package org.personal.adventofcode.y2022.day09;

import java.util.Objects;

public class Position {
  private int row;
  private int col;

  public Position(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getCol() {
    return col;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public boolean isAbove(Position position) {
    return this.getRow() > position.getRow();
  }

  public boolean isBelow(Position position) {
    return this.getRow() < position.getRow();
  }

  public boolean isRightOf(Position position) {
    return this.getCol() > position.getCol();
  }

  public boolean isLeftOf(Position position) {
    return this.getCol() < position.getCol();
  }

  public boolean isAdjacent(Position position) {
    return Math.abs(this.getRow() - position.getRow()) <= 1 && Math.abs(this.getCol() - position.getCol()) <= 1;
  }

  public String toString() {
    return "["+ row +","+ col +"]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;

    if (o == null || getClass() != o.getClass())
      return false;

    Position p = (Position) o;
    return this.getRow() == p.getRow() && this.getCol() == p.getCol();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getRow(), getCol());
  }
}
