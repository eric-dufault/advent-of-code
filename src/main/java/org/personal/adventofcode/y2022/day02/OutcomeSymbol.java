package org.personal.adventofcode.y2022.day02;


public enum OutcomeSymbol {
  X(Outcome.LOSE),
  Y(Outcome.DRAW),
  Z(Outcome.WIN);

  private Outcome outcome;

  OutcomeSymbol(Outcome outcome) {
    this.outcome = outcome;
  }

  public Outcome getOutcome() {
    return outcome;
  }
}
