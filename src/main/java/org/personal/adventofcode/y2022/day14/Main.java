package org.personal.adventofcode.y2022.day14;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) throws Exception {
    List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2022\\day14\\input14.txt"));
    partA(fileLines);
    partB(fileLines);
  }

  private static void partA(List<String> fileLines) {
    Area area = createArea(fileLines, false);
    int i = 0;
    boolean canRest = true;
    while (canRest) {
      canRest = dropSand(area, area.getDropPoint());
      i++;
    }
    System.out.println(i-1);
  }

  private static void partB(List<String> fileLines) {
    Area area = createArea(fileLines, true);
    int i = 0;
    boolean canRest = true;
    while (canRest) {
      canRest = dropSand(area, area.getDropPoint());
      i++;
    }
    System.out.println(i);
  }

  private static boolean dropSand(Area area, Point point) {
    int x = point.getX();
    int y = point.getY() + 1;
    if (!area.inBounds(x, y))
      return false;
    else {
      if (!area.getPoint(x, y).isBlocked())
        return dropSand(area, area.getPoint(x, y));
      else {
        if (!area.inBounds(x - 1, y))
          return false;
        else {
          if (!area.getPoint(x - 1, y).isBlocked())
            return dropSand(area, area.getPoint(x - 1, y));
          else {
            if (!area.inBounds(x + 1, y))
              return false;
            else {
              if (!area.getPoint(x + 1, y).isBlocked())
                return dropSand(area, area.getPoint(x + 1, y));
              else {
                if (point.equals(area.getDropPoint()))
                  return false;
                else {
                  point.setOccupied(true);
                  return true;
                }
              }
            }
          }
        }
      }
    }
  }

  private static Area createArea(List<String> fileLines, boolean addFloor) {
    List<Line> lines = createLines(fileLines, addFloor);
    Point dropPoint = new Point(500, 0, true);
    return new Area(lines, dropPoint);
  }

  private static List<Line> createLines(List<String> fileLines, boolean addFloor) {
    List<Line> lines = new ArrayList<>();
    for (String fileLine : fileLines) {
      Line line = new Line();
      String[] split = fileLine.split(" -> ");
      for (String t : split) {
        String[] pointArray = t.split(",");
        line.addPoint(new Point(Integer.parseInt(pointArray[0]), Integer.parseInt(pointArray[1]), true));
      }
      lines.add(line);
    }

    if (addFloor) {
      int y = 0;
      for (Line l : lines) {
        for (Point p : l.getPoints()) {
          if (y < p.getY())
            y = p.getY();
        }
      }
      Line floor = new Line();
      Point floorRight = new Point(800, y+2);
      Point floorLeft = new Point(-800, y+2);
      floor.addPoint(floorLeft);
      floor.addPoint(floorRight);
      lines.add(floor);
    }

    return lines;
  }
}
