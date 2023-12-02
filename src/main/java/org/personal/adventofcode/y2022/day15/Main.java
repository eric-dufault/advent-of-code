package org.personal.adventofcode.y2022.day15;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
  private static final String FILENAME = "src\\main\\resources\\y2022\\day15\\input15.txt";

  public static void main(String[] args) throws Exception {
    List<String> fileLines = Files.readAllLines(Paths.get(FILENAME));
    List<Sensor> sensors = createSensors(fileLines);

    int coverage = partA(sensors);
    System.out.println(coverage);

    Coordinate distressCoordinate = partB(sensors);
    System.out.println(distressCoordinate.getTuningFrequency());
  }

  private static int partA(List<Sensor> sensors) {
    final int y = 2000000;
    int result = 0;
    Coordinate scanCoverageMin = getScanCoverageMinOnRow(sensors, y);
    Coordinate scanCoverageMax = getScanCoverageMaxOnRow(sensors, y);

    Set<Coordinate> beaconCoordinates = sensors.stream()
        .map(Sensor::getClosestBeacon)
        .map(Beacon::getCoordinate)
        .filter(c -> c.getY() == y)
        .collect(Collectors.toSet());

    for (int x = scanCoverageMin.getX(); x <= scanCoverageMax.getX(); x++) {
      Coordinate coordinate = new Coordinate(x, y);
      boolean foundCoverage = false;

      for (Sensor sensor : sensors) {
        if (sensor.isInScanCoverage(coordinate) && !beaconCoordinates.contains(coordinate)) {
          foundCoverage = true;
          break;
        }
      }

      if (foundCoverage)
        result++;
    }
    return result;
  }

  private static Coordinate partB(List<Sensor> sensors) {
    final int minY = 0;
    final int maxY = 4000000;

    Coordinate distressCoordinate = null;
    int y = minY;
    while (y <= maxY && distressCoordinate == null) {
      List<Interval> intervals = new ArrayList<>();
      for (Sensor sensor : sensors) {
        Coordinate start = sensor.getScanCoverageMinOnRow(y);
        Coordinate end = sensor.getScanCoverageMaxOnRow(y);
        if (start != null && end != null)
          intervals.add(new Interval(start.getX(), end.getX()));
      }

      Stack<Interval> mergedIntervals = mergeIntervals(intervals);
      if (!mergedIntervals.empty() && mergedIntervals.size() > 1) {
        Interval interval = mergedIntervals.pop();
        distressCoordinate = new Coordinate(interval.getLower()-1, y);
      }

      y++;
    }
    return distressCoordinate;
  }

  private static Stack<Interval> mergeIntervals(List<Interval> intervals) {
    Stack<Interval> stack = new Stack<>();
    if (!intervals.isEmpty()) {
      Collections.sort(intervals, Comparator.comparing(Interval::getLower));

      stack.push(intervals.get(0));

      for (int i = 1; i < intervals.size(); i++) {
        Interval top = stack.peek();
        if (top.getUpper() < intervals.get(i).getLower()) {
          stack.push(intervals.get(i));
        }
        else if (top.getUpper() < intervals.get(i).getUpper()) {
          top.setUpper(intervals.get(i).getUpper());
          stack.pop();
          stack.push(top);
        }
      }
    }
    return stack;
  }

  private static Coordinate getScanCoverageMinOnRow(List<Sensor> sensors, int y) {
    Coordinate result = null;
    for (Sensor sensor : sensors) {
      Coordinate coordinate = sensor.getScanCoverageMinOnRow(y);
      if (coordinate != null) {
        if (result == null)
          result = coordinate;
        else {
          if (coordinate.getX() < result.getX())
            result = coordinate;
        }
      }
    }
    return result;
  }

  private static Coordinate getScanCoverageMaxOnRow(List<Sensor> sensors, int y) {
    Coordinate result = null;
    for (Sensor sensor : sensors) {
      Coordinate coordinate = sensor.getScanCoverageMaxOnRow(y);
      if (coordinate != null) {
        if (result == null)
          result = coordinate;
        else {
          if (coordinate.getX() > result.getX())
            result = coordinate;
        }
      }
    }
    return result;
  }

  private static List<Sensor> createSensors(List<String> fileLines) {
    List<Sensor> sensors = new ArrayList<>();
    for (String fileLine : fileLines) {
      String[] mainPart = fileLine.split(":");

      String[] beaconPart = mainPart[1].trim().split(" ");
      String[] beaconXPart = beaconPart[4].replace(",", "").split("=");
      String[] beaconYPart = beaconPart[5].split("=");
      Coordinate beaconCoordinate = new Coordinate(Integer.parseInt(beaconXPart[1]), Integer.parseInt(beaconYPart[1]));
      Beacon beacon = new Beacon(beaconCoordinate);

      String[] sensorPart = mainPart[0].trim().split(" ");
      String[] sensorXPart = sensorPart[2].replace(",", "").split("=");
      String[] sensorYPart = sensorPart[3].split("=");
      Coordinate sensorCoordinate = new Coordinate(Integer.parseInt(sensorXPart[1]), Integer.parseInt(sensorYPart[1]));
      Sensor sensor = new Sensor(sensorCoordinate, beacon);

      sensors.add(sensor);
    }
    return sensors;
  }
}
