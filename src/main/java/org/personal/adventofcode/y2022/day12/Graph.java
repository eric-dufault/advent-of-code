package org.personal.adventofcode.y2022.day12;

import java.util.HashSet;
import java.util.Set;

public class Graph {
  private Node[][] nodes;
  private Set<Node> heads = new HashSet<>();
  private Node target;

  public Graph(Node[][] nodes, Node head, Node target) {
    this.nodes = nodes;
    this.heads.add(head);
    this.target = target;
  }

  public Graph(Node[][] nodes, Set<Node> heads, Node target) {
    this.nodes = nodes;
    this.heads.addAll(heads);
    this.target = target;
  }

  public Node[][] getNodes() {
    return nodes;
  }

  public Node getTarget() {
    return target;
  }

  public Set<Node> getHeads() {
    return heads;
  }

  public void resetBfs() {
    for (int i = 0; i < nodes.length; i++) {
      for (int j = 0; j < nodes[i].length; j++) {
        nodes[i][j].resetBfs();
      }
    }
  }

  public int getPathLength() {
    int count = 0;
    Node current = target;
    while (current != null) {
      current = current.getParent();
      count++;
    }
    return count - 1;
  }
}
