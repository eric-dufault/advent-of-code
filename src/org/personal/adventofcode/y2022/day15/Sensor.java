package org.personal.adventofcode.y2022.day15;

public class Sensor {
  private Coordinate coordinate;
  private Beacon closestBeacon;
  private int manhattanDistance;

  public Sensor(Coordinate coordinate, Beacon closestBeacon) {
    this.coordinate = coordinate;
    this.closestBeacon = closestBeacon;
    this.manhattanDistance = Math.abs(coordinate.getX() - closestBeacon.getCoordinate().getX())
        + Math.abs(coordinate.getY() - closestBeacon.getCoordinate().getY());
  }

  public Coordinate getScanCoverageMinOnRow(int y) {
    Coordinate result = null;
    int  vertical = Math.abs(coordinate.getY() - y);
    if (vertical <= manhattanDistance)
      result = new Coordinate(coordinate.getX() - (manhattanDistance - vertical), y);

    return result;
  }

  public Coordinate getScanCoverageMaxOnRow(int y) {
    Coordinate result = null;
    int vertical = Math.abs(coordinate.getY() - y);
    if (vertical <= manhattanDistance)
      result = new Coordinate(coordinate.getX() + (manhattanDistance - vertical), y);
    return result;
  }

  public boolean isInScanCoverage(Coordinate coordinate) {
    return Math.abs(this.coordinate.getX() - coordinate.getX()) + Math.abs(this.getCoordinate().getY() - coordinate.getY())
        <= manhattanDistance;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public Beacon getClosestBeacon() {
    return closestBeacon;
  }

  public long getManhattanDistance() {
    return manhattanDistance;
  }

}
