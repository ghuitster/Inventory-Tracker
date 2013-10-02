
package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A class to represent a product container
 * @author David
 */
public abstract class ProductContainer implements Serializable
{
	private static final long serialVersionUID = 9015876223451150036L;
	protected String name;
	protected Set<Product> products;
	protected Set<Item> items;
	protected Set<ProductGroup> productGroups;

	protected ProductContainer(String name)
	{
		this.name = name;
		products = new HashSet<Product>();
		items = new HashSet<Item>();
		productGroups = new HashSet<ProductGroup>();
	}
	
	/**
	 * @precondition item.barcode != empty
	 * @precondition item.barcode is a valid UPC barcode
	 * @precondition item.barcode is unique among all items
	 * @precondition item.date != empty
	 * @precondition item.date is not in the future
	 * @precondition item.date is not before 1/1/2000
	 * @param item the Item to attempt to add
	 * @return whether the Item can be added or not
	 */
	public boolean ableToAddItem(Item item)
	{
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
		if(this.products.contains(product))
			return false;

		for(ProductContainer container: this.productGroups)
			return (container.ableToAddProduct(product));

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

		ProductContainer container = this.findContainer(product);

		if(container == null)
		{
			product.addContainer(this);
			this.products.add(product);
			this.items.add(item);
		}
		else
		{
			product.addContainer(container);
			container.products.add(product);
			container.items.add(item);
		}
	}

	private ProductContainer findContainer(Product product)
	{
		for(ProductContainer con: this.productGroups)
			return con.findContainer(product);

		if(this.products.contains(product))
			return this;
		else
			return null;
	}

	/**
	 * @precondition product must be a valid Product and not null
	 * @postcondition my.products.contains(product)
	 * @param product the Product to add
	 */
	public void addProduct(Product product)
	{
		ProductContainer container = this.findContainer(product);

		if(container == null)
		{
			this.products.add(product);
			product.addContainer(this);
		}
		else
		{
			this.products.add(product);
			product.addContainer(this);
			product.removeContainer(container);

			for(Item item: container.items)
				if(item.getProduct().equals(product))
				{
					this.items.add(item);
					container.items.remove(item);
				}
		}
	}

	/**
	 * @precondition productGroup must be a valid ProductGroup and not null
	 * @precondition productGroup.name is unique in this ProductContainer
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
		Inventory.getInstance().reportRemovedItem(item);
	}

	/**
	 * @precondition product must be a valid Product and not null
	 * @precondition my.items.doesNotContain(Items of this Product type)
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
	 * @param targetProductContainer the target ProductContainer
	 */
	public void transferItem(Item item, ProductContainer targetContainer)
	{
		ProductContainer container = this.findContainer(item.getProduct());

		if(container == null)
		{
			targetContainer.products.add(item.getProduct());
			item.getProduct().addContainer(targetContainer);
		}
		else
		{
			for(Item it: this.items)
				if(it.getProduct().equals(item.getProduct()))
				{
					this.items.remove(it);
					targetContainer.items.add(it);
					it.setContainer(targetContainer);
				}

			this.products.remove(item.getProduct());
			item.getProduct().removeContainer(this);
			targetContainer.products.add(item.getProduct());
			item.getProduct().addContainer(targetContainer);
		}

		targetContainer.items.add(item);
		item.setContainer(targetContainer);
		this.items.remove(item);
	}
}
