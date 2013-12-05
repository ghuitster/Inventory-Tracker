
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import model.visitor.IVisitor;

/**
 * A class to represent a product container
 * @author David
 */
public abstract class ProductContainer extends Observable implements
		IProductContainer, Serializable

{
	
	static int lastUsedId = 0;
	
	private static final long serialVersionUID = 9015876223451150036L;
	protected String name;
	protected SortedSet<IProduct> products;
	protected SortedSet<IItem> items;
	protected SortedSet<IProductGroup> productGroups;
	private int id;
	
	private transient Object tag;

	protected ProductContainer(String name)
	{
		this.id = lastUsedId++;
		init(name);
	}
	
	protected ProductContainer(int id, String name)
	{
		this.id = id;
		init(name);
	}

	private void init(String name)
	{
		this.name = name;
		products = new TreeSet<IProduct>();
		items = new TreeSet<IItem>();
		productGroups = new TreeSet<IProductGroup>();
		this.addObserver(Inventory.getInstance());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#ableToAddItem(model.IItem)
	 */
	@Override
	public boolean ableToAddItem(IItem item)
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#ableToAddProduct(model.IProduct)
	 */
	@Override
	public boolean ableToAddProduct(IProduct product)
	{
		if(this.products.contains(product))
			return false;

		for(IProductContainer container: this.productGroups)
			return (container.ableToAddProduct(product));

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#ableToAddProductGroup(model.ProductGroup)
	 */
	@Override
	public boolean ableToAddProductGroup(IProductGroup productGroup)
	{
		if(this.productGroups.contains(productGroup))
			return false;

		return true;
	}

	@Override
	public boolean ableToAddProductGroupNamed(String name)
	{
		if(name == null || name.isEmpty())
			return false;

		for(IProductGroup pg: this.productGroups)
			if(pg.getName().toLowerCase().equals(name.toLowerCase()))
				return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#ableToRemoveItem(model.IItem)
	 */
	@Override
	public boolean ableToRemoveItem(IItem item)
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#ableToRemoveProduct(model.IProduct)
	 */
	@Override
	public boolean ableToRemoveProduct(IProduct product)
	{
		for(IItem item: this.items)
			if(item.getProduct().equals(product))
				return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#ableToRemoveProductGroup(model.ProductGroup)
	 */
	@Override
	public boolean ableToRemoveProductGroup(IProductGroup productGroup)
	{
		if(!productGroup.getAllItems().isEmpty())
			return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#addItem(model.Item)
	 */
	@Override
	public void addItem(IItem item)
	{
		IProduct product = item.getProduct();

		IProductContainer container = this.findContainer(product);

		if(container == null || container == this)
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
			container.addProduct(product);
			container.addItem(item);
		}

		this.setChanged();
		this.notifyObservers(new ObservableArgs(item, UpdateType.ADDED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#addProduct(model.Product)
	 */
	@Override
	public void addProduct(IProduct product)
	{
		IProductContainer container =
				this.getStorageUnit().findContainer(product);

		if(container == null)
		{
			this.products.add(product);
			product.addContainer(this);
		}
		else
		{
			this.products.add(product);
			product.addContainer(this);
			container.removeProduct(product);

			for(IItem item: container.getAllItems())
				if(item.getProduct().equals(product))
				{
					this.items.add(item);
					container.removeItem(item);
				}
		}

		this.setChanged();
		this.notifyObservers(new ObservableArgs(product, UpdateType.ADDED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#addProductGroup(model.ProductGroup)
	 */
	@Override
	public void addProductGroup(IProductGroup productGroup)
	{
		this.productGroups.add(productGroup);
		productGroup.setContainer(this);

		this.setChanged();
		this.notifyObservers(new ObservableArgs(productGroup, UpdateType.ADDED));
	}

	@Override
	public int compareTo(IProductContainer o)
	{
		return this.getName().toLowerCase()
				.compareTo(o.getName().toLowerCase());
	}

	@Override
	public IProductContainer findContainer(IProduct product)
	{
		for(IProductContainer con: this.productGroups)
		{
			IProductContainer result = con.findContainer(product);

			if(result != null)
				return result;
		}

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
	@Override
	public SortedSet<IItem> getAllItems()
	{
		return new TreeSet<IItem>(this.items);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#getAllProductGroups()
	 */
	@Override
	public SortedSet<IProductGroup> getAllProductGroups()
	{
		return new TreeSet<IProductGroup>(this.productGroups);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#getAllProducts()
	 */
	@Override
	public SortedSet<IProduct> getAllProducts()
	{
		return new TreeSet<IProduct>(this.products);
	}

	@Override
	public int getId()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#getName()
	 */
	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public IStorageUnit getStorageUnit()
	{
		IProductContainer container = this;

		while(container instanceof IProductGroup)
			container = ((IProductGroup) container).getContainer();

		return (IStorageUnit) container;
	}

	@Override
	public Object getTag()
	{
		return tag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#removeItem(model.Item)
	 */
	@Override
	public void removeItem(IItem item)
	{
		removeItem(item, UpdateType.REMOVED);
	}

	private void removeItem(IItem item, UpdateType updateType)
	{
		item.setContainer(null);
		this.items.remove(item);

		this.setChanged();
		this.notifyObservers(new ObservableArgs(item, updateType));
	}

	@Override
	public void removeItemTemporary(IItem item)
	{
		removeItem(item, UpdateType.TEMP_REMOVED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#removeProduct(model.IProduct)
	 */
	@Override
	public void removeProduct(IProduct product)
	{
		removeProduct(product, UpdateType.REMOVED);
	}

	private void removeProduct(IProduct product, UpdateType updateType)
	{
		product.removeContainer(this);
		this.products.remove(product);

		this.setChanged();
		this.notifyObservers(new ObservableArgs(product, updateType));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#removeProductGroup(model.ProductGroup)
	 */
	@Override
	public void removeProductGroup(IProductGroup productGroup)
	{
		this.productGroups.remove(productGroup);
		this.setChanged();
		this.notifyObservers(new ObservableArgs(productGroup,
				UpdateType.REMOVED));
	}

	@Override
	public void removeProductTemporary(IProduct product)
	{
		removeProduct(product, UpdateType.TEMP_REMOVED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#setName(java.lang.String)
	 */
	@Override
	public void setName(String name)
	{
		this.name = name;

		this.setChanged();
		this.notifyObservers(new ObservableArgs(this, UpdateType.UPDATED));
	}

	@Override
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProductContainer#transferItem(model.Item,
	 * model.ProductContainer)
	 */
	@Override
	public void transferItem(IItem item, IProductContainer targetContainer)
	{
		this.removeItem(item);

		List<IItem> itemsToAdd = new ArrayList<IItem>();
		itemsToAdd.add(item);

		if(targetContainer.getStorageUnit() == this.getStorageUnit())
		{
			Set<IItem> items = this.getAllItems();

			for(IItem containerItem: items)
				if(containerItem.getProduct() == item.getProduct())
					itemsToAdd.add(containerItem);

			this.removeProduct(item.getProduct());
		}
		else
		{
			IProductContainer existing =
					targetContainer.getStorageUnit().findContainer(
							item.getProduct());
			if(existing != null && existing != targetContainer)
			{
				Set<IItem> toMove = new HashSet<IItem>();

				for(IItem otherItem: existing.getAllItems())
				{
					if(otherItem.getProduct() == item.getProduct())
						toMove.add(otherItem);
				}

				for(IItem otherItem: toMove)
					existing.removeItemTemporary(otherItem);

				existing.removeProduct(item.getProduct());

				for(IItem otherItem: toMove)
					targetContainer.addItem(otherItem);
			}

		}

		for(IItem itemToAdd: itemsToAdd)
			targetContainer.addItem(itemToAdd);
	}

	void visitChildren(IVisitor visitor)
	{
		for(IProduct product: this.products)
		{
			visitor.visitProduct(product);
		}

		for(IItem item: this.items)
		{
			visitor.visitItem(item);
		}

		for(IProductGroup group: this.productGroups)
		{
			group.traverse(visitor);
		}
	}
}
