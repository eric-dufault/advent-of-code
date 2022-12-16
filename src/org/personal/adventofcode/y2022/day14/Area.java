package org.personal.adventofcode.y2022.day14;

import java.util.List;

public class Area {
  private Point[][] area;
  private List<Line> lines;
  private Point dropPoint;
  private int minimumX;
  private int maximumX;
  private int maximumY;

  public Area(List<Line> lines, Point dropPoint) {
    this.lines = lines;
    this.dropPoint = dropPoint;
    this.minimumX = findMinimumX();
    this.maximumX = findMaximumX();
    this.maximumY = findMaximumY();
    createArea();
  }

  private void createArea() {
    area = new Point[maximumY+1][maximumX-minimumX+1];
    for (int i = 0; i < maximumY+1; i++) {
      for (int j = 0; j < maximumX-minimumX+1; j++) {
        int x = j + minimumX;
        int y = i;
        Point point = getLinesPoint(x, y);
        if (dropPoint.equals(x, y)) {
          area[i][j] = dropPoint;
        }
        else if(point != null) {
          area[i][j] = point;
        }
        else {
          area[i][j] = new Point(x, y);
        }
      }
    }
  }

  private int findMaximumX() {
    int x = 0;
    for (Line line : lines) {
      for (Point point : line.getPoints()) {
        if (point.getX() > x)
          x = point.getX();
      }
    }
    return x;
  }

  private int findMinimumX() {
    int x = Integer.MAX_VALUE;
    for (Line line : lines) {
      for (Point point : line.getPoints()) {
        if (point.getX() < x)
          x = point.getX();
      }
    }
    return x;
  }

  private int findMaximumY() {
    int y = 0;
    for (Line line : lines) {
      for (Point point : line.getPoints()) {
        if (point.getY() > y)
          y = point.getY();
      }
    }
    return y;
  }

  public Point getPoint(int x, int y) {
    int i = y;
    int j = x - minimumX;
    return area[i][j];
  }

  public boolean inBounds(int x, int y) {
    return minimumX <= x && x <= maximumX && 0 <= y && y <= maximumY;
  }

  public Point getLinesPoint(int x, int y) {
    Point point = null;
    for (Line line : lines) {
      point = line.getPoint(x, y);
      if (point != null)
        break;
    }
    return point;
  }

  public void print() {
    for (int i = 0; i < area.length; i++) {
      for (int j = 0; j < area[i].length; j++) {
        System.out.print(getPrintChar(area[i][j], dropPoint));
      }
      System.out.println();
    }
  }

  private String getPrintChar(Point point, Point dropPoint) {
    String temp = ".";
    if (point.equals(dropPoint))
      temp = "+";
    else if (point.isSolid())
      temp = "#";
    else if (point.isOccupied())
      temp = "O";
    return temp;
  }

  public List<Line> getLines() {
    return lines;
  }

  public Point getDropPoint() {
    return dropPoint;
  }

  public int getMaximumX() {
    return this.maximumX;
  }

  public int getMinimumX() {
    return this.minimumX;
  }

  public int getMaximumY() {
    return this.maximumY;
  }
}
