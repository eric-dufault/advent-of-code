package org.personal.adventofcode.y2022.day10;

public class Instruction {
  private InstructionType instructionType;
  private Integer registerValue;

  public Instruction(InstructionType instructionType, Integer registerValue) {
    this.instructionType = instructionType;
    this.registerValue = registerValue;
  }

  public InstructionType getInstructionType() {
    return instructionType;
  }

  public Integer getRegisterValue() {
    return registerValue;
  }
}
