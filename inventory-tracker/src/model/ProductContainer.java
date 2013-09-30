
package model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * A class to represent a product container
 * @author David
 */
public abstract class ProductContainer
{
	protected String name;
	protected Set<Product> products;
	protected List<Item> items;
	protected Set<ProductGroup> productGroups;

	/**
	 * @precondition item.barcode != empty
	 * @precondition item.barcode is a valid UPC barcode
	 * @precondition item.date != empty
	 * @precondition item.date is not in the future
	 * @precondition item.date is not before 1/1/2000
	 * @param item the Item to attempt to add
	 * @return whether the Item can be added or not
	 */
	public boolean ableToAddItem(Item item)
	{
		for(StorageUnit unit: Inventory.getInstance().getAllStorageUnits())
			for(Item ite: unit.items)
				if(ite.getBarcode().equals(item.getBarcode()))
					return false;

		return true;
	}

	/**
	 * @precondition product.barcode != empty
	 * @precondition product.description != empty
	 * @precondition product.shelfLife > 0
	 * @precondition product.threeMonthSupply > 0
	 * @precondition product.unitSize > 0
	 * @param product the Product to attempt to add
	 * @return whether the Product can be added or not
	 */
	public boolean ableToAddProduct(Product product)
	{
		for(ProductContainer container: this.productGroups)
			if(container.products.contains(product))
				return false;

		if(this.products.contains(product))
			return false;

		return true;
	}

	/**
	 * @precondition productGroup.name != empty
	 * @precondition productGroup.container != empty
	 * @param productGroup the ProductGroup to attempt to add
	 * @return whether the ProductGroup can be added or not
	 */
	public boolean ableToAddProductGroup(ProductGroup productGroup)
	{
		if(this.productGroups.contains(productGroup))
			return false;

		return true;
	}

	/**
	 * @precondition this.items.contains(item)
	 * @param item the Item to attempt to remove
	 * @return whether the Item can be removed or not
	 */
	public boolean ableToRemoveItem(Item item)
	{
		return true;
	}

	/**
	 * @precondition this.products.contains(product)
	 * @param product the Product to attempt to remove
	 * @return whether the Product can be removed or not
	 */
	public boolean ableToRemoveProduct(Product product)
	{
		for(Item item: this.items)
			if(item.getProduct().equals(product))
				return false;

		return true;
	}

	/**
	 * @precondition productGroup must be a valid ProductGroup and not null
	 * @param productGroup the ProductGroup to attempt to remove
	 * @return whether the ProductGroup can be removed or not
	 */
	public boolean ableToRemoveProductGroup(ProductGroup productGroup)
	{
		if(!productGroup.items.isEmpty())
			return false;

		return true;
	}

	/**
	 * @precondition item must be a valid Item and not null
	 * @postcondition my.items.contains(item)
	 * @param item the Item to add
	 */
	public void addItem(Item item)
	{
		Product product = item.getProduct();

		this.products.add(product);

		for(ProductGroup group: this.productGroups)
			if(group.products.contains(product))
			{
				product.addContainer(group);
				group.products.add(product);
			}

		product.addContainer(this);

		this.items.add(item);
	}

	/**
	 * @precondition product must be a valid Product and not null
	 * @postcondition my.products.contains(product)
	 * @param product the Product to add
	 */
	public void addProduct(Product product)
	{
		boolean found = false;
		ProductGroup existingGroup = null;

		for(ProductGroup group: this.productGroups)
			if(group.products.contains(product))
			{
				found = true;
				existingGroup = group;
			}

		if(found)
		{
			for(Item item: existingGroup.items)
				if(item.getProduct().equals(product))
					existingGroup.items.remove(item);

			existingGroup.products.remove(product);
		}
		else
			this.products.add(product);
	}

	/**
	 * @precondition productGroup must be a valid ProductGroup and not null
	 * @postcondition my.productGroups.contains(productGroup)
	 * @param productGroup the ProductGroup to add
	 */
	public void addProductGroup(ProductGroup productGroup)
	{
		this.productGroups.add(productGroup);
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * @precondition item must be a valid Item and not null
	 * @postcondition my.items.doesNotContain(item)
	 * @param item the Item to remove
	 */
	public void removeItem(Item item)
	{
		this.items.remove(item);
		item.setExitTime(new Date(System.currentTimeMillis()));
		item.setContainer(null);
		Map<Date, Set<Item>> removedItems =
				Inventory.getInstance().getRemovedItems();

		if(removedItems.get(item.getExitTime()) == null)
		{
			Set<Item> items = new TreeSet<Item>();
			items.add(item);
			removedItems.put(item.getExitTime(), items);
		}
		else
			removedItems.get(item.getExitTime()).add(item);
	}

	/**
	 * @precondition product must be a valid Product and not null
	 * @postcondition my.products.doesNotContain(product)
	 * @param product the Product to remove
	 */
	public void removeProduct(Product product)
	{
		this.products.remove(product);
	}

	/**
	 * @precondition productGroup must be a valid ProductGroup and not null
	 * @postcondition my.productGroups.doesNotContain(productGroup)
	 * @param productGroup the ProductGroup to remove
	 */
	public void removeProductGroup(ProductGroup productGroup)
	{
		this.productGroups.remove(productGroup);
	}

	/**
	 * @precondition name must be a valid String and not null
	 * @postcondition my.name == name
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @precondition item must be a valid item and not null
	 * @precondition otherProductContainer must be a valid ProductContainer and
	 *               not null
	 * @postcondition my.items.doesNotContain(item)
	 * @postcondition otherProductContainer.items.contains(item)
	 * @param item the Item to transfer
	 * @param otherProductContainer the target ProductContainer
	 */
	public void transferItem(Item item, ProductContainer otherProductContainer)
	{
		for(ProductGroup productGroup: this.productGroups)
			productGroup.products.remove(item.getProduct());

		this.products.remove(item.getProduct());
		item.getProduct().addContainer(otherProductContainer);

		ProductGroup targetGroup = (ProductGroup) otherProductContainer;
		for(ProductGroup productGroup: otherProductContainer.productGroups)
			if(productGroup.products.contains(item.getProduct()))
				targetGroup = productGroup;

		// need to be able to remove myself from the product's set of containers

		item.setContainer(targetGroup);
		this.items.remove(item);
	}
}
