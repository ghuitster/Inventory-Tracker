
package model.command;

import model.IItem;

public class TransferItemCommand extends Command
{
	/**
	 * Transfer the item(s) in this object's Set of Items
	 */
	@Override
	public void execute()
	{
		// TODO Auto-generated method stub

	}

	/**
	 * Un-transfer the items in this object's Set of Items
	 */
	@Override
	public void undo()
	{
		// TODO Auto-generated method stub

	}
	
	public void setItem(IItem item)
	{
		this.items.add(item);
	}
}
