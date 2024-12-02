package org.personal.adventofcode.y2024.day02;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static final int DEFAULT_REPORTS_SIZE = 1000;

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2024\\day02\\input.txt"));
		List<Report> reports = parseReports(fileLines);

		long partACount = reports.stream().filter(Report::isSafe).count();
		System.out.println(partACount);

		long partBCount = reports.stream().filter(Report::isSafeWithDampener).count();
		System.out.println(partBCount);
	}

	private static List<Report> parseReports(List<String> fileLines) {
		List<Report> reports = new ArrayList<>(DEFAULT_REPORTS_SIZE);
		for (String line : fileLines) {
			Report report = new Report();
			String[] split = line.split(" ");
			for (String s : split) {
				report.addLevel(Integer.parseInt(s));
			}
			reports.add(report);
		}
		return reports;
	}

}
