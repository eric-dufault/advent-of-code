package org.personal.adventofcode.y2022.day05;

import java.util.Stack;

public class Move {

  private Integer moveCrateCount;
  private Stack<Character> sourceStack;
  private Stack<Character> destinationStack;

  public Move(Integer moveCreateCount, Stack<Character> sourceStack, Stack<Character> destinationStack) {
    this.moveCrateCount = moveCreateCount;
    this.sourceStack = sourceStack;
    this.destinationStack = destinationStack;
  }

  public void performMove() {
    for (int i = 0; i < moveCrateCount; i++) {
      Character c = sourceStack.pop();
      destinationStack.push(c);
    }
  }

  public void performUnitMove() {
    char[] temp = new char[moveCrateCount];
    for(int i = 0 ; i < moveCrateCount; i++) {
      Character c = sourceStack.pop();
      temp[i] = c;
    }
    for(int i = temp.length-1; i >= 0; i--) {
      destinationStack.push(temp[i]);
    }
  }
}
