package org.personal.adventofcode.y2024.day04;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws Exception {
		List<String> fileLines = Files.readAllLines(Paths.get("src\\main\\resources\\y2024\\day04\\input.txt"));

		partA(fileLines);
		partB(fileLines);
	}

	private static void partA(List<String> fileLines) {
		Set<Word> words = new HashSet<>();
		for (int i = 0; i < fileLines.size(); i++) {
			String line = fileLines.get(i);
			for (int j = 0; j < line.length(); j++) {
				if (line.charAt(j) == 'X') {
					words.addAll(findWords(fileLines, i, j));
				}
			}
		}

		System.out.println(words.size());
	}

	private static void partB(List<String> fileLines) {
		int count = 0;
		for (int i = 0; i < fileLines.size() - 2; i++) {
			for (int j = 0; j < fileLines.get(i).length() - 2; j++) {
				char topLeft = fileLines.get(i).charAt(j);
				char topRight = fileLines.get(i).charAt(j+2);
				char middle = fileLines.get(i+1).charAt(j+1);
				char bottomLeft = fileLines.get(i+2).charAt(j);
				char bottomRight = fileLines.get(i+2).charAt(j+2);
				if (middle == 'A') {
					if (topLeft == 'M' && topRight == 'M' && bottomLeft == 'S' && bottomRight == 'S')
						count++;
					else if (topLeft == 'M' && topRight == 'S' && bottomLeft == 'M' && bottomRight == 'S')
						count++;
					else if (topLeft == 'S' && topRight == 'M' && bottomLeft == 'S' && bottomRight == 'M')
						count++;
					else if (topLeft == 'S' && topRight == 'S' && bottomLeft == 'M' && bottomRight == 'M')
						count++;
				}
			}
		}

		System.out.println(count);
	}

	private static Set<Word> findWords(List<String> fileLines, int x, int y) {
		Set<Word> words = new HashSet<>();

		//right
		if (inBounds(fileLines, x, y+1) && fileLines.get(x).charAt(y+1) == 'M'
				&& inBounds(fileLines, x, y+2) && fileLines.get(x).charAt(y+2) == 'A'
				&& inBounds(fileLines, x, y+3) && fileLines.get(x).charAt(y+3) == 'S') {
			words.add(new Word(new Coord(x,y), new Coord(x,y+1), new Coord(x,y+2), new Coord(x,y+3)));
		}

		//left
		if (inBounds(fileLines, x, y-1) && fileLines.get(x).charAt(y-1) == 'M'
				&& inBounds(fileLines, x, y-2) && fileLines.get(x).charAt(y-2) == 'A'
				&& inBounds(fileLines, x, y-3) && fileLines.get(x).charAt(y-3) == 'S') {
			words.add(new Word(new Coord(x,y), new Coord(x,y-1), new Coord(x,y-2), new Coord(x,y+3)));
		}

		//up
		if (inBounds(fileLines, x+1, y) && fileLines.get(x+1).charAt(y) == 'M'
				&& inBounds(fileLines, x+2, y) && fileLines.get(x+2).charAt(y) == 'A'
				&& inBounds(fileLines, x+3, y) && fileLines.get(x+3).charAt(y) == 'S') {
			words.add(new Word(new Coord(x,y), new Coord(x+1,y), new Coord(x+2,y), new Coord(x+3,y)));
		}

		//down
		if (inBounds(fileLines, x-1, y) && fileLines.get(x-1).charAt(y) == 'M'
				&& inBounds(fileLines, x-2, y) && fileLines.get(x-2).charAt(y) == 'A'
				&& inBounds(fileLines, x-3, y) && fileLines.get(x-3).charAt(y) == 'S') {
			words.add(new Word(new Coord(x,y), new Coord(x-1,y), new Coord(x-2,y), new Coord(x-3,y)));
		}

		//diagonal left up
		if (inBounds(fileLines, x-1, y-1) && fileLines.get(x-1).charAt(y-1) == 'M'
				&& inBounds(fileLines, x-2, y-2) && fileLines.get(x-2).charAt(y-2) == 'A'
				&& inBounds(fileLines, x-3, y-3) && fileLines.get(x-3).charAt(y-3) == 'S') {
			words.add(new Word(new Coord(x,y), new Coord(x-1,y-1), new Coord(x-2,y-2), new Coord(x-3,y-3)));
		}

		//diagonal right up
		if (inBounds(fileLines, x-1, y+1) && fileLines.get(x-1).charAt(y+1) == 'M'
				&& inBounds(fileLines, x-2, y+2) && fileLines.get(x-2).charAt(y+2) == 'A'
				&& inBounds(fileLines, x-3, y+3) && fileLines.get(x-3).charAt(y+3) == 'S') {
			words.add(new Word(new Coord(x,y), new Coord(x-1,y+1), new Coord(x-2,y+2), new Coord(x-3,y+3)));
		}

		//diagonal left down
		if (inBounds(fileLines, x+1, y-1) && fileLines.get(x+1).charAt(y-1) == 'M'
				&& inBounds(fileLines, x+2, y-2) && fileLines.get(x+2).charAt(y-2) == 'A'
				&& inBounds(fileLines, x+3, y-3) && fileLines.get(x+3).charAt(y-3) == 'S') {
			words.add(new Word(new Coord(x,y), new Coord(x+1,y-1), new Coord(x+2,y-2), new Coord(x+3,y-3)));
		}

		//diagonal right down
		if (inBounds(fileLines, x+1, y+1) && fileLines.get(x+1).charAt(y+1) == 'M'
				&& inBounds(fileLines, x+2, y+2) && fileLines.get(x+2).charAt(y+2) == 'A'
				&& inBounds(fileLines, x+3, y+3) && fileLines.get(x+3).charAt(y+3) == 'S') {
			words.add(new Word(new Coord(x,y), new Coord(x+1,y+1), new Coord(x+2,y+2), new Coord(x+3,y+3)));
		}

		return words;
	}

	private static boolean inBounds(List<String> fileLines, int i, int j) {
		return 0 <= i && i < fileLines.size() && 0 <= j && j < fileLines.get(0).length();
	}

	private static class Coord {
		private final int x;
		private final int y;

		protected Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean equals(Object o) {
			if (!(o instanceof Coord)) {
				return false;
			}

			Coord c = (Coord) o;
			return this.x == c.x && this.y == c.y;
		}

		public int hashCode() {
			return 31 + this.x + this.y;
		}

		public String toString() {
			return "(" + this.x + "," + this.y + ")";
		}
	}

	private static class Word {
		private final Coord x;
		private final Coord m;
		private final Coord a;
		private final Coord s;

		protected Word(Coord x, Coord m, Coord a, Coord s) {
			this.x = x;
			this.m = m;
			this.a = a;
			this.s = s;
		}

		public boolean equals(Object o) {
			if (!(o instanceof Word)) {
				return false;
			}

			Word w = (Word) o;
			return this.x.equals(w.x) && this.m.equals(w.m) && this.a.equals(w.a) && this.s.equals(w.s);
		}

		public int hashCode() {
			return this.x.hashCode() + this.m.hashCode() + this.a.hashCode() + this.s.hashCode();
		}

		public String toString() {
			return "x="+this.x +" m="+this.m + " a"+this.a + " s"+this.s;
		}
	}
}
