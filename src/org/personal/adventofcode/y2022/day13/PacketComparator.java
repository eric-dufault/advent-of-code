package org.personal.adventofcode.y2022.day13;

import java.util.Comparator;

public class PacketComparator implements Comparator<PacketData> {

	@Override
	public int compare(PacketData left, PacketData right) {
		if (PacketDataType.INTEGER.equals(left.getType()) && PacketDataType.INTEGER.equals(right.getType())) {
			PacketDataInteger leftInt = (PacketDataInteger) left;
			PacketDataInteger rightInt = (PacketDataInteger) right;

			if (leftInt.getValue() < rightInt.getValue())
				return -1;  //right order
			else if (leftInt.getValue() > rightInt.getValue())
				return 1;  //not in the right order
			else
				return 0;  //continue
		}
		else if(PacketDataType.LIST.equals(left.getType()) && PacketDataType.LIST.equals(right.getType())) {
			PacketDataList leftList = (PacketDataList) left;
			PacketDataList rightList = (PacketDataList) right;

			int i = 0;
			while (i < leftList.getList().size() && i < rightList.getList().size()) {
				int result = compare(leftList.getList().get(i), rightList.getList().get(i));
				if (result != 0)
					return result;

				i++;
			}

			if (leftList.getList().size() == i && rightList.getList().size() > i)
				return -1;
			else if (leftList.getList().size() > i && rightList.getList().size() == i)
				return 1;
			else
				return 0;
		}
		else {
			//either left or right is an INT and the other is a LIST
			if (PacketDataType.INTEGER.equals(left.getType())) {
				PacketDataList pdl = new PacketDataList(null);
				pdl.add(left);
				return compare(pdl, right);
			} else {
				PacketDataList pdl = new PacketDataList(null);
				pdl.add(right);
				return compare(left, pdl);
			}
		}
	}
}
