
package model.command;

import gui.item.ItemData;
import gui.product.ProductData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.IItem;
import model.IProductContainer;

public class TransferItemCommand extends Command
{
	private Map<ProductData, List<ItemData>> displayItems;
	private Set<ProductData> pdSet;
	private boolean ProductExist = false;
	private IProductContainer originalContainer;
	
	public TransferItemCommand(IItem item, IProductContainer target, Map<ProductData, List<ItemData>> displayItems, Set<ProductData> pdSet)
	{
		this.items.add(item);
		this.originalContainer = this.items.get(0).getContainer();
		this.target = target;
		this.displayItems = displayItems;
		this.pdSet = pdSet;
	}
	/**
	 * Transfer the item(s) in this object's Set of Items
	 */
	@Override
	public void execute()
	{
		ItemData id = (ItemData) this.items.get(0).getTag();
		ProductData pd = (ProductData) this.items.get(0).getProduct().getTag();
		this.pdSet.add(pd);

		if(this.displayItems.containsKey(pd))
		{
			this.displayItems.get(pd).add(id);
		}
		else
		{
			ArrayList<ItemData> temp = new ArrayList<ItemData>();
			temp.add(id);
			this.displayItems.put(pd, temp);
		}

		this.originalContainer.transferItem(this.items.get(0), this.target);

	}

	/**
	 * Un-transfer the items in this object's Set of Items
	 */
	@Override
	public void undo()
	{
		ItemData id = (ItemData) this.items.get(0).getTag();
		ProductData pd = (ProductData) this.items.get(0).getProduct().getTag();
		this.pdSet.remove(pd);

//		if(this.displayItems.containsKey(pd))
//		{
			this.displayItems.get(pd).remove(id);
			if(!this.ProductExist)
			{
				this.displayItems.remove(pd);
				this.target.removeProduct(this.items.get(0).getProduct());
			}
//		}
//		else
//		{
//			ArrayList<ItemData> temp = new ArrayList<ItemData>();
//			temp.add(id);
//			this.displayItems.put(pd, temp);
//		}

		this.target.transferItem(this.items.get(0), this.originalContainer);
	}
}
