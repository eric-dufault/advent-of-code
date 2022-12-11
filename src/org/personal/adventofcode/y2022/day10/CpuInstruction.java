package org.personal.adventofcode.y2022.day10;

public class CpuInstruction {
  private CpuInstructionType cpuInstructionType;
  private Integer registerValue;

  public CpuInstruction(CpuInstructionType cpuInstructionType, Integer registerValue) {
    this.cpuInstructionType = cpuInstructionType;
    this.registerValue = registerValue;
  }

  public CpuInstructionType getCpuInstructionType() {
    return cpuInstructionType;
  }

  public Integer getRegisterValue() {
    return registerValue;
  }
}
