
package model.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gui.item.ItemData;
import gui.product.ProductData;
import observer.DataUpdater;
import model.IItem;
import model.IProduct;
import model.IProductContainer;

public class RemoveItemCommand extends SingleItemCommand
{
	private List<ProductData> displayProducts;
	private final Map<ProductData, List<ItemData>> displayItems;
	private ProductData removingProductData;
	private boolean productDataDidNotExist = false;
	
	public RemoveItemCommand(IProductContainer target, IItem item, List<ProductData> displayProducts, Map<ProductData, List<ItemData>> displayItems)
	{
		super(target, item);
		this.displayProducts = displayProducts;
		this.displayItems = displayItems;
	}
	/**
	 * Remove the item(s) in this object's Set of Items
	 */
	@Override
	public void execute()
	{
		ItemData removingItemData = DataUpdater.createItemData(this.item);
		IProduct removingProduct = this.item.getProduct();
		for(ProductData dp: displayProducts)
			if(dp.getTag().equals(removingProduct))
				this.removingProductData = dp;
		if(this.removingProductData == null)
		{
			this.productDataDidNotExist = true;
			this.removingProductData =
					DataUpdater.createProductData(removingProduct);
			removingProductData.setCount("1");
			displayProducts.add(removingProductData);
		}
		else
		{
			int count = Integer.parseInt(this.removingProductData.getCount());
			count ++;
			this.removingProductData.setCount("" + count);
		}
		addItems(removingProductData, removingItemData);
		
		if(this.target.ableToRemoveItem(this.item))
			this.target.removeItem(this.item);
	}

	/**
	 * Un-remove the items in this object's Set of Items
	 */
	@Override
	public void undo()
	{
		if(this.productDataDidNotExist)
		{
			this.displayItems.remove(this.removingProductData);
			this.displayProducts.remove(this.removingProductData);
			this.removingProductData = null;
		}
		else
		{
			int count = Integer.parseInt(this.removingProductData.getCount());
			count --;
			this.removingProductData.setCount("" + count);
			this.displayItems.get(removingProductData).remove(this.item.getTag());
		}
		this.target.addItem(this.item);
	}

	private void addItems(ProductData productData, ItemData itemData)
	{
		if(displayItems.get(productData) == null)
		{
			List<ItemData> tempList = new ArrayList<ItemData>();
			tempList.add(itemData);
			displayItems.put(productData, tempList);
		}
		else
			displayItems.get(productData).add(itemData);
	}
}
