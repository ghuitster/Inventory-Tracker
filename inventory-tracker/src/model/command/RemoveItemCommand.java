
package model.command;

import model.IItem;
import model.IProductContainer;

public class RemoveItemCommand extends SingleItemCommand
{
	public RemoveItemCommand(IProductContainer target, IItem item)
	{
		super(target, item);
	}
	/**
	 * Remove the item(s) in this object's Set of Items
	 */
	@Override
	public void execute()
	{
		// TODO Auto-generated method stub

	}

	/**
	 * Un-remove the items in this object's Set of Items
	 */
	@Override
	public void undo()
	{
		// TODO Auto-generated method stub

	}
}
