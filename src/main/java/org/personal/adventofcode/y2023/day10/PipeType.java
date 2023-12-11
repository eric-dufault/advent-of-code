package org.personal.adventofcode.y2023.day10;

import java.util.Map;

public class PipeType {

	public static final PipeType VERTICAL = new PipeType('|', false, false, true, true);
	public static final PipeType HORIZONTAL = new PipeType('-', true, true, false, false);
	public static final PipeType BEND_NORTH_EAST = new PipeType('L', true, false, true, false);
	public static final PipeType BEND_NORTH_WEST = new PipeType('J', false, true, true, false);
	public static final PipeType BEND_SOUTH_WEST = new PipeType('7', false, true, false, true);
	public static final PipeType BEND_SOUTH_EAST = new PipeType('F', true, false, false, true);
	public static final PipeType GROUND = new PipeType('.', false, false, false, false);
	public static final PipeType STARTING_POSITION = new PipeType('S', true, true, true, true);

	private static final Map<Character, PipeType> PIPE_TYPE_MAP = Map.ofEntries(
			Map.entry('|', VERTICAL), Map.entry('-', HORIZONTAL), Map.entry('L', BEND_NORTH_EAST),
			Map.entry('J', BEND_NORTH_WEST), Map.entry('7', BEND_SOUTH_WEST), Map.entry('F', BEND_SOUTH_EAST),
			Map.entry('.', GROUND), Map.entry('S', STARTING_POSITION)
	);

	public static PipeType findByGlyph(char c) {
		return PIPE_TYPE_MAP.get(c);
	}

	private final char glyph;
	private final boolean acceptsRight;
	private final boolean acceptsLeft;
	private final boolean acceptsUp;
	private final boolean acceptsDown;

	private PipeType(char glyph, boolean acceptsRight, boolean acceptsLeft, boolean acceptsUp, boolean acceptsDown) {
		this.glyph = glyph;
		this.acceptsRight = acceptsRight;
		this.acceptsLeft = acceptsLeft;
		this.acceptsUp = acceptsUp;
		this.acceptsDown = acceptsDown;
	}

	public char getGlyph() {
		return glyph;
	}

	public boolean isAcceptsRight() {
		return acceptsRight;
	}


	public boolean isAcceptsLeft() {
		return acceptsLeft;
	}


	public boolean isAcceptsUp() {
		return acceptsUp;
	}


	public boolean isAcceptsDown() {
		return acceptsDown;
	}

	public boolean equals(PipeType o) {
		if (o == null)
			return false;

		if (!(o instanceof PipeType))
			return false;

		PipeType other = (PipeType) o;
		return getGlyph() == other.getGlyph();
	}

	public int hashCode() {
		return getGlyph();
	}
}
