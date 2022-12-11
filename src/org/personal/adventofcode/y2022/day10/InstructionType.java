package org.personal.adventofcode.y2022.day10;

public class InstructionType {

  public static InstructionType ADD_X = new InstructionType("addx", 2, true);
  public static InstructionType NOOP = new InstructionType("noop", 1, false);

  private String name;
  private int cycles;
  private boolean modifyRegister;

  public InstructionType(String name, int cycles, boolean modifyRegister) {
    this.name = name;
    this.cycles = cycles;
    this.modifyRegister = modifyRegister;
  }

  public String getName() {
    return name;
  }

  public int getCycles() {
    return cycles;
  }

  public boolean isModifyRegister() {
    return modifyRegister;
  }
}
