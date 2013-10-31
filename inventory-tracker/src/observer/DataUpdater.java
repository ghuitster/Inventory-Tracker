package observer;

import gui.inventory.ProductContainerData;
import gui.item.ItemData;
import gui.product.ProductData;
import model.IItem;
import model.IProduct;
import model.IProductContainer;
import model.IProductGroup;
import model.ITaggable;

public class DataUpdater
{
	public static void resetTagData(IItem item)
	{
		item.setTag(createItemData(item));
	}
	
	public static void resetTagData(IProduct product)
	{
		product.setTag(createProductData(product));
	}
	
	public static void resetTagData(IProductContainer productContainer)
	{
		productContainer.setTag(createProductContainerData(productContainer));
	}
	
	public static ProductContainerData createProductContainerData(IProductContainer productContainer)
	{
		ProductContainerData pcData = new ProductContainerData();
		pcData.setName(productContainer.getName());
		productContainer.setTag(pcData);
		pcData.setTag(productContainer);
		return pcData;
	}

	public static ProductData createProductData(IProduct product)
	{
		ProductData productData = new ProductData();
		productData.setBarcode(product.getBarcode().toString());
		productData.setDescription(product.getDescription().toString());
		productData.setShelfLife("" + product.getShelfLife());
		productData.setSize(product.getSize().toString());
		productData.setSupply(product.getThreeMonthSupply().toString());
		product.setTag(productData);
		productData.setTag(product);
		return productData;
	}
	
	public static ItemData createItemData(IItem item)
	{
		ItemData itemData = new ItemData();
		itemData.setBarcode(item.getBarcode().toString());
		itemData.setEntryDate(item.getEntryDate());
		itemData.setExpirationDate(item.getExpirationDate());
		if(item.getContainer() instanceof IProductGroup)
			itemData.setProductGroup(item.getContainer().getName());
		if(item.getContainer() != null)
			itemData.setStorageUnit(item.getContainer().getStorageUnit()
					.getName());
		item.setTag(itemData);
		itemData.setTag(item);
		return itemData;
	}
	
}
