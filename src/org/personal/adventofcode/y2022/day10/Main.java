package org.personal.adventofcode.y2022.day10;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
  private static final int CYCLE_LENGTH = 40;
  private static final int CYCLE_OFFSET = 20;

  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Eric\\Projects\\advent-of-code\\src\\org\\personal\\adventofcode\\y2022\\day10\\input10.txt"));
    partA(lines);
    partB(lines);
  }

  private static void partA(List<String> lines) {
    int currentCycle = 0;
    int registerValue = 1;
    List<Cycle> interestingCycles = new ArrayList<>();

    for (String line : lines) {
      Instruction cpuInstruction = getInstruction(line);
      if (cpuInstruction != null) {
        for (int i = 0; i < cpuInstruction.getInstructionType().getCycles(); i++) {
          currentCycle++;

          if ((currentCycle + CYCLE_OFFSET) % CYCLE_LENGTH == 0)
            interestingCycles.add(new Cycle(currentCycle, registerValue));
        }

        if (cpuInstruction.getInstructionType().isModifyRegister())
          registerValue += cpuInstruction.getRegisterValue();
      }
    }

    int sum = interestingCycles.stream().mapToInt(s -> s.getCycleNumber() * s.getRegisterValue()).reduce(0, Integer::sum);
    System.out.println(sum);
  }

  private static void partB(List<String> lines) {
    int currentCycle = 0;
    int registerValue = 1;

    for (String line : lines) {
      Instruction crtInstruction = getInstruction(line);
      for (int i = 0; i < crtInstruction.getInstructionType().getCycles(); i++) {
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

      if (crtInstruction.getInstructionType().isModifyRegister())
        registerValue += crtInstruction.getRegisterValue();
    }
  }

  private static Instruction getInstruction(String line) {
    Instruction instruction = null;
    if (line.contains(InstructionType.NOOP.getName())) {
      instruction = new Instruction(InstructionType.NOOP, null);
    }
    else if(line.contains(InstructionType.ADD_X.getName())) {
      String[] split = line.split(" ");
      instruction = new Instruction(InstructionType.ADD_X, Integer.parseInt(split[1]));
    }
    return instruction;
  }
}