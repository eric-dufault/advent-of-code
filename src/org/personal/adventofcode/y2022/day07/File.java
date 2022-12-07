package org.personal.adventofcode.y2022.day07;

import java.math.BigInteger;

public class File {
  private String name;
  private BigInteger size;

  public File(BigInteger size, String name) {
    this.size = size;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public BigInteger getSize() {
    return size;
  }
}
