package org.personal.adventofcode.y2022.day14;

import java.util.ArrayList;
import java.util.List;

public class Line {
  private List<Point> points = new ArrayList<>();

  public void addPoint(Point point) {
    if (points.isEmpty())
      points.add(point);
    else {
      Point previousPoint = points.get(points.size() - 1);
      if (previousPoint.getX() == point.getX()) {
        int min = Math.min(previousPoint.getY(), point.getY());
        int max = Math.max(previousPoint.getY(), point.getY());
        for (int i = min+1; i < max; i++) {
          points.add(new Point(point.getX(), i, true));
        }
      }
      else if (previousPoint.getY() == point.getY()) {
        int min = Math.min(previousPoint.getX(), point.getX());
        int max = Math.max(previousPoint.getX(), point.getX());
        for (int i = min+1; i < max; i++) {
          points.add(new Point(i, point.getY(), true));
        }
      }
      points.add(point);
    }
  }

  public List<Point> getPoints() {
    return points;
  }

  public Point getPoint(int x, int y) {
    Point point = null;
    for (Point p : points) {
      if (p.getX() == x && p.getY() == y) {
        point = p;
        break;
      }
    }
    return point;
  }

  public boolean hasPoint(int x, int y) {
    return getPoint(x, y) != null;
  }
}
