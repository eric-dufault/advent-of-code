package org.personal.adventofcode.y2022.day12;

import java.util.ArrayList;
import java.util.List;

public class Node {
  private char key;
  private int row;
  private int col;

  private Node parent;
  private List<Node> adjacentNodes = new ArrayList<>();
  private boolean visited;

  public Node(char key, int row, int col) {
    this.key = key;
    this.row = row;
    this.col = col;
  }

  public char getKey() {
    return key;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public Node getParent() {
    return parent;
  }

  public void setParent(Node parent) {
    this.parent = parent;
  }

  public boolean isVisited() {
    return visited;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public List<Node> getAdjacentNodes() {
    return adjacentNodes;
  }

  public void addAdjacentNode(Node node) {
    adjacentNodes.add(node);
  }

  public Integer getHeight() {
    return Character.getNumericValue(key) - 9;
  }

  public boolean isAllowedStep(Node adjacentNode) {
    return adjacentNode.getHeight() - getHeight() <= 1;
  }

  public void resetBfs() {
    parent = null;
    visited = false;
  }
}
