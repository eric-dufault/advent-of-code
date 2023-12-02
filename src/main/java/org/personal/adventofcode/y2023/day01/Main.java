package org.personal.adventofcode.y2023.day01;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2023\\day01\\input.txt"));

		PartA.execute(fileLines);
		PartB.execute(fileLines);
	}
}
