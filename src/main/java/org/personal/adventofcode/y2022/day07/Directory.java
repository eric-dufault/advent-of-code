package org.personal.adventofcode.y2022.day07;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Directory implements Comparable<Directory> {
  private Directory parent;
  private String name;
  private List<Directory> subDirectories = new ArrayList<>();
  private List<File> files = new ArrayList<>();
  private BigInteger totalSize = BigInteger.ZERO;

  public Directory(Directory parent, String name) {
    this.parent = parent;
    this.name = name;
  }

  public void addFile(File file) {
    files.add(file);
    totalSize = totalSize.add(file.getSize());

    Directory d = this.getParent();
    while (d != null) {
      d.setTotalSize(d.getTotalSize().add(file.getSize()));
      d = d.getParent();
    }
  }

  public void addSubDirectory(Directory directory) {
    subDirectories.add(directory);
  }

  public Directory getSubDirectory(String name) {
    return subDirectories.stream().filter(sd -> sd.getName().equals(name)).findFirst().orElse(null);
  }

//  public BigInteger getFilesSize() {
//    BigInteger filesSize = BigInteger.ZERO;
//    for (File file : files)
//      filesSize = filesSize.add(file.getSize());
//    return filesSize;
//  }
//
//  public BigInteger getTotalSize() {
//    return getTotalSizeHelper(this);
//  }
//
//  private BigInteger getTotalSizeHelper(Directory directory) {
//    BigInteger size = directory.getFilesSize();
//
//    if (!directory.getSubDirectories().isEmpty()) {
//      for (Directory subDirectory : directory.getSubDirectories())
//        size = size.add(getTotalSizeHelper(subDirectory));
//    }
//    return size;
//  }

  public BigInteger getTotalSize() {
    return this.totalSize;
  }

  public void setTotalSize(BigInteger totalSize) {
    this.totalSize = totalSize;
  }

  public Directory getParent() {
    return parent;
  }

  public String getName() {
    return name;
  }

  public List<File> getFiles() {
    return files;
  }

  public List<Directory> getSubDirectories() {
    return subDirectories;
  }

  @Override
  public int compareTo(Directory o) {
    return this.getTotalSize().compareTo(o.getTotalSize());
  }
}
