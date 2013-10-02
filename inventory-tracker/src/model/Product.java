
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * A class to represent a product
 * @author David
 */
public class Product implements Serializable
{
	// Variables
	private static final long serialVersionUID = 1835988277946941153L;
	private Date creationDate;
	private ProductDescription description;
	private Barcode barcode;
	private Amount size;
	private int shelfLife;
	private CountThreeMonthSupply threeMonthSupply;
	private final Set<ProductContainer> containers;

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
	 * @param containers
	 */
	public Product(Date creationDate, String description, Barcode barcode,
			Amount size, int shelfLife, CountThreeMonthSupply threeMonthSupply,
			Set<ProductContainer> containers)
	{
		super();
		this.creationDate = creationDate;
		this.description = new ProductDescription(description);
		this.barcode = barcode;
		this.size = size;
		this.shelfLife = shelfLife;
		this.threeMonthSupply = threeMonthSupply;
		this.containers = containers;
	}

	/**
	 * @pre container != null
	 * @param container the container to attempt to add
	 * @return whether the container can be added or not
	 */
	public boolean ableToAddContainer(StorageUnit container)
	{
		boolean response = false;

		if(container != null)
		{
			if(container.ableToAddProduct(this))
				response = true;
		}

		return response;
	}

	/**
	 * @pre barcode != null
	 * @param barcode the Barcode to attempt to set
	 * @return if the Barcode can be set or not
	 */
	public boolean ableToSetBarcode(Barcode barcode)
	{
		boolean response = false;

		if(Barcode.isValid(barcode.getNumber()))
			response = true;

		return response;
	}

	/**
	 * @pre description != null
	 * @param description the description to attempt to set
	 * @return if the description can be set or not
	 */
	public boolean ableToSetDescription(String description)
	{
		return ProductDescription.isValid(description);
	}

	/**
	 * @pre shelfLife must be > 0
	 * @param shelfLife the shelfLife to attempt to set
	 * @return if the shelflife can be set or not
	 */
	public boolean ableToSetShelfLife(int shelfLife)
	{
		boolean response = false;

		if(shelfLife > 0)
			response = true;

		return response;
	}

	/**
	 * @pre size != null
	 * @pre size number component must not be <= 0
	 * @param size the size to attempt to set
	 * @return whether the size can be set or not
	 */
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

	/**
	 * @pre threeMonthSupply != null
	 * @param threeMonthSupply the threeMonthSupply to attempt to set
	 * @return whether the threeMonthSupply could be set or not
	 */
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

	/**
	 * @pre container != null
	 * @pre this Product must not exist in another product container in this
	 *      storage unit
	 * @post container == passed in container
	 * @param productContainer the container to add
	 */
	public void addContainer(ProductContainer productContainer)
	{
		this.containers.add(productContainer);
	}

	/**
	 * @pre productContainer must != null
	 * @param productContainer the container to attempt to remove
	 */
	public boolean ableToRemoveContainer(ProductContainer productContainer)
	{
		boolean response = false;

		if(productContainer != null)
		{
			if(productContainer.ableToRemoveProduct(this))
				response = true;
		}

		return response;
	}

	/**
	 * @pre productContainer != null
	 * @pre productContainer must exist in the set of containers for this
	 *      product
	 * @post productContainer no longer exists in the set of containers for this
	 *       product
	 * @param productContainer the container to remove
	 */
	public void removeContainer(ProductContainer productContainer)
	{
		this.containers.remove(productContainer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
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
		if(containers == null)
		{
			if(other.containers != null)
				return false;
		}
		else if(!containers.equals(other.containers))
			return false;
		if(creationDate == null)
		{
			if(other.creationDate != null)
				return false;
		}
		else if(!creationDate.equals(other.creationDate))
			return false;
		if(description == null)
		{
			if(other.description != null)
				return false;
		}
		else if(!description.equals(other.description))
			return false;
		if(shelfLife != other.shelfLife)
			return false;
		if(size == null)
		{
			if(other.size != null)
				return false;
		}
		else if(!size.equals(other.size))
			return false;
		if(threeMonthSupply == null)
		{
			if(other.threeMonthSupply != null)
				return false;
		}
		else if(!threeMonthSupply.equals(other.threeMonthSupply))
			return false;
		return true;
	}

	/**
	 * @return the barcode
	 */
	public Barcode getBarcode()
	{
		return barcode;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate()
	{
		return creationDate;
	}

	/**
	 * @return the description
	 */
	public ProductDescription getDescription()
	{
		return description;
	}

	/**
	 * @return the shelfLife
	 */
	public int getShelfLife()
	{
		return shelfLife;
	}

	/**
	 * @return the size
	 */
	public Amount getSize()
	{
		return this.size;
	}

	/**
	 * @return the threeMonthSupply
	 */
	public Amount getThreeMonthSupply()
	{
		return threeMonthSupply;
	}

	/**
	 * @see java.lang.Object#hashCode()
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

	/**
	 * @pre barcode != null
	 * @post barcode == passed in barcode
	 * @param barcode the Barcode to set
	 */
	public void setBarcode(Barcode barcode)
	{
		if(barcode != null && Barcode.isValid(barcode.getNumber()))
			this.barcode = barcode;
	}

	/**
	 * @pre creationDate != null
	 * @pre creationDate == earliest entryDate of items of this product
	 * @post creationData == passed in creationDate
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}

	/**
	 * @pre != null
	 * @pre description must be valid
	 * @param description the description to set
	 */
	public void setDescription(ProductDescription description)
	{
		this.description = description;
	}

	/**
	 * @precondition shelfLife != null
	 * @precondition shelfLife must be non negative
	 * @postcondition shelfLife == passed in shelfLife
	 * @param shelfLife the shelfLife to set
	 */
	public void setShelfLife(int shelfLife)
	{
		this.shelfLife = shelfLife;
	}

	/**
	 * @pre size number component must be > 0
	 * @pre size != null
	 * @post size == passed in size
	 * @param size the size to set
	 */
	public void setSize(UnitSize size)
	{
		this.size = size;
	}

	/**
	 * @pre threeMonthSupply != null
	 * @pre threeMonthSupply number component must be > 0
	 * @post threeMonthSupply == passed in threeMonthSupply
	 * @param threeMonthSupply the threeMonthSupply to set
	 */
	public void setThreeMonthSupply(CountThreeMonthSupply threeMonthSupply)
	{
		this.threeMonthSupply = threeMonthSupply;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Product [creationDate=" + creationDate + ", description="
				+ description + ", barcode=" + barcode + ", size=" + size
				+ ", shelfLife=" + shelfLife + ", threeMonthSupply="
				+ threeMonthSupply + ", containers=" + containers + "]";
	}
}
