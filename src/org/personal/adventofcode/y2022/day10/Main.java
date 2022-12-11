package org.personal.adventofcode.y2022.day10;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
  private static final int CYCLE_LENGTH = 40;

  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Eric\\Projects\\advent-of-code\\src\\org\\personal\\adventofcode\\y2022\\day10\\input10.txt"));
    partA(lines);
    partB(lines);
  }

  private static void partA(List<String> lines) {
    int currentCycle = 0;
    int registerValue = 1;
    int[] interestingCycles = new int[] {20, 60, 100, 140, 180, 220};
    int[] interestingRegisterValues = new int[interestingCycles.length];
    int interestingCyclesIndex = 0;

    for (String line : lines) {
      CpuInstruction instruction = getCpuInstruction(line);
      if (instruction != null) {
        for (int i = 0; i < instruction.getCpuInstructionType().getCycles(); i++) {
          currentCycle++;
          if (Arrays.binarySearch(interestingCycles, currentCycle) >= 0) {
            interestingRegisterValues[interestingCyclesIndex] = registerValue;
            interestingCyclesIndex++;
          }
        }

        if (instruction.getCpuInstructionType().isModifyRegister())
          registerValue += instruction.getRegisterValue();
      }
    }

    int sum = 0;
    for (int i = 0; i < interestingCycles.length; i++)
      sum += (interestingCycles[i]*interestingRegisterValues[i]);
    System.out.println(sum);
  }

  private static void partB(List<String> lines) {
    int currentCycle = 0;
    int registerValue = 1;

    for (String line : lines) {
      CpuInstruction instruction = getCpuInstruction(line);
      for (int i = 0; i < instruction.getCpuInstructionType().getCycles(); i++) {
        if (Math.abs(currentCycle - registerValue) <= 1)
          System.out.print("#");
        else
          System.out.print(".");

        currentCycle++;

        if (currentCycle % CYCLE_LENGTH == 0) {
          System.out.println();
          currentCycle = 0;
        }
      }

      if (instruction.getCpuInstructionType().isModifyRegister())
        registerValue += instruction.getRegisterValue();
    }
  }

  private static CpuInstruction getCpuInstruction(String line) {
    CpuInstruction cpuInstruction = null;
    if (line.contains(CpuInstructionType.NOOP.getName())) {
      cpuInstruction = new CpuInstruction(CpuInstructionType.NOOP, null);
    }
    else if(line.contains(CpuInstructionType.ADD_X.getName())) {
      String[] split = line.split(" ");
      cpuInstruction = new CpuInstruction(CpuInstructionType.ADD_X, Integer.parseInt(split[1]));
    }
    return cpuInstruction;
  }
}