
package model.command;

import model.IItem;
import model.IProductContainer;
import model.Inventory;

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
		if(this.target.ableToRemoveItem(this.item))
			this.target.removeItem(this.item);
	}

	/**
	 * Un-remove the items in this object's Set of Items
	 */
	@Override
	public void undo()
	{
		this.target.addItem(this.item);
	}
}
