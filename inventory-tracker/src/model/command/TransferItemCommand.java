
package model.command;

import gui.item.ItemData;
import gui.product.ProductData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.IItem;
import model.IProductContainer;

public class TransferItemCommand extends SingleItemCommand
{
	private Map<ProductData, List<ItemData>> displayItems;
	private Set<ProductData> pdSet;
	private IProductContainer ProductExistInContainer = null;
	private IProductContainer originalContainer;
	
	public TransferItemCommand(IItem item, IProductContainer target, 
			Map<ProductData, List<ItemData>> displayItems, Set<ProductData> pdSet)
	{
		super(target, item);
		this.originalContainer = this.item.getContainer();
		this.displayItems = displayItems;
		this.pdSet = pdSet;
		this.ProductExistInContainer = this.target.findContainer(this.item.getProduct());
	}
	/**
	 * Transfer the item(s) in this object's Set of Items
	 */
	@Override
	public void execute()
	{
		ItemData id = (ItemData) this.item.getTag();
		ProductData pd = (ProductData) this.item.getProduct().getTag();
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

		this.originalContainer.transferItem(this.item, this.target);

	}

	/**
	 * Un-transfer the items in this object's Set of Items
	 */
	@Override
	public void undo()
	{
		ItemData id = (ItemData) this.item.getTag();
		ProductData pd = (ProductData) this.item.getProduct().getTag();
		this.pdSet.remove(pd);
		
		this.displayItems.get(pd).remove(id);
		if(this.ProductExistInContainer == null)
		{
			this.displayItems.remove(pd);
			this.target.removeProduct(this.item.getProduct());
		}

		this.target.transferItem(this.item, this.originalContainer);
	}
}
