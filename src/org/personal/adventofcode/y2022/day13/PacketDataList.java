package org.personal.adventofcode.y2022.day13;

import java.util.ArrayList;
import java.util.List;

public class PacketDataList implements PacketData {

	private PacketDataList parentList;
	private List<PacketData> list = new ArrayList<>();

	public PacketDataList(PacketDataList parentList) {
		this.parentList = parentList;
	}

	@Override
	public PacketDataType getType() {
		return PacketDataType.LIST;
	}

	public void add(PacketData packetData) {
		list.add(packetData);
	}

	public List<PacketData> getList() {
		return list;
	}

	public PacketDataList getParentList() {
		return parentList;
	}
}
