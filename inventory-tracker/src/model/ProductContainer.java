
package model;

import gui.common.Tagable;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A class to represent a product container
 * @author David
 */
public abstract class ProductContainer extends Tagable implements Serializable
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
	 * @pre item.barcode != empty
	 * @pre item.barcode is a valid UPC barcode
	 * @pre item.barcode is unique among all items
	 * @pre item.date != empty
	 * @pre item.date is not in the future
	 * @pre item.date is not before 1/1/2000
	 * @param item the Item to attempt to add
	 * @return whether the Item can be added or not
	 */
	public boolean ableToAddItem(Item item)
	{
		return true;
	}

	/**
	 * @pre product.barcode != empty
	 * @pre product.description != empty
	 * @pre product.shelfLife > 0
	 * @pre product.threeMonthSupply > 0
	 * @pre product.unitSize > 0
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
	 * @pre productGroup.name != empty
	 * @pre productGroup.container != empty
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
	 * @pre this.items.contains(item)
	 * @param item the Item to attempt to remove
	 * @return whether the Item can be removed or not
	 */
	public boolean ableToRemoveItem(Item item)
	{
		return true;
	}

	/**
	 * @pre this.products.contains(product)
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
	 * @pre productGroup must be a valid ProductGroup and not null
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
	 * @pre item must be a valid Item and not null
	 * @post my.items.contains(item)
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

	/**
	 * @pre product must be a valid Product and not null
	 * @post my.products.contains(product)
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
	 * @pre productGroup must be a valid ProductGroup and not null
	 * @pre productGroup.name is unique in this ProductContainer
	 * @post my.productGroups.contains(productGroup)
	 * @param productGroup the ProductGroup to add
	 */
	public void addProductGroup(ProductGroup productGroup)
	{
		this.productGroups.add(productGroup);
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
	 * @pre this.items != null
	 * @return the Set<Item> of all Items
	 */
	public Set<Item> getAllItems()
	{
		return new HashSet<Item>(this.items);
	}

	/**
	 * @pre this.productGroups != null
	 * @return the Set<ProductGroup> of all ProductGroups
	 */
	public Set<ProductGroup> getAllProductGroups()
	{
		return new HashSet<ProductGroup>(this.productGroups);
	}

	/**
	 * @pre this.products != null
	 * @return the Set<Product> of all Products
	 */
	public Set<Product> getAllProducts()
	{
		return new HashSet<Product>(this.products);
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * @pre item must be a valid Item and not null
	 * @post my.items.doesNotContain(item)
	 * @param item the Item to remove
	 */
	public void removeItem(Item item)
	{
		this.items.remove(item);
		Inventory.getInstance().reportRemovedItem(item);
	}

	/**
	 * @pre product must be a valid Product and not null
	 * @pre my.items.doesNotContain(Items of this Product type)
	 * @post my.products.doesNotContain(product)
	 * @param product the Product to remove
	 */
	public void removeProduct(Product product)
	{
		this.products.remove(product);
	}

	/**
	 * @pre productGroup must be a valid ProductGroup and not null
	 * @post my.productGroups.doesNotContain(productGroup)
	 * @param productGroup the ProductGroup to remove
	 */
	public void removeProductGroup(ProductGroup productGroup)
	{
		this.productGroups.remove(productGroup);
	}

	/**
	 * @pre name must be a valid String and not null
	 * @post my.name == name
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @pre item must be a valid item and not null
	 * @pre otherProductContainer must be a valid ProductContainer and not null
	 * @post my.items.doesNotContain(item)
	 * @post otherProductContainer.items.contains(item)
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
