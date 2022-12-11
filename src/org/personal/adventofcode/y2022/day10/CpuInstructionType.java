package org.personal.adventofcode.y2022.day10;

public class CpuInstructionType {

  public static CpuInstructionType ADD_X = new CpuInstructionType("addx", 2, true);
  public static CpuInstructionType NOOP = new CpuInstructionType("noop", 1, false);

  private String name;
  private int cycles;
  private boolean modifyRegister;

  public CpuInstructionType(String name, int cycles, boolean modifyRegister) {
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
