
package model.command;

import java.util.List;

import model.IItem;
import model.IProduct;

public class AddItemCommand implements Command
{
	private final List<IItem> items;
	private final List<IProduct> products;

	public AddItemCommand(List<IItem> items, List<IProduct> products)
	{
		this.items = items;
		this.products = products;
	}

	/**
	 * Add the item(s) in this object's Set of Items
	 */
	@Override
	public void execute()
	{
		// TODO Auto-generated method stub

	}

	/**
	 * Un-add the items in this object's Set of Items
	 */
	@Override
	public void undo()
	{
		// TODO Auto-generated method stub

	}

}
