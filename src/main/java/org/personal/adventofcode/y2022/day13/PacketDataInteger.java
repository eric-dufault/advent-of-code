package org.personal.adventofcode.y2022.day13;

public class PacketDataInteger implements PacketData {

	private Integer value;

	public PacketDataInteger(Integer value) {
		this.value = value;
	}

	@Override
	public PacketDataType getType() {
		return PacketDataType.INTEGER;
	}

	public Integer getValue() {
		return value;
	}
}
