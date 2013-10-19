
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

/**
 * A class to represent a product
 * @author David
 */
public class Product extends Observable implements IProduct, Serializable
{
	// Variables
	private static final long serialVersionUID = 1835988277946941153L;
	private Date creationDate;
	private IProductDescription description;
	private IBarcode barcode;
	private Amount size;
	private int shelfLife;
	private CountThreeMonthSupply threeMonthSupply;
	private final Set<IProductContainer> containers;

	/**
	 * @pre creationDate must be == the earliest EntryDate for any item of this
	 *      Product
	 * @pre description must be valid
	 * @pre barcode must be == to the manufacturer's barcode
	 * @pre size the number component must be > 0
	 * @pre shelfLife must be >= 0
	 * @pre threeMonthSupply number component must be >= 0
	 * @pre containers each container in set must exist
	 * @pre at most one product container in a storage unit may contain a
	 *      particular project
	 * @post creationData == passed in creationDate
	 * @post description == passed in description
	 * @post barcode == passed in barcode
	 * @post size == passed in size
	 * @post shelfLife == passed in shelfLife
	 * @post threeMonthSupply == passed in threemonthSupply
	 * @post containers == passed in containers
	 * @param creationDate
	 * @param description
	 * @param barcode
	 * @param size
	 * @param shelfLife
	 * @param threeMonthSupply
	 */
	public Product(Date creationDate, String description, IBarcode barcode,
			Amount size, int shelfLife, CountThreeMonthSupply threeMonthSupply)
	{
		super();
		this.creationDate = creationDate;
		this.description = new ProductDescription(description);
		this.barcode = barcode;
		this.size = size;
		this.shelfLife = shelfLife;
		this.threeMonthSupply = threeMonthSupply;
		this.containers = new HashSet<IProductContainer>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#ableToAddContainer(model.StorageUnit)
	 */
	@Override
	public boolean ableToAddContainer(IStorageUnit container)
	{
		boolean response = false;

		if(container != null)
		{
			if(container.ableToAddProduct(this))
				response = true;
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#ableToRemoveContainer(model.ProductContainer)
	 */
	@Override
	public boolean ableToRemoveContainer(IProductContainer productContainer)
	{
		boolean response = false;

		if(productContainer != null)
		{
			if(productContainer.ableToRemoveProduct(this))
				response = true;
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#ableToSetBarcode(model.IBarcode)
	 */
	@Override
	public boolean ableToSetBarcode(IBarcode barcode)
	{
		boolean response = false;

		if(ProductBarcode.isValid(barcode.getNumber()))
			response = true;

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#ableToSetDescription(java.lang.String)
	 */
	@Override
	public boolean ableToSetDescription(String description)
	{
		return ProductDescription.isValid(description);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#ableToSetShelfLife(int)
	 */
	@Override
	public boolean ableToSetShelfLife(int shelfLife)
	{
		boolean response = false;

		if(shelfLife > 0)
			response = true;

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#ableToSetSize(model.Amount)
	 */
	@Override
	public boolean ableToSetSize(Amount size)
	{
		boolean response = false;

		if(size != null)
		{
			if(size instanceof UnitSize)
			{
				UnitSize temp = (UnitSize) size;
				if(temp.getSize() > 0.0 && temp.getUnitType() != UnitType.COUNT)
				{
					response = true;
				}
			}
			else if(size instanceof CountUnitSize)
			{
				CountUnitSize temp = (CountUnitSize) size;
				if(temp.getSize() > 0 && temp.getUnitType() == UnitType.COUNT)
				{
					response = true;
				}
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * model.IProduct#ableToSetThreeMonthSupply(model.CountThreeMonthSupply)
	 */
	@Override
	public boolean ableToSetThreeMonthSupply(
			CountThreeMonthSupply threeMonthSupply)
	{
		boolean response = false;

		if(threeMonthSupply != null)
		{
			if(threeMonthSupply.getAmount() > 0)
			{
				if(threeMonthSupply.getUnitType() == UnitType.COUNT)
				{
					response = true;
				}
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#addContainer(model.ProductContainer)
	 */
	@Override
	public void addContainer(IProductContainer productContainer)
	{
		this.containers.add(productContainer);

		if(this.countObservers() == 0)
			this.addObserver(Inventory.getInstance());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#equals(java.lang.Object)
	 */

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if(barcode == null)
		{
			if(other.barcode != null)
				return false;
		}
		else if(!barcode.equals(other.barcode))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(IProduct o)
	{
		return this.getDescription().toString().toLowerCase()
				.compareTo(o.getDescription().toString().toLowerCase());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#getBarcode()
	 */
	@Override
	public IBarcode getBarcode()
	{
		return barcode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#getContainers()
	 */
	@Override
	public Set<IProductContainer> getContainers()
	{
		return new HashSet<IProductContainer>(this.containers);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#getCreationDate()
	 */
	@Override
	public Date getCreationDate()
	{
		return creationDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#getDescription()
	 */
	@Override
	public IProductDescription getDescription()
	{
		return description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#getShelfLife()
	 */
	@Override
	public int getShelfLife()
	{
		return shelfLife;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#getSize()
	 */
	@Override
	public Amount getSize()
	{
		return this.size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#getThreeMonthSupply()
	 */
	@Override
	public Amount getThreeMonthSupply()
	{
		return threeMonthSupply;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barcode == null) ? 0 : barcode.hashCode());
		result =
				prime * result
						+ ((containers == null) ? 0 : containers.hashCode());
		result =
				prime
						* result
						+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result =
				prime * result
						+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + shelfLife;
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result =
				prime
						* result
						+ ((threeMonthSupply == null) ? 0 : threeMonthSupply
								.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#removeContainer(model.ProductContainer)
	 */
	@Override
	public void removeContainer(IProductContainer productContainer)
	{
		this.containers.remove(productContainer);

		if(this.getContainers().size() == 0)
			this.deleteObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#setBarcode(model.IBarcode)
	 */
	@Override
	public void setBarcode(IBarcode barcode)
	{
		if(barcode != null && ProductBarcode.isValid(barcode.getNumber()))
			this.barcode = barcode;

		this.setChanged();
		this.notifyObservers(new ObservableArgs(this, UpdateType.UPDATED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#setCreationDate(java.util.Date)
	 */
	@Override
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;

		this.setChanged();
		this.notifyObservers(new ObservableArgs(this, UpdateType.UPDATED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#setDescription(model.ProductDescription)
	 */
	@Override
	public void setDescription(IProductDescription description)
	{
		this.description = description;

		this.setChanged();
		this.notifyObservers(new ObservableArgs(this, UpdateType.UPDATED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#setShelfLife(int)
	 */
	@Override
	public void setShelfLife(int shelfLife)
	{
		this.shelfLife = shelfLife;
		
		this.setChanged();
		this.notifyObservers(new ObservableArgs(this, UpdateType.UPDATED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#setSize(model.UnitSize)
	 */
	@Override
	public void setSize(Amount size)
	{
		this.size = size;

		this.setChanged();
		this.notifyObservers(new ObservableArgs(this, UpdateType.UPDATED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#setThreeMonthSupply(model.CountThreeMonthSupply)
	 */
	@Override
	public void setThreeMonthSupply(CountThreeMonthSupply threeMonthSupply)
	{
		this.threeMonthSupply = threeMonthSupply;

		this.setChanged();
		this.notifyObservers(new ObservableArgs(this, UpdateType.UPDATED));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IProduct#toString()
	 */
	@Override
	public String toString()
	{
		return "Product [creationDate=" + creationDate + ", description="
				+ description + ", barcode=" + barcode + ", size=" + size
				+ ", shelfLife=" + shelfLife + ", threeMonthSupply="
				+ threeMonthSupply + ", containers=" + containers + "]";
	}

	private transient Object tag;

	@Override
	public Object getTag()
	{
		return tag;
	}

	@Override
	public void setTag(Object tag)
	{
		this.tag = tag;
	}
}
