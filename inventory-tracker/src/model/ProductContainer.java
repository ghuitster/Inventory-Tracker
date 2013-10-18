
package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A class to represent a product container
 * @author David
 */
public abstract class ProductContainer extends BaseProductContainer implements Serializable
		
{
	private static final long serialVersionUID = 9015876223451150036L;
	protected String name;
	protected Set<BaseProduct> products;
	protected Set<Item> items;
	protected Set<ProductGroup> productGroups;

	protected ProductContainer(String name)
	{
		this.name = name;
		products = new HashSet<BaseProduct>();
		items = new HashSet<Item>();
		productGroups = new HashSet<ProductGroup>();
		this.addObserver(Inventory.getInstance());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#ableToAddItem(model.IItem)
	 */
	public boolean ableToAddItem(BaseItem item)
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#ableToAddProduct(model.IProduct)
	 */
	public boolean ableToAddProduct(BaseProduct product)
	{
		if(this.products.contains(product))
			return false;

		for(BaseProductContainer container: this.productGroups)
			return (container.ableToAddProduct(product));

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#ableToAddProductGroup(model.ProductGroup)
	 */
	public boolean ableToAddProductGroup(IProductGroup productGroup)
	{
		if(this.productGroups.contains(productGroup))
			return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#ableToRemoveItem(model.IItem)
	 */
	public boolean ableToRemoveItem(BaseItem item)
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#ableToRemoveProduct(model.IProduct)
	 */
	public boolean ableToRemoveProduct(BaseProduct product)
	{
		for(BaseItem item: this.items)
			if(item.getProduct().equals(product))
				return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#ableToRemoveProductGroup(model.ProductGroup)
	 */
	public boolean ableToRemoveProductGroup(ProductGroup productGroup)
	{
		if(!productGroup.items.isEmpty())
			return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#addItem(model.Item)
	 */
	public void addItem(Item item)
	{
		BaseProduct product = item.getProduct();

		ProductContainer container = this.findContainer(product);

		if(container == null)
		{
			product.addContainer(this);
			item.setContainer(this);
			this.products.add(product);
			this.items.add(item);
		}
		else
		{
			product.addContainer(container);
			item.setContainer(container);
			container.products.add(product);
			container.items.add(item);
		}
		
		this.notifyObservers(new ObservableArgs(item, UpdateType.ADDED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#addProduct(model.Product)
	 */
	public void addProduct(BaseProduct product)
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
		
		this.notifyObservers(new ObservableArgs(product, UpdateType.ADDED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#addProductGroup(model.ProductGroup)
	 */
	public void addProductGroup(ProductGroup productGroup)
	{
		this.productGroups.add(productGroup);
		
		this.notifyObservers(new ObservableArgs(productGroup, UpdateType.ADDED));
	}

	private ProductContainer findContainer(BaseProduct product)
	{
		for(ProductContainer con: this.productGroups)
			return con.findContainer(product);

		if(this.products.contains(product))
			return this;
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#getAllItems()
	 */
	public Set<Item> getAllItems()
	{
		return new HashSet<Item>(this.items);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#getAllProductGroups()
	 */
	public Set<ProductGroup> getAllProductGroups()
	{
		return new HashSet<ProductGroup>(this.productGroups);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#getAllProducts()
	 */
	public Set<BaseProduct> getAllProducts()
	{
		return new HashSet<BaseProduct>(this.products);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#getName()
	 */
	public String getName()
	{
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#removeItem(model.Item)
	 */
	public void removeItem(Item item)
	{
		item.setContainer(null);
		this.items.remove(item);
		
		this.notifyObservers(new ObservableArgs(item, UpdateType.REMOVED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#removeProduct(model.IProduct)
	 */
	public void removeProduct(BaseProduct product)
	{
		product.removeContainer(this);
		this.products.remove(product);
		
		this.notifyObservers(new ObservableArgs(product, UpdateType.REMOVED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#removeProductGroup(model.ProductGroup)
	 */
	public void removeProductGroup(IProductGroup productGroup)
	{
		this.productGroups.remove(productGroup);
		this.notifyObservers(new ObservableArgs(productGroup, UpdateType.REMOVED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#setName(java.lang.String)
	 */
	public void setName(String name)
	{
		this.name = name;
		this.notifyObservers(new ObservableArgs(this, UpdateType.UPDATED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#transferItem(model.Item,
	 * model.ProductContainer)
	 */
	public void transferItem(Item item, ProductContainer targetContainer)
	{
		BaseProductContainer container = this.findContainer(item.getProduct());

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
		
		this.notifyObservers(new ObservableArgs(item, UpdateType.MOVED));
	}
	
	private Object tag;
	@Override
	public Object getTag()
	{ return tag; }

	@Override
	public void setTag(Object tag)
	{ this.tag = tag; }
}
