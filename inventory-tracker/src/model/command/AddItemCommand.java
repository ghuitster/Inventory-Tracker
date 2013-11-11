
package model.command;

import gui.batches.AddItemBatchController;

import java.util.List;

import model.IItem;
import model.IProductContainer;

public class AddItemCommand extends MultipleItemCommand
{
	private final boolean productCreated;

	public AddItemCommand(IProductContainer target, List<IItem> items,
			boolean productCreated)
	{
		super(target, items);
		this.productCreated = productCreated;
	}

	/**
	 * Add the item(s) in this object's Set of Items
	 */
	@Override
	public void execute()
	{
		if(target.ableToAddProduct(AddItemBatchController.product))
			target.addProduct(AddItemBatchController.product);

		for(IItem ii: items)
			if(target.ableToAddItem(ii))
				target.addItem(ii);
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
