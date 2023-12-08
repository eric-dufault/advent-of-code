package org.personal.adventofcode.y2023.day07;

import org.personal.adventofcode.y2023.day07.partA.PartA;
import org.personal.adventofcode.y2023.day07.partB.PartB;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2023\\day07\\input.txt"));

		PartA.execute(fileLines);
		PartB.execute(fileLines);
	}

}
