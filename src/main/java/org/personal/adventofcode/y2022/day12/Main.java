package org.personal.adventofcode.y2022.day12;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Main {
  private static final char START_POSITION = 'S';
  private static final char END_POSITION = 'E';

  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("src\\main\\resources\\y2022\\day12\\input12.txt"));
    partA(lines);
    partB(lines);
  }

  private static void partA(List<String> lines) {
    Graph graph = createGraph(lines, false);

    Node head = graph.getHeads().iterator().next();
    calculateBfs(graph, head);

    System.out.println(graph.getPathLength());
  }

  private static void partB(List<String> lines) {
    Graph graph = createGraph(lines, true);

    List<Integer> pathLengths = new ArrayList<>();
    for (Node head : graph.getHeads()) {
      calculateBfs(graph, head);
      int pathLength = graph.getPathLength();
      if (pathLength > 0) {
        pathLengths.add(pathLength);
      }
      graph.resetBfs();
    }

    Collections.sort(pathLengths);
    System.out.println(pathLengths.get(0));
  }

  private static void calculateBfs(Graph graph, Node head) {
    Node target = graph.getTarget();

    Queue<Node> queue = new ArrayDeque<>();
    queue.add(head);
    head.setVisited(true);
    head.setParent(null);
    while (!queue.isEmpty()) {
      Node current = queue.poll();
      for (Node adjacent : current.getAdjacentNodes()) {
        if (!adjacent.isVisited()) {
          queue.add(adjacent);
          adjacent.setVisited(true);
          adjacent.setParent(current);
          if (adjacent.equals(target)) {
            break;
          }
        }
      }
    }
  }

  private static Graph createGraph(List<String> lines, boolean multipleHeads) {
    Set<Node> heads = new HashSet<>();
    Node target = null;

    Node[][] nodes = new Node[lines.size()][lines.get(0).length()];
    for (int i = 0; i < lines.size(); i++) {
      for (int j = 0; j < lines.get(0).length(); j++) {
        char key = lines.get(i).charAt(j);
        Node node = null;
        if (START_POSITION == key) {
          node = new Node('a', i, j);
          heads.add(node);
        }
        if (END_POSITION == key) {
          node = new Node('z', i, j);
          target = node;
        }

        if (START_POSITION != key && END_POSITION != key) {
          node = new Node(key, i, j);
          if (multipleHeads && key == 'a')
            heads.add(node);
        }

        nodes[i][j] = node;
      }
    }

    //set adjacent weights and nodes
    for (int i = 0; i < lines.size(); i++) {
      for (int j = 0; j < lines.get(0).length(); j++) {
        Node node = nodes[i][j];
        if (0 <= (j+1) && (j+1) < nodes[0].length && node.isAllowedStep(nodes[i][j + 1])) {
          node.addAdjacentNode(nodes[i][j+1]);
        }

        if (0 <= (i+1) && (i+1) < nodes.length && node.isAllowedStep(nodes[i + 1][j])) {
          node.addAdjacentNode(nodes[i+1][j]);
        }

        if (0 <= (j-1) && (j-1) < nodes[0].length && node.isAllowedStep(nodes[i][j - 1])) {
          node.addAdjacentNode(nodes[i][j-1]);
        }

        if (0 <= (i-1) && (i-1) < nodes.length && node.isAllowedStep(nodes[i - 1][j])) {
          node.addAdjacentNode(nodes[i-1][j]);
        }
      }
    }

    return new Graph(nodes, heads, target);
  }
}
