package org.personal.adventofcode.y2022.day14;

import java.util.Objects;

public class Point {
  private int x;
  private int y;
  private boolean solid;
  private boolean occupied;

  public Point(int x, int y) {
    this(x, y, false);
  }

  public Point(int x, int y, boolean solid) {
    this.x = x;
    this.y = y;
    this.solid = solid;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public boolean isSolid() {
    return solid;
  }

  public boolean isOccupied() {
    return occupied;
  }

  public void setOccupied(boolean occupied) {
    this.occupied = occupied;
  }

  public boolean isBlocked() {
    return isSolid() || isOccupied();
  }

  public boolean equals(int x, int y) {
    return getX() == x && getY() == y;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;

    if (! (o instanceof Point))
      return false;

    Point p = (Point) o;
    return getX() == p.getX() && getY() == p.getY();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getX(), getY());
  }
}
