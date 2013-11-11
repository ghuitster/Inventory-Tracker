package model;

import java.io.Serializable;
import java.util.Comparator;

public class RemovedItemComparator implements Comparator<IItem>, Serializable
{

	@Override
	public int compare(IItem arg0, IItem arg1)
	{
		return arg1.getExitTime().compareTo(arg0.getExitTime());
	}

}
