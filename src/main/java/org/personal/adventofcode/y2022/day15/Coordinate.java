package org.personal.adventofcode.y2022.day15;

import java.util.Objects;

public class Coordinate {
  private static final long TUNING_MULTIPLIER = 4000000L;

  private int x;
  private int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
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

  public Long getTuningFrequency() {
    return (TUNING_MULTIPLIER * ((long) getX())) + ((long) getY());
  }

  @Override
  public String toString() {
    return "["+getX()+","+getY()+"]";
  }

  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (!(o instanceof Coordinate))
      return false;

    Coordinate c = (Coordinate) o;
    return getX() == c.getX() && getY() == c.getY();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getX(), getY());
  }
}
