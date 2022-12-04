package org.personal.adventofcode.y2022.day02;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.personal.adventofcode.y2022.day02.Outcome.*;
import static org.personal.adventofcode.y2022.day02.Shape.*;

public class Main {

  public static void main(String[] args) {
    try {
      List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Eric\\Projects\\advent-of-code\\src\\org\\personal\\adventofcode\\y2022\\day02\\input02.txt"));
      partA(lines);
      partB(lines);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private static void partA(List<String> lines) {
    int score = 0;
    for (String line : lines) {
      String[] plays = line.trim().split(" ");
      score += getOutcome(OpponentSymbol.valueOf(plays[0]).getShape(), PlayerSymbol.valueOf(plays[1]).getShape()).getScore();
      score += PlayerSymbol.valueOf(plays[1]).getShape().getAdditionalScore();
    }
    System.out.println(score);
  }

  private static Outcome getOutcome(Shape opponentShape, Shape playerShape) {
    Outcome outcome = null;
    switch(playerShape) {
      case ROCK:
        if (opponentShape == SCISSORS)
          outcome = WIN;
        else if (opponentShape == ROCK)
          outcome = DRAW;
        else
          outcome = LOSE;
        break;
      case PAPER:
        if (opponentShape == ROCK)
          outcome = WIN;
        else if (opponentShape == PAPER)
          outcome = DRAW;
        else
          outcome = LOSE;
        break;
      case SCISSORS:
        if (opponentShape == PAPER)
          outcome = WIN;
        else if (opponentShape == SCISSORS)
          outcome = DRAW;
        else
          outcome = LOSE;
        break;
    }
    return outcome;
  }

  private static void partB(List<String> lines) {
    int score = 0;
    for (String line : lines) {
      String[] plays = line.trim().split(" ");
      Shape opponentShape = OpponentSymbol.valueOf(plays[0]).getShape();
      Outcome outcome = OutcomeSymbol.valueOf(plays[1]).getOutcome();

      score += getPlayerShape(opponentShape, outcome).getAdditionalScore();
      score += outcome.getScore();
    }
    System.out.println(score);
  }

  private static Shape getPlayerShape(Shape opponentShape, Outcome outcome) {
    Shape playerShape = null;
    switch(outcome) {
      case WIN:
        if (opponentShape == ROCK)
          playerShape = Shape.PAPER;
        else if (opponentShape == PAPER)
          playerShape = Shape.SCISSORS;
        else
          playerShape = Shape.ROCK;
        break;
      case DRAW:
        playerShape = opponentShape;
        break;
      case LOSE:
        if (opponentShape == ROCK)
          playerShape = SCISSORS;
        else if (opponentShape == PAPER)
          playerShape = ROCK;
        else
          playerShape = PAPER;
        break;
    }
    return playerShape;
  }
}
