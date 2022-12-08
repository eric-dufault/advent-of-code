package org.personal.adventofcode.y2022.day08;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Eric\\Projects\\advent-of-code\\src\\org\\personal\\adventofcode\\y2022\\day08\\input08.txt"));

    int cols = lines.size();
    int rows = lines.get(0).length();
    Tree[][] tree = new Tree[rows][cols];

    List<Tree> trees = new ArrayList<>();
    for (int i = 0; i < lines.size(); i++) {
      for (int j = 0; j < lines.get(i).length(); j++) {
        tree[i][j] = new Tree(Integer.parseInt(Character.toString(lines.get(i).charAt(j))));
      }
    }

    int visible = (2 * rows) + (2 * cols) - 4;

    for (int i = 1; i < rows-1; i++) {
      for (int j = 1; j < cols-1; j++) {
        trees.add(tree[i][j]);

        //check if visible looking up
        boolean visibleUp = true;
        for (int u = i-1; u >= 0; u--) {
          tree[i][j].setViewUp(tree[i][j].getViewUp()+1);
          if (tree[i][j].getHeight() <= tree[u][j].getHeight()) {
            visibleUp = false;
            break;
          }
        }
        if (visibleUp) {
          tree[i][j].setVisible(true);
        }

        //check if visible looking down
        boolean visibleDown = true;
        for (int d = i+1; d < rows; d++) {
          tree[i][j].setViewDown(tree[i][j].getViewDown() + 1);
          if (tree[i][j].getHeight() <= tree[d][j].getHeight()) {
            visibleDown = false;
            break;
          }
        }
        if (visibleDown) {
          tree[i][j].setVisible(true);
        }

        //check if visible looking right
        boolean visibleRight = true;
        for (int r = j+1; r < cols; r++) {
          tree[i][j].setViewRight(tree[i][j].getViewRight() + 1);
          if (tree[i][j].getHeight() <= tree[i][r].getHeight()) {
            visibleRight = false;
            break;
          }
        }
        if (visibleRight) {
          tree[i][j].setVisible(true);
        }

        //check if visible looking left
        boolean visibleLeft = true;
        for (int l = j-1; l >= 0; l--) {
          tree[i][j].setViewLeft(tree[i][j].getViewLeft()+1);
          if (tree[i][j].getHeight() <= tree[i][l].getHeight()) {
            visibleLeft = false;
            break;
          }
        }
        if (visibleLeft) {
          tree[i][j].setVisible(true);
        }

        if (tree[i][j].isVisible()) {
          visible++;
        }
      }
    }

    System.out.println(visible);

    Collections.sort(trees, Collections.reverseOrder());
    System.out.println(trees.get(0).getScenicScore());
  }

}
