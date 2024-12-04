package org.personal.adventofcode.y2024.day03;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2024\\day03\\input.txt"));

		partA(fileLines.get(0));
		partB(fileLines.get(0));
	}

	private static void partA(String instructions) {
		Pattern pattern = Pattern.compile("mul\\([0-9]+,[0-9]+\\)");
		long sum = 0;
		Matcher matcher = pattern.matcher(instructions);
		while (matcher.find()) {
			String mul = instructions.substring(matcher.start(), matcher.end());
			String[] split = mul.substring(3).split(",");

			long a = Long.parseLong(split[0].substring(1));
			long b = Long.parseLong(split[1].substring(0, split[1].length() - 1));

			sum += (a * b);
		}

		System.out.println(sum);
	}

	private static void partB(String instructions) {
		Pattern mulPattern = Pattern.compile("mul\\([0-9]+,[0-9]+\\)");
		Pattern doPattern = Pattern.compile("do\\(\\)");
		Pattern dontPattern = Pattern.compile("don't\\(\\)");

		long sum = 0;
		List<RangeEntry> ranges = new ArrayList<>();
		ranges.add(new RangeEntry(0, true));
		Matcher doMatcher = doPattern.matcher(instructions);
		while (doMatcher.find()) {
			ranges.add(new RangeEntry(doMatcher.start(), true));
		}

		Matcher dontMatcher = dontPattern.matcher(instructions);
		while (dontMatcher.find()) {
			ranges.add(new RangeEntry(dontMatcher.start(), false));
		}

		Collections.sort(ranges);

		Matcher mulMatcher = mulPattern.matcher(instructions);
		while (mulMatcher.find()) {
			RangeEntry rangeEntry = getRangeEntryForMul(ranges, mulMatcher.start());
			if (rangeEntry.doMul) {
				String mul = instructions.substring(mulMatcher.start(), mulMatcher.end());
				String[] split = mul.substring(3).split(",");

				long a = Long.parseLong(split[0].substring(1));
				long b = Long.parseLong(split[1].substring(0, split[1].length() - 1));

				sum += (a * b);
			}
		}

		System.out.println(sum);
	}

	private static RangeEntry getRangeEntryForMul(List<RangeEntry> ranges, int mulIndex) {
		RangeEntry r = null;
		for (int i = 0; i < ranges.size(); i++) {
			r = ranges.get(i);
			if (r.beginIndex > mulIndex) {
				r = ranges.get(i-1);
				break;
			}
		}
		return r;
	}

	private static class RangeEntry implements Comparable<RangeEntry> {
		private final int beginIndex;
		private final boolean doMul;

		protected RangeEntry(int beginIndex, boolean doMul) {
			this.beginIndex = beginIndex;
			this.doMul = doMul;
		}

		@Override
		public int compareTo(RangeEntry rangeEntry) {
			return Integer.compare(this.beginIndex, rangeEntry.beginIndex);
		}
	}

}
