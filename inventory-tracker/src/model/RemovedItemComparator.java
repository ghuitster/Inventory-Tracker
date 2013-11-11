package model;

import java.util.Comparator;

public class RemovedItemComparator implements Comparator<IItem>
{

	@Override
	public int compare(IItem arg0, IItem arg1)
	{
		return arg1.getExitTime().compareTo(arg0.getExitTime());
	}

}
