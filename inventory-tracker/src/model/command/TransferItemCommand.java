
package model.command;

import java.util.List;

import model.IItem;
import model.IProduct;

public class TransferItemCommand implements Command
{
	private final List<IItem> items;
	private final List<IProduct> products;

	public TransferItemCommand(List<IItem> items, List<IProduct> products)
	{
		this.items = items;
		this.products = products;
	}

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

}
