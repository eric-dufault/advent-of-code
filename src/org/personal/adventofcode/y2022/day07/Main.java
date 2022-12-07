package org.personal.adventofcode.y2022.day07;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
  private static final String CHANGE_DIRECTORY_COMMAND = "$ cd ";
  private static final String READ_DIRECTORY_COMMAND = "$ ls";
  private static final String DIRECTORY_IDENTIFIER = "dir";
  private static final String MOVE_PARENT_DIRECTORY = "..";

  public static void main(String[] args) throws Exception {
    List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Eric\\Projects\\advent-of-code\\src\\org\\personal\\adventofcode\\y2022\\day07\\input07.txt"));
    Directory root = getFilesystemRoot(lines);

    partA(root);
    partB(root);
  }

  private static Directory getFilesystemRoot(List<String> lines) {
    Directory root = new Directory(null, "/");
    Directory currentDirectory = root;
    for (int i = 1; i < lines.size(); i++) {
      String line = lines.get(i);
      if (line.startsWith(CHANGE_DIRECTORY_COMMAND)) {
        String dirName = line.replace(CHANGE_DIRECTORY_COMMAND, "");
        if (MOVE_PARENT_DIRECTORY.equals(dirName))
          currentDirectory = currentDirectory.getParent();
        else
          currentDirectory = currentDirectory.getSubDirectories().stream().filter(d -> dirName.equals(d.getName())).findFirst().get();
      }
      else if (line.startsWith(READ_DIRECTORY_COMMAND)) {}
      else {
        String[] parts = line.split(" ");
        if (DIRECTORY_IDENTIFIER.equals(parts[0])) {
          Directory subDirectory = currentDirectory.getSubDirectory(parts[0]);
          if (subDirectory == null) {
            currentDirectory.addSubDirectory(new Directory(currentDirectory, parts[1]));
          }
        } else {
          currentDirectory.addFile(new File(BigInteger.valueOf(Long.parseLong(parts[0])), parts[1]));
        }
      }
    }
    return root;
  }

  private static void partA(Directory root) {
    BigInteger threshold = BigInteger.valueOf(100000L);

    List<Directory> directories = collectChildren(root);
    Collections.sort(directories);

    BigInteger sum = BigInteger.ZERO;
    int i = 0;
    Directory directory = directories.get(0);
    BigInteger size = directory.getTotalSize();
    while(size.compareTo(threshold) <= 0) {
      sum = sum.add(size);
      directory = directories.get(++i);
      size = directory.getTotalSize();
    }
    System.out.println(sum);
  }

  private static void partB(Directory root) {
    BigInteger totalSpace = BigInteger.valueOf(70000000L);
    BigInteger requiredSpace = BigInteger.valueOf(30000000L);

    BigInteger usedSpace = root.getTotalSize();
    BigInteger availableSpace = totalSpace.subtract(usedSpace);
    BigInteger toFreeSpace = requiredSpace.subtract(availableSpace);

    List<Directory> directories = collectChildren(root);
    Collections.sort(directories);

    int i = 0;
    Directory directory = directories.get(i);
    BigInteger size = directory.getTotalSize();
    while(size.compareTo(toFreeSpace) <= 0) {
      directory = directories.get(++i);
      size = directory.getTotalSize();
    }
    System.out.println(directory.getName() + ": " + directory.getTotalSize());
  }

  private static List<Directory> collectChildren(Directory directory) {
    List<Directory> directories = new ArrayList<>();
    collectChildren(directory, directories);
    return directories;
  }

  private static void collectChildren(Directory directory, List<Directory> directories) {
    directories.add(directory);
    if (!directory.getSubDirectories().isEmpty()) {
      for (Directory subDirectory : directory.getSubDirectories())
        collectChildren(subDirectory, directories);
    }
  }

  private static void debug(Directory directory, String indent) {
    BigInteger totalSize = directory.getTotalSize();

    System.out.println(indent + directory.getName() + "(dir)" + "(" + totalSize.toString() + ")");
    indent += "  ";
    for (File file : directory.getFiles())
      System.out.println(indent + "- " + file.getName() + " " + file.getSize());

    if (!directory.getSubDirectories().isEmpty()) {
      for (Directory subDirectory : directory.getSubDirectories())
        debug(subDirectory, indent);
    }
  }
}
